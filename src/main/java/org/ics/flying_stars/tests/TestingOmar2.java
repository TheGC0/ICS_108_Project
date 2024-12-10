package org.ics.flying_stars.tests;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.ics.flying_stars.engine.GameLoop;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.geometry.Point;
import org.ics.flying_stars.game.Game;
import org.ics.flying_stars.game.entities.Player;
import org.ics.flying_stars.game.entities.Star;
import org.ics.flying_stars.game.factories.StarFactory;
import org.ics.flying_stars.settings.Settings;

public class TestingOmar2 extends Application {

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas();
        canvas.setWidth(1280);
        canvas.setHeight(720);
        Pane pane = new Pane();
        pane.setMinSize(300, 300);
        pane.getChildren().add(canvas);
        stage.setScene(new Scene(pane));
        stage.setWidth(1280);
        stage.setHeight(720);

        Game game = new Game(new Settings());

        game.start(canvas);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
