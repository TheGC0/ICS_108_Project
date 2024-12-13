package org.ics.flying_stars.game.entities;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.canvas.Colored;
import org.ics.flying_stars.engine.canvas.ColoredCircle;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.collision.colliders.colored.ColoredCircleCollider;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.engine.sprites.Sprite;


// Player class
public class Player extends Sprite implements Colored {
    public static final int SPEED_MULTIPLIER = 15;
    public static final int SIZE = 10;
    public static final int INVINCIBILITY_TIME = 2000;
    private final Vector2D mousePosition;
    private final ColoredCircle circle;
    private final ColoredCircleCollider coloredCircleCollider;
    private Colour colour;

    private boolean isInvincible = false;
    private double invincibilityEndTime;


    public Player(Vector2D startPos, Colour color) {
        // Create a colored circle and circle collider
        this.circle = new ColoredCircle(SIZE, startPos, color);
        this.coloredCircleCollider = new ColoredCircleCollider(circle, color);

        // Setup color and temp mouse position
        colour = color;
        mousePosition = new Vector2D(20,20);

        // Setup drawable
        drawable = circle;

        // Setup collider
        setCollider(coloredCircleCollider);
    }

    public boolean isInvincible() { return isInvincible; }

    // Enable and set invincibility expected duration
    public void getHit(){
        isInvincible = true;
        double time = System.currentTimeMillis();
        invincibilityEndTime = time + INVINCIBILITY_TIME;
    }

    // Set mouse pos
    public void setMousePos(double x, double y) {
        mousePosition.setXY(x, y);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        boolean draw = true;

        // Check invincibility
        if (isInvincible){
            // Emulate blinking by not drawing in specific ranges (odd/even)
            final int BLINK_DURATION = 200;
            int remainingInvincibleDuration = (int) (invincibilityEndTime - System.currentTimeMillis());

            // Check if invincibility already ended
            if (remainingInvincibleDuration < 0) {
                // No longer invincible
                isInvincible = false;

            } else {
                // Determine range
                int nthRange = remainingInvincibleDuration / BLINK_DURATION;
                // Draw in odd ranges
                draw = nthRange % 2 == 1;
            }
        }

        // Draw
        if (draw) {
            super.draw(graphicsContext);
        }
    }

    @Override
    public void move(int physicsFrames) {
        // Calculate unit vector velocity from pos to mouse pos
        Vector2D velocity = circle.getCenter().getUnitVectorFrom(mousePosition);

        // Scale the velocity vector depending on distance
        double scale = mousePosition.distanceFrom(circle.getCenter()) / physicsFrames * SPEED_MULTIPLIER;
        velocity.scale(scale);

        // Update position based on velocity
        circle.getCenter().setXY(velocity.getX() + circle.getX(), velocity.getY() + circle.getY());
    }

    @Override
    public void setColor(Colour color) {
        // Update color of the collider and drawable
        coloredCircleCollider.setColor(color);
        circle.setColor(color);
        colour = color;
    }

    @Override
    public Colour getColor() {
        return colour;
    }
}
