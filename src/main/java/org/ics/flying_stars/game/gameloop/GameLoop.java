package org.ics.flying_stars.game.gameloop;


import org.ics.flying_stars.game.collision.Collidable;
import org.ics.flying_stars.game.canvas.Drawable;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

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
    private final Canvas gameCanvas;
    private final ArrayList<Collidable> collidables;
    private final ArrayList<Drawable> drawables;
    private final Timeline gameLoop;
    private final Deque<Event> gameEvents;

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
        drawables = new ArrayList<>();
        collidables = new ArrayList<>();
        gameEvents = new ArrayDeque<>();

        // Set up loop
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        setupLoop();

        // Set up game event filter
//        setupGameEventsFilter();
    }

    /**
     * Set up the game loop
     */
    private void setupLoop() {
        // Create main frame to loop over
        KeyFrame mainFrame = new KeyFrame(
                Duration.seconds(1.0 / frames),
                this::handle // Assign a function to handle each frame
        );
        // Add to loop
        gameLoop.getKeyFrames().clear();
        gameLoop.getKeyFrames().add(mainFrame);
    }

    private void setupGameEventsFilter() {
        // Catch all events, clone them, and then consume them.
        // The cloned events will be re-fired the next frame
        gameCanvas.addEventFilter(
                Event.ANY,
                event -> {
                    // Only catch events if game loop is running
                    if (gameLoop.getStatus() != Animation.Status.RUNNING) {
                        return;
                    }
                    // Only catch events with a target node
                    if (event.getTarget() instanceof Node) {
                        gameEvents.addLast((Event) event.clone());
                        event.consume();
                    }
                }
        );
    }

    /**
     * Start the game loop
     */
    public void start() {
        gameLoop.play();
    }

    /**
     * Pause the game loop
     */
    public void pause() {
        gameLoop.pause();
    }

    /**
     * Stop the game loop
     */
    public void stop() {
        gameLoop.stop();
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
     * Handle this frame
     */
    private void handle(ActionEvent event) {
        drawFrame();
        detectCollisions();
//        fireEvents();
    }

    private void fireEvents() {
        // Re-fire all events in gameEvents while emptying queue
        while (!gameEvents.isEmpty()) {
            Event gameEvent = gameEvents.removeFirst();
            System.out.println(gameEvent);

            // Trigger event
            Node gameEventTarget = (Node) gameEvent.getTarget();
            gameEventTarget.fireEvent(gameEvent);
        }
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
        // Clear canvas
        clear();

        // Draw all drawables
        for (Drawable drawable: drawables) {
            drawable.draw(gameCanvas.getGraphicsContext2D());
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
                // Detect collision
                boolean collision = collidable.detectCollision(otherCollidable);

                if (collision) {
                    // TODO Do something (raise an event)
                    System.out.println("Collision detected");
                }
            }
        }
    }
}
