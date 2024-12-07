package org.ics.flying_stars.game.collision;

import org.ics.flying_stars.game.canvas.Sprite;
import org.ics.flying_stars.game.geometry.Circle;

public record CircleCollider(Circle circle) implements Collidable {

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
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
        throw new UnsupportedOperationException("Collision not supported");
    }

}
