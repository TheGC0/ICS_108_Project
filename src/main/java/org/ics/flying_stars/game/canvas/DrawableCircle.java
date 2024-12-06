package org.ics.flying_stars.game.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.geometry.Circle;

public record DrawableCircle(Circle circle, boolean fill) implements Drawable {
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
