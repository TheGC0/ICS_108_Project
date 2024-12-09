package org.ics.flying_stars.game.engine.collision;

public class CollisionTranscript {
    private final Collidable origin;
    private Collidable head;
    private CollisionTranscript linkedTranscript;

    public CollisionTranscript(Collidable origin) {
        this.origin = origin;
        this.head = origin;
        this.linkedTranscript = null;
    }

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

    public void setLinkedTranscript(CollisionTranscript linkedTranscript) {
        this.linkedTranscript = linkedTranscript;
    }

}

