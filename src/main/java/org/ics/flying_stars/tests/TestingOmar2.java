package org.ics.flying_stars.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.ics.flying_stars.game.Game;
import org.ics.flying_stars.settings.Settings;

public class TestingOmar2 extends Application {

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas();
        canvas.setWidth(720);
        canvas.setHeight(720);
        Pane pane = new Pane();
        pane.setMinSize(300, 300);
        pane.getChildren().add(canvas);
        stage.setScene(new Scene(pane));
        stage.setWidth(720);
        stage.setHeight(720);
        pane.setBackground(Background.fill(Color.BLACK));

        Game game = new Game(new Settings());

        game.start(canvas);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
