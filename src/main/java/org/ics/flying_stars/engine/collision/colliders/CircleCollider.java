package org.ics.flying_stars.engine.collision.colliders;

import org.ics.flying_stars.engine.collision.Collidable;
import org.ics.flying_stars.engine.collision.CollisionTranscript;
import org.ics.flying_stars.engine.collision.CollisionsMath;
import org.ics.flying_stars.engine.geometry.Circle;

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
        boolean collision = CollisionsMath.detectCollision2Circles(circle, otherCircleCollider.circle);
        if (collision) {
            // Create the transcripts for each collider
            CollisionTranscript transcript1 = new CollisionTranscript(this);
            CollisionTranscript transcript2 = new CollisionTranscript(otherCircleCollider);

            // Link the transcripts
            transcript1.setLinkedTranscript(transcript2);

            // Build the full transcripts
            buildCollisionTranscript(transcript1);
            otherCircleCollider.buildCollisionTranscript(transcript2);

            // Handle collision
            handleCollision(transcript1);
            otherCircleCollider.handleCollision(transcript2);
        }
        return collision;
    }

    @Override
    public boolean detectElementaryCollision(LineCollider otherLineCollider) {
        boolean collision = CollisionsMath.detectCollisionCircleLine(circle, otherLineCollider.line());
        if (collision) {
            // Create the transcripts for each collider
            CollisionTranscript transcript1 = new CollisionTranscript(this);
            CollisionTranscript transcript2 = new CollisionTranscript(otherLineCollider);

            // Link the transcripts
            transcript1.setLinkedTranscript(transcript2);
            transcript2.setLinkedTranscript(transcript1);

            // Build the full transcripts
            buildCollisionTranscript(transcript1);
            otherLineCollider.buildCollisionTranscript(transcript2);

            // Handle collision
            handleCollision(transcript1);
            otherLineCollider.handleCollision(transcript2);
        }
        return collision;
    }
}