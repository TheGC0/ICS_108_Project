package org.ics.flying_stars.engine.collision.colliders;

import org.ics.flying_stars.engine.collision.Collidable;
import org.ics.flying_stars.engine.collision.CollisionTranscript;
import org.ics.flying_stars.engine.collision.CollisionsMath;
import org.ics.flying_stars.engine.geometry.Line;

/**
 * This class encapsulates a Line and detects collisions for it
 */
public class LineCollider extends Collider {
    private final Line line;

    /**
     * @param line The Line object to encapsulate and detect collisions for
     */
    public LineCollider(Line line) {
        super();
        this.line = line;
    }

    public Line line() {
        return line;
    }

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        return otherCollidable.detectElementaryCollision(this);
    }

    @Override
    public boolean detectElementaryCollision(CircleCollider otherCircleCollider) {
        boolean collision = CollisionsMath.detectCollisionCircleLine(otherCircleCollider.circle(), line);
        if (collision) {
            CollisionTranscript transcript1 = new CollisionTranscript(this);
            CollisionTranscript transcript2 = new CollisionTranscript(otherCircleCollider);

            transcript1.setLinkedTranscript(transcript2);
            transcript2.setLinkedTranscript(transcript1);

            buildCollisionTranscript(transcript1);
            otherCircleCollider.buildCollisionTranscript(transcript2);

            handleCollision(transcript1);
            otherCircleCollider.handleCollision(transcript2);
        }
        return collision;
    }

    @Override
    public boolean detectElementaryCollision(LineCollider otherLineCollider) {
        boolean collision = CollisionsMath.detectCollision2Lines(line, otherLineCollider.line);
        if (collision) {
            CollisionTranscript transcript1 = new CollisionTranscript(this);
            CollisionTranscript transcript2 = new CollisionTranscript(otherLineCollider);

            transcript1.setLinkedTranscript(transcript2);
            transcript2.setLinkedTranscript(transcript1);

            buildCollisionTranscript(transcript1);
            otherLineCollider.buildCollisionTranscript(transcript2);

            handleCollision(transcript1);
            otherLineCollider.handleCollision(transcript2);
        }
        return collision;
    }

}
