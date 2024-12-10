package org.ics.flying_stars.game.engine.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.engine.canvas.samples.DrawableCircle;
import org.ics.flying_stars.game.engine.geometry.Point;

public class ColoredCircle extends DrawableCircle implements Colored {
    private Colour color;

    /**
     * @param radius The radius of the circle
     * @param center The center point
     * @param fill   Whether the circle should be drawn filled or not
     */
    public ColoredCircle(int radius, Point center, boolean fill, Colour color) {
        super(radius, center, fill);
        this.color = color;
    }

    @Override
    public void setColor(Colour color) {
        this.color = color;
    }

    @Override
    public Colour getColor() {
        return this.color;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setStroke(color.color);
        super.draw(context);
    }
}
