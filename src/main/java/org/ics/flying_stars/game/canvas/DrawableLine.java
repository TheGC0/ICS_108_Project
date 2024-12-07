package org.ics.flying_stars.game.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.geometry.Line;

public record DrawableLine(Line line) implements Drawable {
    @Override
    public void draw(GraphicsContext context) {
        context.strokeLine(line.startPoint().getX(), line.startPoint().getY(),
                line.endPoint().getX(), line.endPoint().getY());
    }
}
