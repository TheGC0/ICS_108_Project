package org.ics.flying_stars.engine.canvas.samples;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.canvas.Drawable;
import org.ics.flying_stars.engine.geometry.Line;
import org.ics.flying_stars.engine.geometry.Polygon;
import org.ics.flying_stars.engine.geometry.Vector2D;

public class DrawablePolygon extends Polygon implements Drawable {
    public DrawablePolygon(Vector2D[] points) {
        super(points);
    }

    @Override
    public void draw(GraphicsContext context) {
        for (Line edge: getEdges()) {
            // Draw the line
            context.strokeLine(edge.startPoint().getX(), edge.startPoint().getY(),
                    edge.endPoint().getX(), edge.endPoint().getY());
        }
    }
}
