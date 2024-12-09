package org.ics.flying_stars.game.collision;

import org.ics.flying_stars.game.geometry.Line;

/**
 * This class encapsulates a Line and detects collisions for it
 */
public final class LineCollider extends Collider {
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
        return otherCollidable.detectBaseCollision(this);
    }

    @Override
    public boolean detectBaseCollision(CircleCollider otherCircleCollider) {
        boolean collision = CollisionsDetector.detectCollisionCircleLine(otherCircleCollider.circle(), line);
        if (collision) {
            handleCollision(otherCircleCollider);
            otherCircleCollider.handleCollision(this);
        }
        return collision;
    }

    @Override
    public boolean detectBaseCollision(LineCollider otherLineCollider) {
        boolean collision = CollisionsDetector.detectCollision2Lines(line, otherLineCollider.line);
        if (collision) {
            handleCollision(otherLineCollider);
            otherLineCollider.handleCollision(this);
        }
        return collision;
    }

}
