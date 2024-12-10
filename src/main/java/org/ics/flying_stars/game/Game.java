package org.ics.flying_stars.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.ics.flying_stars.engine.GameLoop;
import org.ics.flying_stars.engine.canvas.Colored;
import org.ics.flying_stars.engine.canvas.ColoredCircle;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.canvas.samples.DrawablePolygon;
import org.ics.flying_stars.engine.collision.colliders.PolygonCollider;
import org.ics.flying_stars.engine.geometry.Polygon;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.entities.Player;
import org.ics.flying_stars.game.entities.FlyingStar;
import org.ics.flying_stars.game.factories.StarFactory;
import org.ics.flying_stars.settings.Difficulty;
import org.ics.flying_stars.settings.Settings;
import org.ics.flying_stars.ui.LosingScreenUI;

public class Game {
    private final Settings settings;
    private GameLoop gameLoop;

    public Game(Settings settings) {
        this.settings = settings;
    }


    public void start(Canvas canvas) {
        gameLoop = new GameLoop(60, canvas);
        canvas.getScene().addEventHandler(KeyEvent.KEY_TYPED, event -> {
            System.out.println(event.getCharacter());
            if (event.getCharacter().equals("p")) {
                if (gameLoop.status() == Animation.Status.RUNNING) {
                    gameLoop.pause();

                } else if (gameLoop.status() == Animation.Status.PAUSED) {
                    gameLoop.start();
                }
            }
        });
        HealthBar healthBar = new HealthBar();
        gameLoop.getDrawables().add(healthBar);

        LosingScreenUI losingScreenUI = new LosingScreenUI();

        // Create player and connect to mouse
        Player player = new Player(new Vector2D(200, 200), Colour.RED);
        gameLoop.addSprite(player);
        canvas.addEventHandler(MouseEvent.ANY, event -> player.setMousePos(event.getX(), event.getY()));
        player.addCollisionHandler(collisionTranscript -> {
            if (collisionTranscript.getLinkedTranscript().getHead() instanceof FlyingStar flyingStar) {
                Colour edgeColor = ((Colored) collisionTranscript.getLinkedTranscript().getOrigin()).getColor();
                if (player.getColor() == edgeColor) {
                    player.setColor(Colour.getShuffled()[0]);
                    gameLoop.removeSprite(flyingStar);

                } else {
                    healthBar.takeDamage();
                    gameLoop.removeSprite(flyingStar);
                }
            }
        });

        // Create bounds
        PolygonCollider bounds = new PolygonCollider(new Polygon(
                new Vector2D[] {
                        new Vector2D(-150, -150),
                        new Vector2D(canvas.getWidth() + 150, -150),
                        new Vector2D(canvas.getWidth() + 150, canvas.getHeight() + 150),
                        new Vector2D(-150, canvas.getHeight() + 150),
                }
        ));
        gameLoop.getCollidables().add(bounds);
        bounds.addCollisionHandler(collisionTranscript -> {
//            System.out.println("Bound collision" + collisionTranscript.getLinkedTranscript().getHead().getClass());
            if (collisionTranscript.getLinkedTranscript().getHead() instanceof FlyingStar flyingStar) {
                gameLoop.removeSprite(flyingStar);
            }
        });



        // Create a star spawner
        StarFactory starFactory = new StarFactory(new Vector2D((double) 720 /2, (double) 720 /2));
        Difficulty difficulty = Difficulty.EXTREME;
        Timeline spawner = new Timeline(
                new KeyFrame(Duration.seconds(difficulty.getDifficultyLevel()), event -> {
                    System.out.println("Spawning star");
                    FlyingStar flyingStar = starFactory.create(Math.random() * Math.PI / 3, 100 * difficulty.getDifficultyLevel());
                    gameLoop.addSprite(flyingStar);

                    if(healthBar.getHealth() == 0){
                        gameLoop.stop();
                        canvas.getScene().setRoot(losingScreenUI.getRoot());
                        losingScreenUI.tryingButton().setOnAction(event1 ->{
                            canvas.getScene();
                        });
                    }
                })
        );

        spawner.setCycleCount(Timeline.INDEFINITE);

        gameLoop.getAttachedLoops().add(spawner);
        gameLoop.start();

    }

    public Settings getSettings() {
        return settings;
    }

    public Animation.Status getStatus() {
        return gameLoop.status();
    }
}
