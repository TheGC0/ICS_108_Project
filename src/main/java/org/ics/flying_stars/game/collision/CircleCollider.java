package org.ics.flying_stars.game.collision;

import org.ics.flying_stars.game.sprites.Sprite;
import org.ics.flying_stars.game.geometry.Circle;

/**
 * This class encapsulates a Circle and detects collisions for it
 * @param circle The Circle object to encapsulate and detect collisions for
 */
public record CircleCollider(Circle circle) implements Collidable {

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        // Determine type of other collidable
        if (otherCollidable instanceof CircleCollider otherCircleCollider) {
            return CollisionsDetector.detectCollision2Circles(circle, otherCircleCollider.circle);
        }
        if (otherCollidable instanceof LineCollider otherLineCollider) {
            return CollisionsDetector.detectCollisionCircleLine(circle, otherLineCollider.line());
        }
        if (otherCollidable instanceof PolygonCollider otherPolygonCollider) {
            return otherPolygonCollider.detectCollision(this);
        }
        if (otherCollidable instanceof Sprite otherSprite) {
            return otherSprite.detectCollision(this);
        }
        // Unsupported other collidable
        throw new UnsupportedOperationException("Collision not supported");
    }

}
