package org.ics.flying_stars.engine.collision.colliders.colored;

import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.collision.colliders.LineCollider;
import org.ics.flying_stars.engine.collision.colliders.PolygonCollider;
import org.ics.flying_stars.engine.geometry.Polygon;

// Same as a normal collider except this has child colored line colliders
public class MultipleColoredPolygonCollider extends PolygonCollider {
    private final Colour[] colours;

    /**
     * @param polygon The Polygon object to encapsulate and detect collisions for
     */
    public MultipleColoredPolygonCollider(Polygon polygon, Colour[] colours) {
        super();
        this.colours = colours;
        this.polygon = polygon;
        this.lineColliders = new LineCollider[polygon.getEdges().length];

        // Create a line collider for each edge
        createLineColliders();

        // Connect each line collider handler to this handler
        connectLineColliderHandlers();
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
