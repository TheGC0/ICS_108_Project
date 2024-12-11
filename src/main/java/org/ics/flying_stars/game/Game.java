package org.ics.flying_stars.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.ics.flying_stars.engine.GameLoop;
import org.ics.flying_stars.engine.canvas.Colored;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.collision.CollisionTranscript;
import org.ics.flying_stars.engine.collision.colliders.PolygonCollider;
import org.ics.flying_stars.engine.geometry.Polygon;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.entities.FlyingObstacle;
import org.ics.flying_stars.game.entities.Player;
import org.ics.flying_stars.game.factories.StarFactory;
import org.ics.flying_stars.settings.Difficulty;
import org.ics.flying_stars.settings.Settings;
import org.ics.flying_stars.ui.AbstractUI;
import org.ics.flying_stars.ui.LosingScreenUI;
import org.ics.flying_stars.ui.PauseMenuUI;

import java.util.HashMap;


public class Game {
    private final Settings settings;
    private final StackPane rootStackPane;
    private final Canvas canvas;
    private final PauseMenuUI pauseMenu;
    private final LosingScreenUI loseMenu;
    private final HealthBar healthBar;
    private final Score score;
    private GameLoop gameLoop;
    private final HashMap<FlyingObstacle, Double> starsTimer;

    private final Player player;
    private final PolygonCollider bounds;

    public Game(Settings settings, Canvas canvas) {
        this.settings = settings;
        this.canvas = canvas;

        starsTimer = new HashMap<>();
        score = new Score();
        rootStackPane = new StackPane();
        pauseMenu = new PauseMenuUI();
        loseMenu = new LosingScreenUI();
        healthBar = new HealthBar();
        player = new Player(new Vector2D(200, 200), Colour.RED);
        player.addCollisionHandler(this::playerCollisionHandler);
        canvas.addEventHandler(MouseEvent.ANY, event -> player.setMousePos(event.getX(), event.getY()));

        bounds = new PolygonCollider(new Polygon(
                new Vector2D[] {
                        new Vector2D(-150, -150),
                        new Vector2D(canvas.getWidth() + 150, -150),
                        new Vector2D(canvas.getWidth() + 150, canvas.getHeight() + 150),
                        new Vector2D(-150, canvas.getHeight() + 150),
                }
        ));
        // TODO Connect buttons of menus here
        loseMenu.tryingButton().setOnAction(event -> start());
        pauseMenu.restartButton().setOnAction(event -> start());
        pauseMenu.resumeButton().setOnAction(event -> {
            gameLoop.start();
            removeMenu(pauseMenu);
        });

        // Set backgrounds

        // Add canvas, score, and health bar
        VBox vBox = new VBox();
        vBox.getChildren().addAll(score.getRoot(),healthBar.getRoot());
        rootStackPane.setBackground(Background.fill(Color.BLACK));
        rootStackPane.getChildren().addAll(vBox, canvas);
    }

    private void showMenu(AbstractUI menu) {
        rootStackPane.getChildren().add(menu.getRoot());
    }

    private void removeMenu(AbstractUI menu) {
        rootStackPane.getChildren().remove(menu.getRoot());
    }

    private void pauseEvent(KeyEvent event) {
        if (event.getCharacter().equals("p")) {
            if (gameLoop.status() == Animation.Status.RUNNING) {
                gameLoop.pause();
                showMenu(pauseMenu);

            } else if (gameLoop.status() == Animation.Status.PAUSED) {
                gameLoop.start();
                removeMenu(pauseMenu);
            }
        }
    }

    private void playerCollisionHandler(CollisionTranscript collisionTranscript) {
        if (collisionTranscript.getLinkedTranscript().getHead() instanceof FlyingObstacle flyingObstacle) {
            Colour edgeColor = ((Colored) collisionTranscript.getLinkedTranscript().getOrigin()).getColor();
            if (player.getColor() == edgeColor) {
                score.hit((double) System.currentTimeMillis() - starsTimer.get(flyingObstacle));
                player.setColor(Colour.getShuffled()[0]);
                gameLoop.removeSprite(flyingObstacle);

            } else {
                healthBar.takeDamage();
                score.miss((double) System.currentTimeMillis() - starsTimer.get(flyingObstacle));
                gameLoop.removeSprite(flyingObstacle);
            }

            // Check loss condition
            if(healthBar.getHealth() == 0){
                gameLoop.stop();
                showMenu(loseMenu);
            }
        }
    }

    private void boundsCollisionHandler(CollisionTranscript collisionTranscript) {
        if (collisionTranscript.getLinkedTranscript().getHead() instanceof FlyingObstacle flyingObstacle) {
            gameLoop.removeSprite(flyingObstacle);
            score.miss((double) System.currentTimeMillis() - starsTimer.get(flyingObstacle));

        }
    }

    public void start() {
        removeMenu(loseMenu);
        removeMenu(pauseMenu);
        gameLoop = new GameLoop(60, canvas);

        healthBar.startANewLife();
        score.reset();
        gameLoop.addSprite(player);

        gameLoop.getCollidables().add(bounds);
        bounds.addCollisionHandler(this::boundsCollisionHandler);



        // Create a star spawner
        StarFactory starFactory = new StarFactory(new Vector2D((double) 720 /2, (double) 720 /2));
        Difficulty difficulty = Difficulty.EASY;
        Timeline spawner = new Timeline(
                new KeyFrame(Duration.seconds(difficulty.getDifficultyLevel()), event -> {
                    FlyingObstacle flyingObstacle = starFactory.create(Math.random() * Math.PI / 3, 100 * difficulty.getDifficultyLevel());
                    gameLoop.addSprite(flyingObstacle);
                    starsTimer.put(flyingObstacle, (double) System.currentTimeMillis());
                })
        );

        spawner.setCycleCount(Timeline.INDEFINITE);

        gameLoop.getAttachedLoops().add(spawner);
        gameLoop.start();

    }

    public Settings getSettings() {
        return settings;
    }

    public StackPane getRoot() {
        return rootStackPane;
    }

    public Button backToMainMenuPauseButton() {
        return pauseMenu.backToMainMenuButton();
    }

    public Button backToMainMenuLoseButton() {
        return loseMenu.backToMainMenuButton();
    }
}
