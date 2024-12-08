package org.ics.flying_stars.game.sprites;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.canvas.Drawable;
import org.ics.flying_stars.game.collision.Collidable;

public abstract class Sprite implements Drawable, Collidable, Movable {
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
}
