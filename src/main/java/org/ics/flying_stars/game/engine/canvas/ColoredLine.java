package org.ics.flying_stars.game.engine.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.engine.canvas.samples.DrawableLine;
import org.ics.flying_stars.game.engine.geometry.Line;
import org.ics.flying_stars.game.engine.geometry.Point;

public class ColoredLine extends DrawableLine implements Colored {
    private Colour color;


    public ColoredLine(Point startPoint, Point endPoint, Colour color) {
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
