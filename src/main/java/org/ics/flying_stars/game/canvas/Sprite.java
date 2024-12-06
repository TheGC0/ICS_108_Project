package org.ics.flying_stars.game.canvas;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.collision.Collidable;

public class Sprite implements Drawable, Collidable {
    @Override
    public void draw(GraphicsContext context) {

    }

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        return false;
    }

    @Override
    public Bounds getBounds() {
        return null;
    }
}
