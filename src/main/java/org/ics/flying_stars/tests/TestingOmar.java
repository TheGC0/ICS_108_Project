package org.ics.flying_stars.tests;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.ics.flying_stars.game.engine.canvas.Colour;
import org.ics.flying_stars.game.engine.canvas.samples.DrawableCircle;
import org.ics.flying_stars.game.engine.canvas.samples.DrawableLine;
import org.ics.flying_stars.game.engine.collision.colliders.CircleCollider;
import org.ics.flying_stars.game.engine.collision.colliders.LineCollider;
import org.ics.flying_stars.game.engine.GameLoop;
import org.ics.flying_stars.game.engine.geometry.Point;
import org.ics.flying_stars.game.engine.geometry.Vector2D;
import org.ics.flying_stars.game.engine.sprites.Sprite;
import org.ics.flying_stars.game.entities.Player;
import org.ics.flying_stars.game.entities.Star;
import org.ics.flying_stars.game.factories.StarFactory;

public class TestingOmar extends Application {

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas();
        canvas.setWidth(500);
        canvas.setHeight(500);
        Pane pane = new Pane();
        pane.setMinSize(300, 300);
        pane.getChildren().add(canvas);

        GameLoop gameLoop = new GameLoop(60, canvas);


        Player playerSprite = new Player(new Point(10,10), Colour.YELLOW);

        gameLoop.getDrawables().add(playerSprite);
        gameLoop.getCollidables().add(playerSprite);
        gameLoop.getMovables().add(playerSprite);

        DrawableLine wallLine = new DrawableLine(new Point(50, 50), new Point(100, 100));
        LineCollider wallCollider = new LineCollider(wallLine);

        gameLoop.getDrawables().add(wallLine);
        gameLoop.getCollidables().add(wallCollider);

        StarFactory starFactory = new StarFactory();

        Star star = new Star(new Colour[]{
                Colour.RED,
                Colour.BLACK,
                Colour.GREEN,
                Colour.RED,
                Colour.RED,
                Colour.BLUE,
                Colour.RED,
                Colour.RED,
                Colour.BROWN,
                Colour.RED,
        },
                new Point[]{
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
//        PolygonCollider starCollider = new PolygonCollider(star);

        star.setVelocities(
                new Vector2D[]{
                        new Vector2D(50, 50),
                        new Vector2D(100, 100),
                        new Vector2D(150, 150),
                        new Vector2D(150+10, 150+40),
                        new Vector2D(150+10, 150+10),
                        new Vector2D(150+20, 150+40),
                        new Vector2D(150+10, 150+10),
                        new Vector2D(150+20, 150+40),
                        new Vector2D(150+10, 150+10),
                        new Vector2D(150+20, 150+40),
                        new Vector2D(150+10, 150+10),
                        new Vector2D(150+20, 150+40),

                }
        );

        gameLoop.getDrawables().add(star);
        gameLoop.getCollidables().add(star);
        gameLoop.getMovables().add(star);

        stage.setScene(new Scene(pane));
        stage.setWidth(500);
        stage.setHeight(500);
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

    }

    public static void main(String[] args) {
        launch();
    }
}
