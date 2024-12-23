package org.ics.flying_stars.engine.collision.colliders;

import org.ics.flying_stars.engine.collision.Collidable;
import org.ics.flying_stars.engine.geometry.Polygon;

/**
 * This class encapsulates a Polygon and detects collisions for it
 */
public class PolygonCollider extends Collider {
    protected Polygon polygon;
    protected LineCollider[] lineColliders;

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

    // Only for use with subclasses
    protected PolygonCollider() {
        super();
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

    // Connect each line collider to build full collision transcripts and forward collisions to collision handlers
    protected void connectLineColliderHandlers() {
        for (LineCollider collider: lineColliders) {
            collider.addCollisionHandler(this::handleCollision);
            collider.setCollisionTranscriptBuilder(this::buildCollisionTranscript);
        }
    }

    /**
     * @return The encapsulated polygon
     */
    public Polygon polygon() {
        return polygon;
    }

    // Helper method to detect a collision for each child line collider
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