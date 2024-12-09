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
        return otherCollidable.detectBaseCollision(this);
    }

    @Override
    public boolean detectBaseCollision(CircleCollider otherCircleCollider) {
        boolean collision = CollisionsDetector.detectCollision2Circles(circle, otherCircleCollider.circle);
        if (collision) {
            handleCollision(otherCircleCollider);
            otherCircleCollider.handleCollision(this);
        }
        return collision;
    }

    @Override
    public boolean detectBaseCollision(LineCollider otherLineCollider) {
        boolean collision = CollisionsDetector.detectCollisionCircleLine(circle, otherLineCollider.line());
        if (collision) {
            handleCollision(otherLineCollider);
            otherLineCollider.handleCollision(this);
        }
        return collision;
    }
}