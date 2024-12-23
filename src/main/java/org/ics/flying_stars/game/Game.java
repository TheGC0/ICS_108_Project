package org.ics.flying_stars.game;

import javafx.scene.Parent;
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
import org.ics.flying_stars.game.factories.ObstacleFactory;
import org.ics.flying_stars.game.factories.RandomPolygonFactory;
import org.ics.flying_stars.game.factories.SquareFactory;
import org.ics.flying_stars.game.factories.StarFactory;
import org.ics.flying_stars.game.factories.TriangleFactory;
import org.ics.flying_stars.settings.Settings;
import org.ics.flying_stars.ui.AbstractMenu;
import org.ics.flying_stars.ui.LosingScreenMenu;
import org.ics.flying_stars.ui.PauseMenu;
import org.ics.flying_stars.ui.UI;

import java.util.HashMap;


// Game class that handles game logic (Implements UI since it technically has children UIs)
public class Game implements UI {
    private static final int BOUNDS_OFFSET = 100;
    private final Settings settings;
    private final StackPane rootStackPane;
    private final Canvas canvas;
    private final PauseMenu pauseMenu;
    private final LosingScreenMenu loseMenu;
    private final HealthBar healthBar;
    private final Score score;
    private final HashMap<FlyingObstacle, Double> obstacleCreationTimes;

    private final Player player;
    private final PolygonCollider bounds;

    private GameLoop gameLoop;

    public Game(Settings settings, Canvas canvas) {
        this.settings = settings;
        this.canvas = canvas;

        // Create a hashmap to store creation time of each obstacle to use in calculating reaction time
        obstacleCreationTimes = new HashMap<>();

        // Create root layout
        rootStackPane = new StackPane();

        // Create pause and lose menu
        pauseMenu = new PauseMenu();  // Pause menu is unused!
        loseMenu = new LosingScreenMenu();

        // Create score and health bar
        score = new Score();
        healthBar = new HealthBar();

        // Create player at 200, 200 with an initial Color of red
        player = new Player(new Vector2D(200, 200), Colour.RED);

        // Connect the collision handler
        player.addCollisionHandler(this::playerCollisionHandler);

        // Connect mouse events to player (setting current mouse pos in player for the player to use in movement calculations)
        canvas.addEventHandler(MouseEvent.ANY, event -> player.setMousePos(event.getX(), event.getY()));

        // Create the bounds for obstacles to detect and get removed
        bounds = createBounds(canvas);

        // Connect the collision handler
        bounds.addCollisionHandler(this::boundsCollisionHandler);

        // Connect each button in pause and lose menu
        loseMenu.tryingButton().setOnAction(event -> start());
        pauseMenu.restartButton().setOnAction(event -> start());
        pauseMenu.resumeButton().setOnAction(event -> {
            gameLoop.start();
            removeMenu(pauseMenu);
        });

        // Add canvas, score, and health bar
        VBox vBox = new VBox();
        vBox.getChildren().addAll(score.getRoot(),healthBar.getRoot());
        rootStackPane.setBackground(Background.fill(Color.BLACK));
        rootStackPane.getChildren().addAll(vBox, canvas);
    }

    // Helper method to create bounds based on the canvas dimensions and BOUNDS_OFFSET
    private PolygonCollider createBounds(Canvas canvas) {
        return new PolygonCollider(new Polygon(
                new Vector2D[] {
                        new Vector2D(-BOUNDS_OFFSET, -BOUNDS_OFFSET),
                        new Vector2D(canvas.getWidth() + BOUNDS_OFFSET, -BOUNDS_OFFSET),
                        new Vector2D(canvas.getWidth() + BOUNDS_OFFSET, canvas.getHeight() + BOUNDS_OFFSET),
                        new Vector2D(-BOUNDS_OFFSET, canvas.getHeight() + BOUNDS_OFFSET),
                }
        ));
    }

    // Helper method to show a menu above the game canvas
    private void showMenu(AbstractMenu menu) {
        rootStackPane.getChildren().add(menu.getRoot());
    }

    // Helper method to remove a menu from the canvas
    private void removeMenu(AbstractMenu menu) {
        rootStackPane.getChildren().remove(menu.getRoot());
    }

    // Unused pause event handler
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

    // Method for the game to handle detected collisions from the Player object
    private void playerCollisionHandler(CollisionTranscript collisionTranscript) {
        // Ignore collisions if player is invincible
        if(player.isInvincible()) {
            return;
        }
        // Check if collision was with a flying obstacle
        if (collisionTranscript.getLinkedTranscript().getHead() instanceof FlyingObstacle flyingObstacle) {
            // Get the color of the specific edge that the collision originated from
            Colour edgeColor = ((Colored) collisionTranscript.getLinkedTranscript().getOrigin()).getColor();

            // Correct hit
            if (player.getColor() == edgeColor) {
                // Get obstacle creation time
                if(obstacleCreationTimes.containsKey(flyingObstacle)) {
                    // Update score
                    score.hit((double) System.currentTimeMillis() - obstacleCreationTimes.get(flyingObstacle));
                    obstacleCreationTimes.remove(flyingObstacle);
                }

                // Set the player's color to a new random color
                player.setColor(Colour.getShuffled()[0]);

            } else {
                // Take damage
                healthBar.takeDamage();

                // Turn on invincibility for player
                player.getHit();

                // Get obstacle creation time
                if(obstacleCreationTimes.containsKey(flyingObstacle)) {
                    // Update score
                    score.miss((double) System.currentTimeMillis() - obstacleCreationTimes.get(flyingObstacle));
                    obstacleCreationTimes.remove(flyingObstacle);
                }
            }

            // Remove the obstacle
            gameLoop.removeSprite(flyingObstacle);

        }
        // Check loss condition
        if(healthBar.getHealth() == 0){
            gameLoop.stop();
            showMenu(loseMenu);
        }
    }

    // Method for the game to handle detected collisions from the Bounds
    private void boundsCollisionHandler(CollisionTranscript collisionTranscript) {
        // Check if the other collider is a FlyingObstacle
        if (collisionTranscript.getLinkedTranscript().getHead() instanceof FlyingObstacle flyingObstacle) {
            // Remove obstacle
            gameLoop.removeSprite(flyingObstacle);

            if(obstacleCreationTimes.containsKey(flyingObstacle)) {
                // Add miss
                score.miss((double) System.currentTimeMillis() - obstacleCreationTimes.get(flyingObstacle));
                // Remove from map
                obstacleCreationTimes.remove(flyingObstacle); }
        }
    }

    // Helper method to create a Timeline (loop) to spawn obstacles using an obstacle factory
    private Timeline createObstacleSpawner(ObstacleFactory obstacleFactory) {
        double difficultyLevel = settings.getDifficulty().getDifficultyLevel();
        Timeline spawner = new Timeline(
                // Repeat depending on the difficulty level
                new KeyFrame(Duration.seconds(difficultyLevel), event -> {
                    // Create a flying obstacle
                    FlyingObstacle flyingObstacle = obstacleFactory.create(Math.random() * Math.PI / 3, 75 * difficultyLevel);

                    // Add it to the game loop
                    gameLoop.addSprite(flyingObstacle);

                    // Record the obstacle creation time
                    obstacleCreationTimes.put(flyingObstacle, (double) System.currentTimeMillis());
                }));

        // Set to repeat forever (until stopped)
        spawner.setCycleCount(Timeline.INDEFINITE);
        return spawner;
    }

    // Helper method to create and return an obstacle factory based on the shape settings
    private ObstacleFactory getObstacleFactory() {
        // Determine center for the factories
        Vector2D center = new Vector2D(canvas.getWidth()/2, canvas.getHeight()/2);

        // Create an obstacle factory depending on the shape in the settings
        ObstacleFactory obstacleFactory;
        obstacleFactory = switch (settings.getShape()) {
            case Square -> new SquareFactory(center);
            case Triangle -> new TriangleFactory(center);
            case RandomPolygon -> new RandomPolygonFactory(center);
            case Star -> new StarFactory(center);
            case null -> new StarFactory(center);
        };
        return obstacleFactory;
    }
    public void start() {
        // Remove any possible stray menus
        removeMenu(loseMenu);
        removeMenu(pauseMenu);

        // Reset health and score
        healthBar.startANewLife();
        score.reset();

        // Create a new game loop
        gameLoop = new GameLoop(60, canvas);

        // Add the player and bounds
        gameLoop.addSprite(player);
        gameLoop.getCollidables().add(bounds);

        // Get a flying obstacle factory based on the shape settings
        ObstacleFactory obstacleFactory = getObstacleFactory();

        // Create an obstacle spawner loop and attach it to the game loop
        gameLoop.getAttachedLoops().add(createObstacleSpawner(obstacleFactory));

        // Start the game loop
        gameLoop.start();
    }

    // Getter for the game settings
    public Settings getSettings() {
        return settings;
    }

    // Unused button
    public Button backToMainMenuPauseButton() {
        return pauseMenu.backToMainMenuButton();
    }

    // Button that returns to main menu in the losing screen
    public Button backToMainMenuLoseButton() {
        return loseMenu.backToMainMenuButton();
    }

    @Override
    public Parent getRoot() {
        return rootStackPane;
    }
}
