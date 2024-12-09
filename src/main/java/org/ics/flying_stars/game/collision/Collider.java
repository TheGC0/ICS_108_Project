package org.ics.flying_stars.game.collision;

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
    public void handleCollision(Collidable otherCollidable) {
        for (CollisionHandler collisionHandler: collisionHandlers) {
            collisionHandler.handle(otherCollidable);
        }
    }
}
