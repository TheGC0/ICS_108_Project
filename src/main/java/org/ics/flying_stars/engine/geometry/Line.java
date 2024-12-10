package org.ics.flying_stars.engine.geometry;

public class Line {
    private final Vector2D startPoint;
    private final Vector2D endPoint;

    public Line(Vector2D startPoint, Vector2D endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Vector2D startPoint() {
        return startPoint;
    }

    public Vector2D endPoint() {
        return endPoint;
    }
}
