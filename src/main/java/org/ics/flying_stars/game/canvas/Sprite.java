package org.ics.flying_stars.game.canvas;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.collision.Collidable;

public class Sprite implements Drawable, Collidable {
    private final Collidable collidable;
    private final Drawable drawable;

    public Sprite(Drawable drawable, Collidable collidable) {
        this.collidable = collidable;
        this.drawable = drawable;
    }

    @Override
    public void draw(GraphicsContext context) {
        drawable.draw(context);
    }

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        return collidable.detectCollision(otherCollidable);
    }

    @Override
    public Bounds getBounds() {
        return collidable.getBounds();
    }
}
