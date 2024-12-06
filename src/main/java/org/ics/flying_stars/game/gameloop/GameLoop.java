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

public class GameLoop {
    private final Canvas gameCanvas;
    private final ArrayList<Collidable> collidables;
    private final ArrayList<Drawable> drawables;
    private final Timeline gameLoop;
    private final Deque<Event> gameEvents;

    private int frames;

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
        setupGameEventsFilter();
    }

    private void setupLoop() {
        // Create main frame to loop over
        KeyFrame mainFrame = new KeyFrame(
                Duration.seconds(1.0 / frames),
                this::handle
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

    public void start() {
        gameLoop.play();
    }

    public void pause() {
        gameLoop.pause();
    }

    public void stop() {
        gameLoop.stop();
    }

    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }


    private void handle(ActionEvent event) {
        drawFrame();
        detectCollisions();
        fireEvents();
    }

    private void fireEvents() {
        // Re-fire all events in gameEvents while emptying queue
        while (!gameEvents.isEmpty()) {
            Event gameEvent = gameEvents.removeFirst();
            
            // Trigger event
            Node gameEventTarget = (Node) gameEvent.getTarget();
            gameEventTarget.fireEvent(gameEvent);
        }
    }

    private void clear() {
        // Get canvas size
        double width = gameCanvas.getWidth();
        double height = gameCanvas.getHeight();

        // Clear canvas
        gameCanvas.getGraphicsContext2D().clearRect(0, 0, width, height);
    }

    private void drawFrame() {
        // Clear canvas
        clear();

        // Draw all drawables
        for (Drawable drawable: drawables) {
            drawable.draw(gameCanvas.getGraphicsContext2D());
        }
    }

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
