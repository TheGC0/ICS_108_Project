package org.ics.flying_stars.game.engine.collision;

import org.ics.flying_stars.game.engine.geometry.Circle;
import org.ics.flying_stars.game.engine.geometry.Line;

/**
 * A mathematical helper class that detects collisions between basic geometry objects
 */
public class CollisionsDetector {
    /**
     * Detects if there is a collision between the 2 given circles
     * @param circle The circle
     * @param otherCircle The other circle
     * @return true if a collision is detected
     */
    public static boolean detectCollision2Circles(Circle circle, Circle otherCircle) {
        // A mathematical check to detect circle collision (I do understand this :) )
        double distance = circle.getCenter().distanceFrom(otherCircle.getCenter());
        double radiiSum = 1.0 * circle.getRadius() + otherCircle.getRadius();

        return distance <= radiiSum;
    }

    /**
     * Detects if there is a collision between the 2 given lines (segments)
     * @param line The line
     * @param otherLine The other line
     * @return true if a collision is detected
     */
    public static boolean detectCollision2Lines(Line line, Line otherLine) {
        // A mathematical method to detect collision between 2 lines (I do understand this :) )

        // Find slopes
        double deltaX1 = line.endPoint().getX() - line.startPoint().getX();
        double deltaY1 = line.endPoint().getY() - line.startPoint().getY();

        double m1 = (deltaY1) / deltaX1;

        double deltaX2 = otherLine.endPoint().getX() - otherLine.startPoint().getX();
        double deltaY2 = otherLine.endPoint().getY() - otherLine.startPoint().getY();

        double m2 = (deltaY2) / deltaX2;

        if (m1 == m2) {
            return false;
        }

        // Find intersection point
        double intersectionX = (m2 * otherLine.endPoint().getX() - m1 * line.endPoint().getX() +
                line.endPoint().getY() - otherLine.endPoint().getY()) / (m2 - m1);

        double intersectionY = m2 * (intersectionX - otherLine.endPoint().getX()) + otherLine.endPoint().getY();


        // Check if intersection point lies on both segments
        boolean onSegmentOne = (Math.min(line.startPoint().getX(), line.endPoint().getX()) <= intersectionX) &&
                (Math.max(line.startPoint().getX(), line.endPoint().getX()) >= intersectionX) &&
                (Math.min(line.startPoint().getY(), line.endPoint().getY()) <= intersectionY) &&
                (Math.max(line.startPoint().getY(), line.endPoint().getY()) >= intersectionY);

        boolean onSegmentTwo = (Math.min(otherLine.startPoint().getX(), otherLine.endPoint().getX()) <= intersectionX) &&
                (Math.max(otherLine.startPoint().getX(), otherLine.endPoint().getX()) >= intersectionX) &&
                (Math.min(otherLine.startPoint().getY(), otherLine.endPoint().getY()) <= intersectionY) &&
                (Math.max(otherLine.startPoint().getY(), otherLine.endPoint().getY()) >= intersectionY);


        return onSegmentOne && onSegmentTwo;
    }

    /**
     * Detects if there is a collision between the given circle and line
     * @param circle The circle
     * @param line The line (segment)
     * @return true if a collision is detected
     */
    public static boolean detectCollisionCircleLine(Circle circle, Line line) {
        // A mathematical method to detect collision between circle and line (I don't understand this :/)
        // Used long due to overflow issues
        double deltaX = line.endPoint().getX() - line.startPoint().getX();
        double deltaY = line.endPoint().getY() - line.startPoint().getY();

        double deltaXCenter = line.startPoint().getX() - circle.getX();
        double deltaYCenter = line.startPoint().getY() - circle.getY();

        double a = deltaX * deltaX + deltaY * deltaY;
        double b = 2 * (deltaX * deltaXCenter + deltaY * deltaYCenter);
        double c =  deltaXCenter * deltaXCenter + deltaYCenter * deltaYCenter - circle.getRadius() * circle.getRadius();

        double determinant = b * b - 4 * a * c;
        if (determinant < 0) {
            return false; // No intersection
        }

        double sqrtDet = Math.sqrt(determinant);
        double t0 = (-b + sqrtDet) / (2 * a);
        double t1 = (-b - sqrtDet) / (2 * a);

        return (t0 >= 0 && t0 <= 1) || (t1 >= 0 && t1 <= 1);
    }

}
