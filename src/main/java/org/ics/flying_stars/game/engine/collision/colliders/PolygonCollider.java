package org.ics.flying_stars.game.engine.collision.colliders;

import org.ics.flying_stars.game.engine.collision.Collidable;
import org.ics.flying_stars.game.engine.geometry.Polygon;

/**
 * This class encapsulates a Polygon and detects collisions for it
 */
public class PolygonCollider extends Collider {
    protected final Polygon polygon;
    protected final LineCollider[] lineColliders;

    /**
     * @param polygon The Polygon object to encapsulate and detect collisions for
     */
    public PolygonCollider(Polygon polygon) {
        super();
        this.polygon = polygon;
        this.lineColliders = new LineCollider[polygon.getEdges().length];

        // Create a line collider for each edge
        createLineColliders();

        // Connect each line collider handler to this handler
        connectLineColliderHandlers();
    }

    /**
     * Creates a line collider object for each edge in the polygon
     */
    protected void createLineColliders() {
        // Iterate over every edge
        for (int i=0; i < lineColliders.length; i++) {
            // Create and add a new line collider from the polygon edge
            LineCollider lineCollider = new LineCollider(polygon.getEdges()[i]);
            lineColliders[i] = lineCollider;
        }
    }

    private void connectLineColliderHandlers() {
        for (LineCollider collider: lineColliders) {
            collider.addCollisionHandler(this::handleCollision);
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
            if (otherCollidable.detectElementaryCollision(collider)) {
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
    public boolean detectElementaryCollision(CircleCollider otherCircleCollider) {
        return detectCollisionForAllLines(otherCircleCollider);
    }

    @Override
    public boolean detectElementaryCollision(LineCollider otherLineCollider) {
        return detectCollisionForAllLines(otherLineCollider);
    }
}