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
import org.ics.flying_stars.game.geometry.Circle;
import org.ics.flying_stars.game.geometry.Point;
import org.ics.flying_stars.game.geometry.Vector2D;
import org.ics.flying_stars.game.sprites.Sprite;

public class TestingOmar extends Application {

    public static class CircleSprite extends Sprite {
        private final DrawableCircle circle;
        private Vector2D velocity = new Vector2D(0, 0);
        private Point mousePosition;
        public CircleSprite(DrawableCircle circle) {
            super(circle, new CircleCollider(circle));
            this.circle = circle;
        }

        @Override
        public void move(int physicsFrames) {
            Vector2D vector = circle.getCenter().getUnitVectorFrom(mousePosition);
            double scale = mousePosition.distanceFrom(circle.getCenter()) / physicsFrames * 5;
            vector.scale(scale);
            setVelocity(vector);
            circle.getCenter().setXY(velocity.getX() + circle.getX(), (velocity.getY() + circle.getY()));
        }
        public void setVelocity(Vector2D velocity) {
            this.velocity = velocity;
        }

        public void setMousePosition(Point mousePosition) {
            this.mousePosition = mousePosition;
        }
    }

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas();
        canvas.setWidth(1000);
        canvas.setHeight(1000);
        Pane pane = new Pane();
        pane.setMinSize(300, 300);
        pane.getChildren().add(canvas);

        GameLoop gameLoop = new GameLoop(600, canvas);


        DrawableCircle playerCircle = new DrawableCircle(10, new Point(100,100), true);
        CircleSprite playerSprite = new CircleSprite(playerCircle);

        gameLoop.getDrawables().add(playerSprite);
        gameLoop.getCollidables().add(playerSprite);
        gameLoop.getMovables().add(playerSprite);

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
        stage.setWidth(1000);
        stage.setHeight(1000);
        stage.show();

        pane.addEventHandler(MouseEvent.ANY, event -> {

            Point mousePoint = new Point(event.getX(), event.getY());
            playerSprite.setMousePosition(mousePoint);
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
