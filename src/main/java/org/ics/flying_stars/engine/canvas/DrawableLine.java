package org.ics.flying_stars.engine.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.geometry.Line;
import org.ics.flying_stars.engine.geometry.Vector2D;

/**
 * A line that can be drawn on a canvas
 */
public class DrawableLine extends Line implements Drawable {
    /**
     * @param startPoint The starting point of the line
     * @param endPoint The ending point of the line
     */
    public DrawableLine(Vector2D startPoint, Vector2D endPoint) {
        super(startPoint, endPoint);
    }

    /**
     * Draw this line on a canvas
     * @param context The canvas's graphics context to draw this Drawable on
     */
    @Override
    public void draw(GraphicsContext context) {
        // Draw the line
        context.setLineWidth(2);
        context.strokeLine(startPoint().getX(), startPoint().getY(),
                endPoint().getX(), endPoint().getY());
    }
}
