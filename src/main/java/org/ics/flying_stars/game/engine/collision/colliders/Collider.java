package org.ics.flying_stars.game.engine.collision.colliders;

import org.ics.flying_stars.game.engine.collision.Collidable;
import org.ics.flying_stars.game.engine.collision.CollisionHandler;
import org.ics.flying_stars.game.engine.collision.CollisionTranscript;

import java.util.ArrayList;

public abstract class Collider implements Collidable {
    protected final ArrayList<CollisionHandler> collisionHandlers;

    protected Collider() {
        collisionHandlers = new ArrayList<>();
    }

    @Override
    public void addCollisionHandler(CollisionHandler handler) {
        collisionHandlers.add(handler);
    }

    @Override
    public void handleCollision(CollisionTranscript collisionTranscript) {
        collisionTranscript.setHead(this);
        for (CollisionHandler collisionHandler: collisionHandlers) {
            collisionHandler.handle(collisionTranscript);
        }
    }
}
