package org.ics.flying_stars.network;


// Clients should send these packets in ASCII:
// JOIN
// Desc: Join game

// POS{0-9 your player number}{mouse x in %3.1f format}{mouse y in %3.1f format}
// Desc: Your current mouse pos

// DEAD{0-9 your player number}
// Desc: Send this if you die


// Clients should handle these packets in ASCII:
// GAME{2-9 number of players}
// Desc: Game has started with {0-9} players

// NUM{1-9 player number}
// Desc: You joined the the server with this player number

// OBS{Angle in %1.3f format}{10 integers 0-9 representing Colors}
// Desc: Create an object with the given angle and colors (the colors are represented in ordinal format)

// POS{0-9 player number}{mouse x in %3.1f format}{mouse y in %3.1f format}
// Desc: Current mouse pos of player num

// DEAD{0-9 player number}
// Desc: Player num died

import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.geometry.Vector2D;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class NetworkGameClient extends Thread {

    private final SocketAddress serverAddress;

    public boolean getDead() {
        return dead.get();
    }

    public void setDead() {
        this.dead.set(true);
    }

    public Vector2D getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(Vector2D currentPos) {
        this.currentPos = currentPos;
    }

    public int getPlayerNum() {
        return playerNum.get();
    }

    private void setPlayerNum(int num) {
        playerNum.set(num);
    }

    public State getClientState() {
        return clientState;
    }

    public enum State {
        NOT_JOINED,
        JOINING,
        COULD_NOT_JOIN,
        JOINED,
        ONGOING,
        FINISHED
    }
    private State clientState;
    private final DatagramSocket clientSocket;

    private final AtomicInteger playerNum = new AtomicInteger(0);
    private final AtomicBoolean dead = new AtomicBoolean(false);
    private volatile Vector2D currentPos = null;

    public record ObstacleCreationInfo(double angle, Colour[] colors){}

    public final ConcurrentHashMap<Integer, Vector2D> playerPositionsMap;
    public final ConcurrentLinkedQueue<ObstacleCreationInfo> obstacleCreationInfoQueue;

    public NetworkGameClient(SocketAddress serverAddress) throws SocketException {
        this.serverAddress = serverAddress;
        clientSocket = new DatagramSocket();

        // Set state
        clientState = State.NOT_JOINED;

        // Set daemon
        setDaemon(true);

        // Create concurrent queues
        obstacleCreationInfoQueue = new ConcurrentLinkedQueue<>();
        playerPositionsMap = new ConcurrentHashMap<>();
    }

    private void sendToServer(String string) throws IOException {
        byte[] bytes = string.getBytes(StandardCharsets.US_ASCII);
        clientSocket.send(
                new DatagramPacket(bytes, bytes.length, serverAddress)
        );
    }

    private void sendPos() throws IOException {
        sendToServer("POS%d%3.1f%3.1f".formatted(getPlayerNum(), getCurrentPos().getX(), getCurrentPos().getY()));
    }

    private void sendDead() throws IOException {
        sendToServer("DEAD" + getPlayerNum());
    }

    private boolean joinGame() throws IOException {
        // Set state
        clientState = State.JOINING;

        // Set join timeout to 30 secs
        clientSocket.setSoTimeout(30 * 1000);

        // Send join
        sendToServer("JOIN");

        // Wait for player num
        DatagramPacket playerNumPacket = new DatagramPacket(new byte[4], 4);
        clientSocket.receive(playerNumPacket);

        // Set player num
        setPlayerNum(playerNumPacket.getData()[3]);

        // Set state
        clientState = State.JOINED;

        return true;
    }

    private void handlePos(String posInfo) {
        // POS{0-9 player number}{mouse x in %3.1f format}{mouse y in %3.1f format}
        int playerNum = Integer.parseInt(posInfo.substring(3,4));
        double mouseX = Double.parseDouble(posInfo.substring(4, 9));
        double mouseY = Double.parseDouble(posInfo.substring(9, 14));

        playerPositionsMap.put(playerNum, new Vector2D(mouseX, mouseY));
    }

    private void handleObs(String obsInfo) {
        // OBS{Angle in %1.3f format}{10 integers 0-9 representing Colors}
        double angle = Double.parseDouble(obsInfo.substring(3, 8));
        Colour[] colors = new Colour[10];
        for (int i=8; i<18; i++) {
            int colorOrdinal = Character.getNumericValue(obsInfo.charAt(i));
            colors[i-8] = Colour.values()[colorOrdinal];
        }

        obstacleCreationInfoQueue.add(new ObstacleCreationInfo(angle, colors));
    }

    @Override
    public void run() {
        boolean joined = false;
        try {
            joined = joinGame();
        } catch (IOException ignored) {
        }

        if (!joined) {
            // Exit prematurely
            clientState = State.COULD_NOT_JOIN;
            return;
        }

        // Wait for game to start TODO
        // Set wait  timeout to 30 secs
        try {
            clientSocket.setSoTimeout(30 * 1000);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        DatagramPacket gameStartedPacket = new DatagramPacket(new byte[5], 5);
        try {
            clientSocket.receive(gameStartedPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Set state
        clientState = State.ONGOING;

        // Update task
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Send updates
                try {
                    sendPos();
                    if (getDead()) {
                        sendDead();
                    }
                } catch (IOException ignored) {
                }
            }
        }, 100/3, 100/3);

        // Loop forever, receiving POS/DEAD/OBS packets and handling
        DatagramPacket playerInfoPacket = new DatagramPacket(new byte[16], 16);
        String gameInfo;
        boolean gameFin = false;
        while (!gameFin) {
            try {
                clientSocket.receive(playerInfoPacket);
                gameInfo = new String(playerInfoPacket.getData(), StandardCharsets.US_ASCII);
                // Check info type and handle accordingly
                if (gameInfo.contains("DEAD")) {
                    // TODO
                    gameFin = true;
                } else if (gameInfo.contains("OBS")) {
                    handleObs(gameInfo);

                } else if (gameInfo.contains("POS")) {
                    handlePos(gameInfo);
                }
            } catch (IOException ignored) {
            }
        }

        // Set state to finished
        clientState = State.FINISHED;

        // Close everything
        clientSocket.close();
    }
}
