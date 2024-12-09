package org.ics.flying_stars.game.collision;

import org.ics.flying_stars.game.geometry.Circle;

/**
 * This class encapsulates a Circle and detects collisions for it
 */
public final class CircleCollider extends Collider {
    private final Circle circle;

    /**
     * @param circle The Circle object to encapsulate and detect collisions for
     */
    public CircleCollider(Circle circle) {
        super();
        this.circle = circle;
    }

    public Circle circle() {
        return circle;
    }

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        return otherCollidable.detectCollision(this);
    }

    @Override
    public boolean detectCollision(CircleCollider otherCircleCollider) {
        return CollisionsDetector.detectCollision2Circles(circle, otherCircleCollider.circle);
    }

    @Override
    public boolean detectCollision(LineCollider otherLineCollider) {
        return CollisionsDetector.detectCollisionCircleLine(circle, otherLineCollider.line());
    }

    @Override
    public boolean detectCollision(PolygonCollider otherPolygonCollider) {
        return otherPolygonCollider.detectCollision(this);
    }
}
