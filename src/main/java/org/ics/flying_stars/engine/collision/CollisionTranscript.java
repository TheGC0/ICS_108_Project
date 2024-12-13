package org.ics.flying_stars.engine.collision;


/**
 * A class that holds all information regarding a collision between 2 collidables
 * Points to another collision transcript which also points back to this collision transcript
 * Each transcript only has details of one "entity"
 * Each transcript details the origin of collision (origin) and the main collided object (head)
 */
public class CollisionTranscript {
    private final Collidable origin;
    private Collidable head;
    private CollisionTranscript linkedTranscript;

    public CollisionTranscript(Collidable origin) {
        this.origin = origin;
        this.head = origin;
        this.linkedTranscript = null;
    }

    // Setters and getters
    public Collidable getOrigin() {
        return origin;
    }

    public Collidable getHead() {
        return head;
    }

    public void setHead(Collidable head) {
        this.head = head;
    }

    public CollisionTranscript getLinkedTranscript() {
        return linkedTranscript;
    }

    // Connect to another transcript
    public void setLinkedTranscript(CollisionTranscript linkedTranscript) {
        this.linkedTranscript = linkedTranscript;
        linkedTranscript.linkedTranscript = this;
    }

}

