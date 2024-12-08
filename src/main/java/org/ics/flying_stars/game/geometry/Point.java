package org.ics.flying_stars.game.geometry;

/**
 * A class that stores the information of a 2D point
 */
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setXY(double x, double y) {
        setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Calculates and returns the distance from another point
     * @param otherPoint The other point to calculate distance from
     * @return The distance between this point and the other point
     */
    public double distanceFrom(Point otherPoint) {
        // Distance formula
       double deltaX = otherPoint.x - x;
       double deltaY = otherPoint.y - y;
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    public Vector2D getUnitVectorFrom(Point otherPoint){
        return new Vector2D(otherPoint.x - x, otherPoint.y - y).unitVector();
    }

}
