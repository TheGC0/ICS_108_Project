package org.ics.flying_stars.game.geometry;

public class Circle {
    private final Point center;
    private int radius;

    public Circle(int radius, Point center) {
        this.radius = radius;
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Point getCenter() {
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
