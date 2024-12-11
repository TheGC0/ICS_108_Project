package org.ics.flying_stars.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.ics.flying_stars.game.Game;
import org.ics.flying_stars.settings.Settings;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas();
        canvas.setWidth(720);
        canvas.setHeight(720);
//        pane.setBackground(Background.fill(Color.BLACK));

        Game game = new Game(new Settings(), canvas);

        stage.setScene(new Scene(game.getRoot()));
        stage.setWidth(720);
        stage.setHeight(720);

        stage.show();
        game.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
