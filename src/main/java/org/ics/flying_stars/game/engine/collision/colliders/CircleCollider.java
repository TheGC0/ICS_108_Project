package org.ics.flying_stars.game.engine.collision.colliders;

import org.ics.flying_stars.game.engine.collision.Collidable;
import org.ics.flying_stars.game.engine.collision.CollisionsDetector;
import org.ics.flying_stars.game.engine.geometry.Circle;

/**
 * This class encapsulates a Circle and detects collisions for it
 */
public class CircleCollider extends Collider {
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
        return otherCollidable.detectElementaryCollision(this);
    }

    @Override
    public boolean detectElementaryCollision(CircleCollider otherCircleCollider) {
        boolean collision = CollisionsDetector.detectCollision2Circles(circle, otherCircleCollider.circle);
        if (collision) {
            handleCollision(otherCircleCollider);
            otherCircleCollider.handleCollision(this);
        }
        return collision;
    }

    @Override
    public boolean detectElementaryCollision(LineCollider otherLineCollider) {
        boolean collision = CollisionsDetector.detectCollisionCircleLine(circle, otherLineCollider.line());
        if (collision) {
            handleCollision(otherLineCollider);
            otherLineCollider.handleCollision(this);
        }
        return collision;
    }
}