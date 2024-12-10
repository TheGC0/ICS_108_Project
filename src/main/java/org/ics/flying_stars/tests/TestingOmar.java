package org.ics.flying_stars.tests;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.GameLoop;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.entities.Player;
import org.ics.flying_stars.game.entities.Star;
import org.ics.flying_stars.game.factories.StarFactory;

public class TestingOmar extends Application {

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


        GameLoop gameLoop = new GameLoop(60, canvas);



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
                starFactory.getVertices()
        );

        Player playerSprite = new Player(new Vector2D(10,10), Colour.YELLOW);
        playerSprite.addCollisionHandler(collisionTranscript -> {

        });


        gameLoop.addSprite(playerSprite);
        gameLoop.addSprite(star);


        pane.addEventHandler(MouseEvent.ANY, event -> playerSprite.setMousePos(event.getX(), event.getY()));

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
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
