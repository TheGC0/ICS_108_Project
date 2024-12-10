package org.ics.flying_stars.engine.collision.colliders;

import org.ics.flying_stars.engine.collision.Collidable;
import org.ics.flying_stars.engine.collision.CollisionHandler;
import org.ics.flying_stars.engine.collision.CollisionTranscript;

import java.util.ArrayList;

public abstract class Collider implements Collidable {
    protected final ArrayList<CollisionHandler> collisionHandlers;
    protected CollisionHandler collisionsTranscriptBuilder;

    protected Collider() {
        collisionHandlers = new ArrayList<>();
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
