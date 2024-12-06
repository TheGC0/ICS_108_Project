package org.ics.flying_stars.game.collision;


public interface Collidable {
    boolean detectCollision(Collidable otherCollidable);
}
