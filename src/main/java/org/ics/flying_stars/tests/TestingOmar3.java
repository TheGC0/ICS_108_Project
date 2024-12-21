package org.ics.flying_stars.tests;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.ics.flying_stars.network.NetworkGame;
import org.ics.flying_stars.network.NetworkGameClient;
import org.ics.flying_stars.network.NetworkGameServer;
import org.ics.flying_stars.settings.Settings;

import java.net.SocketException;

public class TestingOmar3 extends Application {

    @Override
    public void start(Stage stage) throws SocketException {
        Canvas canvas = new Canvas();
        canvas.setWidth(850);
        canvas.setHeight(850);
//        pane.setBackground(Background.fill(Color.BLACK));
//        NetworkGameServer gameServer = new NetworkGameServer(new Settings());
//        gameServer.start();
        NetworkGame game = new NetworkGame(new Settings(), canvas, "10.83.142.43");

        stage.setScene(new Scene(game.getRoot()));
        stage.setWidth(720);
        stage.setHeight(720);

        stage.show();

        //
        final boolean[] flag = {false};
        Timeline observeNetworkGameState = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    System.out.println(game.state());
                    System.out.println(game.getPlayerNum());
                    if (!flag[0] && game.state() == NetworkGameClient.State.ONGOING) {
                        game.start();
                        flag[0] = true;
                    }
                })
        );
        observeNetworkGameState.setCycleCount(20);
        observeNetworkGameState.play();
    }

    public static void main(String[] args) {
        launch();
    }
}
