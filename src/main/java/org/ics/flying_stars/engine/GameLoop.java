package org.ics.flying_stars.engine;


import org.ics.flying_stars.engine.collision.Collidable;
import org.ics.flying_stars.engine.canvas.Drawable;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import org.ics.flying_stars.engine.sprites.Movable;
import org.ics.flying_stars.engine.sprites.Sprite;

import java.util.ArrayList;

/**
 * The GameLoop class
 * <p>
 * This class takes a Canvas and handles drawing and collision detecting
 * <p>
 * To add an object to draw, add the object to the drawables arraylist
 * <p>
 * To add an object to detect collisions for, add the object to the collidables arraylist
 */
public final class GameLoop {
    private final int PHYSICS_LOOP_FRAMES = 60;

    private final Canvas gameCanvas;
    private final ArrayList<Collidable> collidables;
    private final ArrayList<Drawable> drawables;
    private final ArrayList<Movable> movables;
    private final Timeline gameLoop;
    private final Timeline physicsLoop;

    private final ArrayList<Timeline> attachedLoops;

    private int frames;

    /**
     * @param framesPerSecond The number of frames to draw each second (This is also for the collision detecting loop)
     * @param canvas The canvas to draw on
     */
    public GameLoop(int framesPerSecond, Canvas canvas) {
        // Set frames and graphics context
        gameCanvas = canvas;
        frames = framesPerSecond;

        // Initialize default values
        movables = new ArrayList<>();
        drawables = new ArrayList<>();
        collidables = new ArrayList<>();

        // Set up drawing and physics loop
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        setupDrawLoop();

        physicsLoop = new Timeline();
        physicsLoop.setCycleCount(Timeline.INDEFINITE);
        setupPhysicsLoop();
        attachedLoops = new ArrayList<>();
    }

    /**
     * Set up the game draw loop
     */
    private void setupDrawLoop() {
        // Create main drawing frame to loop over
        KeyFrame drawFrame = new KeyFrame(
                Duration.seconds(1.0 / frames),
                this::handleDrawFrame // Assign a function to handle each frame
        );
        // Add to drawing loop
        gameLoop.getKeyFrames().clear();
        gameLoop.getKeyFrames().add(drawFrame);

    }

    /**
     * Set up the physics loop
     */
    private void setupPhysicsLoop() {
        // Create main physics frame to loop over
        KeyFrame physicsFrame = new KeyFrame(
                Duration.seconds(1.0 / PHYSICS_LOOP_FRAMES),
                this::handlePhysicsFrame // Assign a function to handle each frame
        );
        // Add to physics loop
        physicsLoop.getKeyFrames().clear();
        physicsLoop.getKeyFrames().add(physicsFrame);
    }

    /**
     * Start the game loop
     */
    public void start() {
        gameLoop.play();
        physicsLoop.play();

        for (Timeline loop: attachedLoops) {
            loop.play();
        }
    }

    /**
     * Pause the game loop
     */
    public void pause() {
        gameLoop.pause();
        physicsLoop.pause();

        for (Timeline loop: attachedLoops) {
            loop.pause();
        }

    }

    /**
     * Stop the game loop
     */
    public void stop() {
        gameLoop.stop();
        physicsLoop.stop();

        for (Timeline loop: attachedLoops) {
            loop.stop();
        }
    }

    /**
     * Returns the status of the game loop (PAUSED, RUNNING, STOPPED)
     * @return The status of the game loop
     */
    public Animation.Status status() {
        return gameLoop.getStatus();
    }

    /**
     * Returns the arraylist of collidables to add or remove from
     * @return The collidables arraylist
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Returns the arraylist of drawables to add or remove from
     * @return The drawables arraylist
     */
    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    /**
     * Returns the arraylist of movables to add or remove from
     * @return The movables arraylist
     */
    public ArrayList<Movable> getMovables() {
        return movables;
    }

    public void addSprite(Sprite sprite) {
        drawables.add(sprite);
        collidables.add(sprite);
        movables.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        drawables.remove(sprite);
        collidables.remove(sprite);
        movables.remove(sprite);
    }

    public void setFramesPerSecond(int newFrames) {
        frames = newFrames;
        setupDrawLoop();
    }

    /**
     * Handle this drawing frame
     */
    private void handleDrawFrame(ActionEvent event) {
        clear();
        drawFrame();
    }

    /**
     * Clear the canvas
     */
    private void clear() {
        // Get canvas size
        double width = gameCanvas.getWidth();
        double height = gameCanvas.getHeight();

        // Clear canvas
        gameCanvas.getGraphicsContext2D().clearRect(0, 0, width, height);
    }

    /**
     * Draw all drawables onto the canvas
     */
    private void drawFrame() {
        // Draw all drawables
        for (Drawable drawable: drawables) {
            drawable.draw(gameCanvas.getGraphicsContext2D());
        }
    }

    /**
     * Handle this physics frame
     */
    private void handlePhysicsFrame(ActionEvent event) {
        moveObjects();
        detectCollisions();
    }

    /**
     * Move all movables
     */
    private void moveObjects() {
        // Move all movables
        for (Movable movable: movables) {
            movable.move(PHYSICS_LOOP_FRAMES);
        }
    }

    /**
     * Detect collisions for all collidables
     */
    private void detectCollisions() {
        // Loop over every collidable and compare it to every other collidable
        for (int i=0; i < collidables.size(); i++) {
            Collidable collidable = collidables.get(i);
            // Loop over every other collidable
            for (int j=i+1; j < collidables.size(); j++) {
                Collidable otherCollidable = collidables.get(j);
                // Detect and handle collision
                collidable.detectCollision(otherCollidable);
            }
        }
    }

    public ArrayList<Timeline> getAttachedLoops() {
        return attachedLoops;
    }
}
