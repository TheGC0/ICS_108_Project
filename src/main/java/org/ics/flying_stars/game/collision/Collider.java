package org.ics.flying_stars.game.collision;

import javafx.geometry.Bounds;

public class Collider implements Collidable {
    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        return false;
    }

    @Override
    public Bounds getBounds() {
        return null;
    }
}
