package org.ics.flying_stars.game.engine.collision;


import org.ics.flying_stars.game.engine.collision.colliders.CircleCollider;
import org.ics.flying_stars.game.engine.collision.colliders.LineCollider;

/**
 * An interface for objects that support and detect collision
 */
public interface Collidable {
    /**
     * Detect collision with other collidable
     *
     * @param otherCollidable The other collidable to detect collision with
     * @return true if this collidable collides with the other collidable and false otherwise
     */
    boolean detectCollision(Collidable otherCollidable);

    boolean detectElementaryCollision(CircleCollider otherCircleCollider);

    boolean detectElementaryCollision(LineCollider otherLineCollider);

    void addCollisionHandler(CollisionHandler handler);

    void handleCollision(Collidable otherCollidable);

}
