package org.ics.flying_stars.tests;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.ics.flying_stars.game.Game;
import org.ics.flying_stars.game.HealthBar;
import org.ics.flying_stars.settings.Settings;
import org.ics.flying_stars.ui.LosingScreenUI;

public class TestingOmar2 extends Application {

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
