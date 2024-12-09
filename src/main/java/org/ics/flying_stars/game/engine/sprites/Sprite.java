package org.ics.flying_stars.game.engine.sprites;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.engine.canvas.Drawable;
import org.ics.flying_stars.game.engine.collision.*;
import org.ics.flying_stars.game.engine.collision.colliders.CircleCollider;
import org.ics.flying_stars.game.engine.collision.colliders.Collider;
import org.ics.flying_stars.game.engine.collision.colliders.LineCollider;

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
    public boolean detectElementaryCollision(CircleCollider otherCircleCollider) {
        return collider.detectElementaryCollision(otherCircleCollider);
    }

    @Override
    public boolean detectElementaryCollision(LineCollider otherLineCollider) {
        return collider.detectElementaryCollision(otherLineCollider);
    }


}