package org.ics.flying_stars.network;

import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.settings.Settings;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

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

public class NetworkGameServer extends Thread {
    private final AtomicBoolean waitForPlayers = new AtomicBoolean(true);

    public void stopWaitingForPlayers() {
        this.waitForPlayers.set(false);
    }

    private boolean waitForPlayers() {
        return waitForPlayers.get();
    }

    public int playerCount() {
        return playerAddresses.size();
    }

    public enum State {
        NOT_STARTED,
        WAITING_FOR_PLAYERS,
        RUNNING,
        FINISHED
    }

    public final int PORT = 10000;
    private State serverState;
    private final Settings settings;
    private final DatagramSocket gameServerSocket;
    private ArrayList<SocketAddress> playerAddresses;

    public NetworkGameServer(Settings settings) throws SocketException {
        this.settings = settings;

        // Create a udp socket
        gameServerSocket = new DatagramSocket(PORT);

        // Set state
        serverState = State.NOT_STARTED;

        // Set daemon
        setDaemon(true);
    }


    private void waitForPlayerConnections() throws SocketException {
        // Set state
        serverState = State.WAITING_FOR_PLAYERS;

        // Initialize player addresses
        playerAddresses = new ArrayList<>();

        // Set wait timeout for waiting for players to connect
        gameServerSocket.setSoTimeout(2000);

        // Create empty packet
        DatagramPacket playerJoinGame = new DatagramPacket(new byte[4], 4);

        while (waitForPlayers()) {
            try {
                gameServerSocket.receive(playerJoinGame);
                // Check if the received packet says JOIN
                String joinString = new String(playerJoinGame.getData(), StandardCharsets.US_ASCII);

                // Register player address
                playerAddresses.add(playerJoinGame.getSocketAddress());

                // Send player id back as an ack
                String playerID = "PLAYER" + playerCount();
                byte[] bytes = playerID.getBytes(StandardCharsets.US_ASCII);
                gameServerSocket.send(
                        new DatagramPacket(bytes, bytes.length, playerJoinGame.getSocketAddress())
                );


            } catch (IOException ignored) {
            }
        }

        // Send a settings packet to every player TODO

    }

    private void sendToAllPlayers(String string) throws IOException {
        byte[] bytes = string.getBytes(StandardCharsets.US_ASCII);
        for (SocketAddress playerAddress: playerAddresses) {
            gameServerSocket.send(
                    new DatagramPacket(string.getBytes(), string.getBytes().length, playerAddress)
            );
        }
    }

    private void startGame() throws IOException {
        // Set state
        serverState = State.RUNNING;

        // Send a game started packet to every player connected
        String gameStarted = "GAME" + playerCount();
        sendToAllPlayers(gameStarted);

        // Start the shape spawner according to settings and sender
        int spawnPeriod = (int) (1000 * settings.getDifficulty().getDifficultyLevel());

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    // Create random angle
                    double randomAngle = Math.random() * Math.PI / 3;

                    // Create random colors array
                    Colour[] colors = Colour.getShuffled();

                    // Build spawn info string
                    StringBuilder infoBuilder = new StringBuilder("OBS");
                    infoBuilder.append("%1.3f".formatted(randomAngle));
                    for (Colour c: colors) {
                        infoBuilder.append(c.ordinal());
                    }

                    // Send info to all players
                    sendToAllPlayers(infoBuilder.toString());

                } catch (IOException ignored) {
                }
            }
        }, spawnPeriod, spawnPeriod);
    }

    public void run() {
        try {
            startGame();
        } catch (IOException e) {
            // TODO better handling
            throw new RuntimeException(e);
        }

        // Loop forever, receiving POS/DEAD packets and relaying to all players
        DatagramPacket playerInfoPacket = new DatagramPacket(new byte[16], 16);
        String playerInfo;
        boolean gameFin = false;
        while (!gameFin) {
            try {
                gameServerSocket.receive(playerInfoPacket);
                playerInfo = new String(playerInfoPacket.getData(), StandardCharsets.US_ASCII);
                // Check info type for DEAD
                if (playerInfo.contains("DEAD")) {
                    // TODO
                    gameFin = true;
                }
                // Relay info to all other players
                sendToAllPlayers(playerInfo);
            } catch (IOException ignored) {
            }
        }
        // Set finished state
        serverState = State.FINISHED;

        // Close everything
        gameServerSocket.close();

    }

    public State getServerState() {
        return serverState;
    }
}
