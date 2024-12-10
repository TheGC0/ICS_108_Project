package org.ics.flying_stars.engine.collision.colliders.colored;

import org.ics.flying_stars.engine.canvas.Colored;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.collision.colliders.CircleCollider;
import org.ics.flying_stars.engine.geometry.Circle;

public class ColoredCircleCollider extends CircleCollider implements Colored {
    private Colour color;

    /**
     * @param circle The Circle object to encapsulate and detect collisions for
     */
    public ColoredCircleCollider(Circle circle, Colour color) {
        super(circle);
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
