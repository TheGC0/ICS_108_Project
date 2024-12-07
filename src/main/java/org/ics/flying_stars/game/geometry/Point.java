package org.ics.flying_stars.game.geometry;

/**
 * A class that stores the information of a 2D point
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setXY(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Calculates and returns the distance from another point
     * @param otherPoint The other point to calculate distance from
     * @return The distance between this point and the other point
     */
    public double distanceFrom(Point otherPoint) {
        // Distance formula
        int deltaX = otherPoint.x - x;
        int deltaY = otherPoint.y - y;
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }
}
