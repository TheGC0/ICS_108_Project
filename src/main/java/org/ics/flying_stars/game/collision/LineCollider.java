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
        return otherCollidable.detectCollision(this);
    }

    @Override
    public boolean detectCollision(CircleCollider otherCircleCollider) {
        return CollisionsDetector.detectCollisionCircleLine(otherCircleCollider.circle(), line);
    }

    @Override
    public boolean detectCollision(LineCollider otherLineCollider) {
        return CollisionsDetector.detectCollision2Lines(line, otherLineCollider.line);
    }

    @Override
    public boolean detectCollision(PolygonCollider otherPolygonCollider) {
        return otherPolygonCollider.detectCollision(this);
    }
}
