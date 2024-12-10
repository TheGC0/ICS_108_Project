package org.ics.flying_stars.engine.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.canvas.samples.DrawableCircle;
import org.ics.flying_stars.engine.geometry.Vector2D;

public class ColoredCircle extends DrawableCircle implements Colored {
    private Colour color;

    /**
     * @param radius The radius of the circle
     * @param center The center point
     */
    public ColoredCircle(int radius, Vector2D center, Colour color) {
        super(radius, center, true);
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
        context.setFill(color.color);
        super.draw(context);
    }
}
