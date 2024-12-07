package org.ics.flying_stars.game.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.geometry.Circle;
import org.ics.flying_stars.game.geometry.Point;

public class DrawableCircle extends Circle implements Drawable {
    private final boolean fill;

    public DrawableCircle(int radius, Point center, boolean fill) {
        super(radius, center);
        this.fill = fill;
    }

    @Override
    public void draw(GraphicsContext context) {
        if (fill) {
            context.fillOval(
                    circle.getX() - circle.getRadius(),
                    circle.getY() - circle.getRadius(),
                    circle.getRadius() * 2,
                    circle.getRadius() * 2);
        } else {
            context.strokeOval(
                    circle.getX() - circle.getRadius(),
                    circle.getY() - circle.getRadius(),
                    circle.getRadius() * 2,
                    circle.getRadius() * 2);
        }
    }
}
