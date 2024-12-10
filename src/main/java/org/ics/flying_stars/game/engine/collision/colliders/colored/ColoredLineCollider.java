package org.ics.flying_stars.game.engine.collision.colliders.colored;

import org.ics.flying_stars.game.engine.canvas.Colored;
import org.ics.flying_stars.game.engine.canvas.Colour;
import org.ics.flying_stars.game.engine.collision.colliders.LineCollider;
import org.ics.flying_stars.game.engine.geometry.Line;

public class ColoredLineCollider extends LineCollider implements Colored {
    private Colour color;
    /**
     * @param line The Line object to encapsulate and detect collisions for
     */
    public ColoredLineCollider(Line line, Colour color) {
        super(line);
        this.color = color;
    }

    @Override
    public void setColor(Colour color) {
        this.color = color;
    }

    @Override
    public Colour getColor() {
        return color;
    }

}
