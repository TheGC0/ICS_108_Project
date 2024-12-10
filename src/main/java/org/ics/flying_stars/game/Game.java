package org.ics.flying_stars.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.ics.flying_stars.engine.GameLoop;
import org.ics.flying_stars.engine.canvas.Colored;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.collision.colliders.PolygonCollider;
import org.ics.flying_stars.engine.geometry.Point;
import org.ics.flying_stars.engine.geometry.Polygon;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.entities.Player;
import org.ics.flying_stars.game.entities.Star;
import org.ics.flying_stars.game.factories.Factory;
import org.ics.flying_stars.game.factories.StarFactory;
import org.ics.flying_stars.settings.Settings;

public class Game {
    private final Settings settings;

    public Game(Settings settings) {
        this.settings = settings;
    }


    public void start(Canvas canvas) {
        GameLoop gameLoop = new GameLoop(60, canvas);

        // Create player and connect to mouse
        Player player = new Player(new Point(200, 200), Colour.RED);
        gameLoop.addSprite(player);
        canvas.addEventHandler(MouseEvent.ANY, event -> {
            player.setMousePos(event.getX(), event.getY());
        });
        player.addCollisionHandler(collisionTranscript -> {
            if (collisionTranscript.getLinkedTranscript().getHead() instanceof Star star) {
                Colour edgeColor = ((Colored) collisionTranscript.getLinkedTranscript().getOrigin()).getColor();
                if (player.getColor() == edgeColor) {
                    System.out.println("GOOOD!");
                } else {
                    System.out.println("BADD!");
                }
            }
        });

        // Create bounds
        PolygonCollider bounds = new PolygonCollider(new Polygon(
                new Point[] {
                        new Point(0, 0),
                        new Point(canvas.getWidth(), 0),
                        new Point(canvas.getWidth(), canvas.getHeight()),
                        new Point(0, canvas.getHeight()),
                }
        ));
        gameLoop.getCollidables().add(bounds);
        bounds.addCollisionHandler(collisionTranscript -> {
//            System.out.println("Bound collision" + collisionTranscript.getLinkedTranscript().getHead().getClass());
            if (collisionTranscript.getLinkedTranscript().getHead() instanceof Star star) {
                gameLoop.removeSprite(star);
            }
        });

        // Create a star spawner
        Factory<Star> starFactory = new StarFactory();
        Timeline spawner = new Timeline(
                new KeyFrame(Duration.seconds(3), event -> {
                    System.out.println("Spawning star");
                    Star star = starFactory.create();

                    Vector2D[] velocities = new Vector2D[10];
                    Vector2D randomVelocity = new Vector2D(Math.random(), Math.random());
                    randomVelocity.scale(90);
                    for (int i=0; i<10; i++) {
                        velocities[i] = randomVelocity;
                    }

                    star.setVelocities(velocities);

                    gameLoop.addSprite(star);
                })
        );

        spawner.setCycleCount(Timeline.INDEFINITE);

        gameLoop.start();
        spawner.play();


    }

    public Settings getSettings() {
        return settings;
    }
}
