package org.ics.flying_stars.engine.geometry;

public class Line {
    private final Point startPoint;
    private final Point endPoint;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point startPoint() {
        return startPoint;
    }

    public Point endPoint() {
        return endPoint;
    }
}
