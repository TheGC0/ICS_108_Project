package org.ics.flying_stars.engine.sprites;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.canvas.Drawable;
import org.ics.flying_stars.engine.collision.*;
import org.ics.flying_stars.engine.collision.colliders.CircleCollider;
import org.ics.flying_stars.engine.collision.colliders.Collider;
import org.ics.flying_stars.engine.collision.colliders.LineCollider;

import java.util.ArrayList;


/**
 * Abstract class for sprites; game objects that can be moved and collided with
 */
public abstract class Sprite implements Drawable, Collidable, Movable {
    private final ArrayList<CollisionHandler> collisionHandlers;
    private CollisionHandler collisionsTranscriptBuilder;
    protected Collider collider;
    protected Drawable drawable;

    public Sprite() {
        collisionHandlers = new ArrayList<>();
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
        // Connect collider
        collider.setCollisionTranscriptBuilder(this::buildCollisionTranscript);
        collider.addCollisionHandler(this::handleCollision);
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

    @Override
    public void addCollisionHandler(CollisionHandler handler) {
        collisionHandlers.add(handler);
    }

    @Override
    public void handleCollision(CollisionTranscript collisionTranscript) {
        for (CollisionHandler collisionHandler: collisionHandlers) {
            collisionHandler.handle(collisionTranscript);
        }
    }

    @Override
    public void setCollisionTranscriptBuilder(CollisionHandler builder) {
        collisionsTranscriptBuilder = builder;
    }

    @Override
    public void buildCollisionTranscript(CollisionTranscript collisionTranscript) {
        collisionTranscript.setHead(this);
        if (collisionsTranscriptBuilder != null) {
            collisionsTranscriptBuilder.handle(collisionTranscript);
        }
    }


}