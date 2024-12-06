package org.ics.flying_stars.game.collision;

import javafx.geometry.Bounds;

public interface Collidable {
    boolean detectCollision(Collidable otherCollidable);

    Bounds getBounds();
}
