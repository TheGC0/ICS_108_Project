package org.ics.flying_stars.engine.geometry;

public class Circle {
    private final Vector2D center;
    private int radius;

    public Circle(int radius, Vector2D center) {
        this.radius = radius;
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Vector2D getCenter() {
        return center;
    }

    public double getX() {
        return center.getX();
    }

    public void setX(int x) {
        center.setX(x);
    }

    public double getY() {
        return center.getY();
    }

    public void setY(int y) {
        center.setY(y);
    }
}
