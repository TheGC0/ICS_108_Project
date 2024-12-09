package org.ics.flying_stars.game.engine.canvas.samples;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.engine.canvas.Drawable;
import org.ics.flying_stars.game.engine.geometry.Circle;
import org.ics.flying_stars.game.engine.geometry.Point;

/**
 * A circle that can be drawn on a canvas
 */
public class DrawableCircle extends Circle implements Drawable {
    private final boolean fill;

    /**
     * @param radius The radius of the circle
     * @param center The center point
     * @param fill Whether the circle should be drawn filled or not
     */
    public DrawableCircle(int radius, Point center, boolean fill) {
        super(radius, center);
        this.fill = fill;
    }

    /**
     * Draw this circle on a canvas
     * @param context The canvas's graphics context to draw this Drawable on
     */
    @Override
    public void draw(GraphicsContext context) {
        if (fill) {
            // Draw a filled circle
            context.fillOval(
                    getX() - getRadius(),
                    getY() - getRadius(),
                    getRadius() * 2,
                    getRadius() * 2);
        } else {
            // Draw an unfilled circle
            context.strokeOval(
                    getX() - getRadius(),
                    getY() - getRadius(),
                    getRadius() * 2,
                    getRadius() * 2);
        }
    }
}
