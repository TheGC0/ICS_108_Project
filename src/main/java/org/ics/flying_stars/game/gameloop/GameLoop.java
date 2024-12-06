package org.ics.flying_stars.game.gameloop;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.collision.Collidable;
import org.ics.flying_stars.game.canvas.Drawable;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import java.util.ArrayList;

public class GameLoop{
    private final GraphicsContext graphicsContext;
    private final ArrayList<Collidable> collidables;
    private final ArrayList<Drawable> drawables;
    private final Timeline gameLoop;

    private int frames;

    public GameLoop(int framesPerSecond, GraphicsContext canvasGraphicsContext) {
        // Set frames and graphics context
        graphicsContext = canvasGraphicsContext;
        frames = framesPerSecond;

        // Initialize default values
        drawables = new ArrayList<>();
        collidables = new ArrayList<>();

        // Set up loop
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        setupLoop();
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
    }

    private void clear() {
        // Get canvas size
        double width = graphicsContext.getCanvas().getWidth();
        double height = graphicsContext.getCanvas().getHeight();

        // Clear canvas
        graphicsContext.clearRect(0, 0, width, height);
    }

    private void drawFrame() {
        // Clear canvas
        clear();

        // Draw all drawables
        for (Drawable drawable: drawables) {
            drawable.draw(graphicsContext);
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
                }
            }
        }
    }
}
