package org.ics.flying_stars.game.engine.collision.colliders;

import org.ics.flying_stars.game.engine.collision.Collidable;
import org.ics.flying_stars.game.engine.collision.CollisionsDetector;
import org.ics.flying_stars.game.engine.geometry.Line;

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
        boolean collision = CollisionsDetector.detectCollisionCircleLine(otherCircleCollider.circle(), line);
        if (collision) {
            handleCollision(otherCircleCollider);
            otherCircleCollider.handleCollision(this);
        }
        return collision;
    }

    @Override
    public boolean detectElementaryCollision(LineCollider otherLineCollider) {
        boolean collision = CollisionsDetector.detectCollision2Lines(line, otherLineCollider.line);
        if (collision) {
            handleCollision(otherLineCollider);
            otherLineCollider.handleCollision(this);
        }
        return collision;
    }

}
