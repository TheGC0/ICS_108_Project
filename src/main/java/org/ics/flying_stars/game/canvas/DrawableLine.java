package org.ics.flying_stars.game.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.geometry.Line;
import org.ics.flying_stars.game.geometry.Point;

public class DrawableLine extends Line implements Drawable {
    public DrawableLine(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void draw(GraphicsContext context) {
        context.strokeLine(startPoint().getX(), startPoint().getY(),
                endPoint().getX(), endPoint().getY());
    }
}
