package org.ics.flying_stars.game.collision;

import org.ics.flying_stars.game.geometry.Polygon;

public final class PolygonCollider implements Collidable {
    private final Polygon polygon;
    private final LineCollider[] lineColliders;

    public PolygonCollider(Polygon polygon) {
        this.polygon = polygon;
        this.lineColliders = new LineCollider[polygon.getEdges().length];
    }

    private void createLineColliders() {
        for (int i=0; i < lineColliders.length; i++) {
            LineCollider lineCollider = new LineCollider(polygon.getEdges()[i]);
            lineColliders[i] = lineCollider;
        }
    }

    public Polygon polygon() {
        return polygon;
    }

    @Override
    public boolean detectCollision(Collidable otherCollidable) {
        for (LineCollider collider: lineColliders) {
            if (collider.detectCollision(otherCollidable)) {
                return true;
            }
        }
        return false;
    }

}