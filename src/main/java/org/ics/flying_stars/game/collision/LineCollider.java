package org.ics.flying_stars.game.collision;

import org.ics.flying_stars.game.geometry.Line;

public record LineCollider(Line line) implements Collidable {

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        if (otherCollidable instanceof LineCollider otherLineCollider) {
            return CollisionsDetector.detectCollision2Lines(line, otherLineCollider.line);
        }
        if (otherCollidable instanceof CircleCollider otherCircleCollider) {
            return CollisionsDetector.detectCollisionCircleLine(otherCircleCollider.circle(), line);
        }
        if (otherCollidable instanceof PolygonCollider otherPolygonCollider) {
            return otherPolygonCollider.detectCollision(this);
        }
        throw new UnsupportedOperationException("Collision not supported");
    }
}
