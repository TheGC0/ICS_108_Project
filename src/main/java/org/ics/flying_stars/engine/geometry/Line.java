package org.ics.flying_stars.engine.geometry;

// Line class that connects 2 points
public class Line {
    private final Vector2D startPoint;
    private final Vector2D endPoint;

    public Line(Vector2D startPoint, Vector2D endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    // Getters
    public Vector2D startPoint() {
        return startPoint;
    }

    public Vector2D endPoint() {
        return endPoint;
    }
}
