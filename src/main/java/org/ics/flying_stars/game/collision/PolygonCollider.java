package org.ics.flying_stars.game.collision;

import org.ics.flying_stars.game.geometry.Polygon;

/**
 * This class encapsulates a Polygon and detects collisions for it
 */
public final class PolygonCollider extends Collider {
    private final Polygon polygon;
    private final LineCollider[] lineColliders;

    /**
     * @param polygon The Polygon object to encapsulate and detect collisions for
     */
    public PolygonCollider(Polygon polygon) {
        super();
        this.polygon = polygon;
        this.lineColliders = new LineCollider[polygon.getEdges().length];

        // Create a line collider for each edge
        createLineColliders();
    }

    /**
     * Creates a line collider object for each edge in the polygon
     */
    private void createLineColliders() {
        // Iterate over every edge
        for (int i=0; i < lineColliders.length; i++) {
            // Create and add a new line collider from the polygon edge
            LineCollider lineCollider = new LineCollider(polygon.getEdges()[i]);
            lineColliders[i] = lineCollider;
        }
    }

    /**
     * @return The encapsulated polygon
     */
    public Polygon polygon() {
        return polygon;
    }

    private <C extends Collidable> boolean detectCollisionForAllLines(C otherCollidable) {
        boolean generalCollision = false;
        for (LineCollider collider: lineColliders) {
            if (otherCollidable.detectBaseCollision(collider)) {
                generalCollision = true;
            }
        }
        return generalCollision;
    }

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        return detectCollisionForAllLines(otherCollidable);
    }

    @Override
    public boolean detectBaseCollision(CircleCollider otherCircleCollider) {
        return detectCollisionForAllLines(otherCircleCollider);
    }

    @Override
    public boolean detectBaseCollision(LineCollider otherLineCollider) {
        return detectCollisionForAllLines(otherLineCollider);
    }

}