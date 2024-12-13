package org.ics.flying_stars.engine.collision;

// Functional interface for collision handler lambdas
public interface CollisionHandler {
    void handle(CollisionTranscript collisionTranscript);
}
