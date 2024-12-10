package org.ics.flying_stars.game.engine.collision.colliders.colored;

import org.ics.flying_stars.game.engine.canvas.Colour;
import org.ics.flying_stars.game.engine.collision.colliders.LineCollider;
import org.ics.flying_stars.game.engine.collision.colliders.PolygonCollider;
import org.ics.flying_stars.game.engine.geometry.Polygon;

public class MultipleColoredPolygonCollider extends PolygonCollider{
    private final Colour[] colours;

    /**
     * @param polygon The Polygon object to encapsulate and detect collisions for
     */
    public MultipleColoredPolygonCollider(Polygon polygon, Colour[] colours) {
        super(polygon);
        this.colours = colours;
    }

    @Override
    protected void createLineColliders() {
        // Iterate over every edge
        for (int i=0; i < lineColliders.length; i++) {
            // Create and add a new line collider from the polygon edge
              LineCollider lineCollider = new ColoredLineCollider(polygon.getEdges()[i], colours[i]);
            lineColliders[i] = lineCollider;
        }
    }

}
