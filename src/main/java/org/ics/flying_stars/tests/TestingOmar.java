package org.ics.flying_stars.tests;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.ics.flying_stars.game.canvas.DrawableCircle;
import org.ics.flying_stars.game.canvas.DrawableLine;
import org.ics.flying_stars.game.canvas.DrawablePolygon;
import org.ics.flying_stars.game.collision.CircleCollider;
import org.ics.flying_stars.game.collision.LineCollider;
import org.ics.flying_stars.game.collision.PolygonCollider;
import org.ics.flying_stars.game.gameloop.GameLoop;
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

        GameLoop gameLoop = new GameLoop(600, canvas);

        DrawableCircle playerCircle = new DrawableCircle(10, new Point(100,100), true);
        CircleCollider playerCollider = new CircleCollider(playerCircle);

        gameLoop.getDrawables().add(playerCircle);
        gameLoop.getCollidables().add(playerCollider);

        DrawableLine wallLine = new DrawableLine(new Point(50, 50), new Point(100, 100));
        LineCollider wallCollider = new LineCollider(wallLine);

        gameLoop.getDrawables().add(wallLine);
        gameLoop.getCollidables().add(wallCollider);

        DrawablePolygon star = new DrawablePolygon(new Point[]{
                new Point(150, 150-60),
                new Point(150+10, 150-30),
                new Point(150+40, 150-30),
                new Point(150+10, 150+10),
                new Point(150+20, 150+40),
                new Point(150, 150+10),
                new Point(150-20, 150+40),
                new Point(150-10, 150+10),
                new Point(150-40, 150-30),
                new Point(150-10, 150-30),
        }
        );
        PolygonCollider starCollider = new PolygonCollider(star);


        gameLoop.getDrawables().add(star);
        gameLoop.getCollidables().add(starCollider);

        stage.setScene(new Scene(pane));
        stage.setWidth(300);
        stage.setHeight(300);
        stage.show();

        pane.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            playerCircle.getCenter().setXY((int) event.getX(), (int) event.getY());
        });

        // Test pausing
        pane.getScene().addEventHandler(KeyEvent.KEY_TYPED, event -> {
            System.out.println("key event");
            System.out.println(event.getCharacter());
            if (event.getCharacter().equals("p")) {
                if (gameLoop.status() == Animation.Status.RUNNING) {
                    gameLoop.pause();
                } else if (gameLoop.status() == Animation.Status.PAUSED) {
                    gameLoop.start();
                }
            }
        });
        gameLoop.start();

        // Test wall movement
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()

        {

            public void handle(long currentNanoTime)

            {

                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 1 * Math.cos(t);
                double x2 = 50 * Math.cos(t);

                double y = 1 * Math.sin(t);
                double y2 = 50 * Math.sin(t);

                for (Point point: star.getVertices()) {
                    point.setXY((int) (point.getX() - x), (int) (point.getY() - y));
                }
//                star.getVertices()[0].setXY((int) x, (int) y);


            }

        }.start();

    }

    public static void main(String[] args) {
        launch();
    }
}
