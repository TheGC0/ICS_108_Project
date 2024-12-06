package org.ics.flying_stars.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.ics.flying_stars.game.canvas.DrawableCircle;
import org.ics.flying_stars.game.gameloop.GameLoop;
import org.ics.flying_stars.game.geometry.Circle;
import org.ics.flying_stars.game.geometry.Point;

public class TestingOmar extends Application {
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas();
        canvas.setWidth(300);
        canvas.setHeight(300);
        Pane pane = new Pane();
        pane.setMinSize(300, 300);
        pane.getChildren().add(canvas);

        GameLoop gameLoop = new GameLoop(60, canvas);

        DrawableCircle circle = new DrawableCircle(new Circle(10, new Point(50, 70)), true);
        gameLoop.getDrawables().add(circle);

        stage.setScene(new Scene(pane));
        stage.setWidth(300);
        stage.setHeight(300);
        stage.show();

        pane.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            circle.circle().setX((int) event.getX());
            circle.circle().setY((int) event.getY());
        });
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
