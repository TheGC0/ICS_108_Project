package org.ics.flying_stars.engine.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.canvas.samples.DrawableLine;
import org.ics.flying_stars.engine.geometry.Line;
import org.ics.flying_stars.engine.geometry.Vector2D;

public class ColoredLine extends DrawableLine implements Colored {
    private Colour color;


    public ColoredLine(Vector2D startPoint, Vector2D endPoint, Colour color) {
        super(startPoint, endPoint);
        this.color = color;
    }

    public ColoredLine(Line line, Colour color) {
        this(line.startPoint(), line.endPoint(), color);
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
