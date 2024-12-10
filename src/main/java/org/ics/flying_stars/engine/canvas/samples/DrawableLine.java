package org.ics.flying_stars.engine.canvas.samples;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.canvas.Drawable;
import org.ics.flying_stars.engine.geometry.Line;
import org.ics.flying_stars.engine.geometry.Point;

/**
 * A line that can be drawn on a canvas
 */
public class DrawableLine extends Line implements Drawable {
    /**
     * @param startPoint The starting point of the line
     * @param endPoint The ending point of the line
     */
    public DrawableLine(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    /**
     * Draw this line on a canvas
     * @param context The canvas's graphics context to draw this Drawable on
     */
    @Override
    public void draw(GraphicsContext context) {
        // Draw the line
        context.strokeLine(startPoint().getX(), startPoint().getY(),
                endPoint().getX(), endPoint().getY());
    }
}
