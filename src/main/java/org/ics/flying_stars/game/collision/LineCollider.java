package org.ics.flying_stars.game.collision;

import org.ics.flying_stars.game.sprites.Sprite;
import org.ics.flying_stars.game.geometry.Line;

/**
 * This class encapsulates a Line and detects collisions for it
 * @param line The Line object to encapsulate and detect collisions for
 */
public record LineCollider(Line line) implements Collidable {

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        // Determine type of other collidable
        if (otherCollidable instanceof LineCollider otherLineCollider) {
            return CollisionsDetector.detectCollision2Lines(line, otherLineCollider.line);
        }
        if (otherCollidable instanceof CircleCollider otherCircleCollider) {
            return CollisionsDetector.detectCollisionCircleLine(otherCircleCollider.circle(), line);
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
