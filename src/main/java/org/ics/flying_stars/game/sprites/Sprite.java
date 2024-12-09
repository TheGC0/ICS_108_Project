package org.ics.flying_stars.game.sprites;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.canvas.Drawable;
import org.ics.flying_stars.game.collision.*;

public abstract class Sprite implements Drawable, Collidable, Movable {
    private final Collider collider;
    private final Drawable drawable;

    public Sprite(Drawable drawable, Collider collider) {
        this.collider = collider;
        this.drawable = drawable;
    }

    @Override
    public void draw(GraphicsContext context) {
        drawable.draw(context);
    }

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        return collider.detectCollision(otherCollidable);
    }

    @Override
    public boolean detectBaseCollision(CircleCollider otherCircleCollider) {
        return collider.detectBaseCollision(otherCircleCollider);
    }

    @Override
    public boolean detectBaseCollision(LineCollider otherLineCollider) {
        return collider.detectBaseCollision(otherLineCollider);
    }


}