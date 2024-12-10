package org.ics.flying_stars.engine.geometry;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D radialVector2D(double radius, double angle) {
        return new Vector2D(Math.cos(angle) * radius, Math.sin(angle) * radius);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public void scale(double scale) {
        x *= scale;
        y *= scale;
    }

    public double getRadius() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    public double getAngle() {
        return Math.atan2(y, x);
    }

    public Vector2D unitVector() {
        return radialVector2D(1, getAngle());
    }

    public Point toPoint() {
        return new Point(x, y);
    }
}
