package org.ics.flying_stars.engine.collision;


import org.ics.flying_stars.engine.collision.colliders.CircleCollider;
import org.ics.flying_stars.engine.collision.colliders.LineCollider;

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

    // Detect elementary collisions (building blocks of colliders)
    boolean detectElementaryCollision(CircleCollider otherCircleCollider);

    boolean detectElementaryCollision(LineCollider otherLineCollider);

    // Add collision handlers (lambdas that run when a collision is registered in a transcript)
    void addCollisionHandler(CollisionHandler handler);

    // Execute attached lambdas
    void handleCollision(CollisionTranscript collisionTranscript);

    // Add a lambda that builds a transcript from children colliders
    void setCollisionTranscriptBuilder(CollisionHandler builder);

    // Execute collision builder lambdas
    void buildCollisionTranscript(CollisionTranscript collisionTranscript);

}
