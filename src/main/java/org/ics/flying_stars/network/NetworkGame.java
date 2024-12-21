package org.ics.flying_stars.network;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.Game;
import org.ics.flying_stars.game.entities.FlyingObstacle;
import org.ics.flying_stars.game.entities.Player;
import org.ics.flying_stars.game.factories.ObstacleFactory;
import org.ics.flying_stars.settings.Settings;

import java.net.InetSocketAddress;
import java.net.SocketException;

public class NetworkGame extends Game {
    private final NetworkGameClient networkGameClient;

    public NetworkGame(Settings settings, Canvas canvas, String host) throws SocketException {
        // TODO remove default settings limitation
        super(new Settings(), canvas);

        networkGameClient = new NetworkGameClient(new InetSocketAddress(host, NetworkGameServer.PORT));
        networkGameClient.start();

        canvas.addEventHandler(MouseEvent.ANY, event -> networkGameClient.setCurrentPos(new Vector2D(event.getX(), event.getY())));
    }

    public NetworkGameClient.State state() {
        return networkGameClient.getClientState();
    }

    public int getPlayerNum() {
        return networkGameClient.getPlayerNum();
    }

    private Timeline createPlayerUpdateLoop() {
        Timeline updatePlayers = new Timeline(
                // Repeat 30 times each second
                new KeyFrame(Duration.seconds(1/30.0), event -> {
                    // Update current color
                    networkGameClient.setCurrentColor(players.get(0).getColor());

                    for (int playerNum: networkGameClient.playerPositionsMap.keySet()) {
                        Player player = players.get(playerNum);
                        if (player == null) {
                            player = createPlayer(playerNum);
                            gameLoop.addSprite(player);
                        }
                        // Update player pos
                        Vector2D pos = networkGameClient.playerPositionsMap.get(playerNum);
                        player.setMousePos(pos.getX(), pos.getY());

                        // Update player color
                        Colour color = networkGameClient.playerColoursMap.get(playerNum);
                        player.setColor(color);
                    }
                }));

        updatePlayers.setCycleCount(Animation.INDEFINITE);
        return updatePlayers;
    }

    @Override
    protected Timeline createObstacleSpawner(ObstacleFactory obstacleFactory) {
        Timeline spawner = new Timeline(
                // Repeat 30 times each second
                new KeyFrame(Duration.seconds(1/30.0), event -> {
                    // Check if there is an obstacle to be made
                    NetworkGameClient.ObstacleCreationInfo obstacleCreationInfo = networkGameClient.obstacleCreationInfoQueue.poll();

                    if (obstacleCreationInfo == null) {
                        return;
                    }

                    // Create a flying obstacle
                    FlyingObstacle flyingObstacle = obstacleFactory.create(obstacleCreationInfo.angle(), 75 * getSettings().getDifficulty().getDifficultyLevel(), obstacleCreationInfo.colors());

                    // Add it to the game loop
                    gameLoop.addSprite(flyingObstacle);

                    // Record the obstacle creation time
                    obstacleCreationTimes.put(flyingObstacle, (double) System.currentTimeMillis());
                }));

        // Set to repeat forever (until stopped)
        spawner.setCycleCount(Timeline.INDEFINITE);

        return spawner;
    }

    @Override
    public void start() {
        super.start();
        gameLoop.pause();
        gameLoop.getAttachedLoops().add(createPlayerUpdateLoop());
        gameLoop.start();
    }

    public void stopNetworkGame() {
        networkGameClient.stopClient();
    }

}
