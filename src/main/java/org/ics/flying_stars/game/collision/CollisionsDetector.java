package org.ics.flying_stars.game.collision;

import org.ics.flying_stars.game.geometry.Circle;
import org.ics.flying_stars.game.geometry.Line;

public class CollisionsDetector {
    public static boolean detectCollision2Circles(Circle circle, Circle otherCircle) {
        // A mathematical check to detect circle collision (I do understand this :) )
        double distance = circle.getCenter().distanceFrom(otherCircle.getCenter());
        double radiiSum = 1.0 * circle.getRadius() + otherCircle.getRadius();

        return distance <= radiiSum;
    }

    public static boolean detectCollision2Lines(Line line, Line otherLine) {
        // A mathematical method to detect collision between 2 lines (I do understand this :) )

        // Find slopes
        int deltaX1 = line.endPoint().getX() - line.startPoint().getX();
        int deltaY1 = line.endPoint().getY() - line.startPoint().getY();

        double m1 = (1.0 * deltaY1) / deltaX1;

        int deltaX2 = otherLine.endPoint().getX() - otherLine.startPoint().getX();
        int deltaY2 = otherLine.endPoint().getY() - otherLine.startPoint().getY();

        double m2 = (1.0 * deltaY2) / deltaX2;

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

    public static boolean detectCollisionCircleLine(Circle circle, Line line) {
        // A mathematical method to detect collision between circle and line (I don't understand this :/)
        int deltaX = line.endPoint().getX() - line.startPoint().getX();
        int deltaY = line.endPoint().getY() - line.startPoint().getY();

        int deltaXCenter = line.startPoint().getX() - circle.getX();
        int deltaYCenter = line.startPoint().getY() - circle.getY();

        int a = deltaX * deltaX + deltaY * deltaY;
        int b = 2 * (deltaX * deltaXCenter + deltaY * deltaYCenter);
        int c = (circle.getX() * circle.getX() + circle.getY() * circle.getY()) +
                (line.startPoint().getX() * line.startPoint().getX()) +
                (line.startPoint().getY() * line.startPoint().getY()) -
                2 * (circle.getX() * line.startPoint().getX() + circle.getY() * line.startPoint().getY()) -
                (circle.getRadius() * circle.getRadius());

        int determinant = b * b - 4 * a * c;
        if (determinant < 0) {
            return false; // No intersection
        }

        double sqrtDet = Math.sqrt(determinant);
        double t0 = (-b + sqrtDet) / (2 * a);
        double t1 = (-b - sqrtDet) / (2 * a);

        return (t0 >= 0 && t0 <= 1) || (t1 >= 0 && t1 <= 1);

    }

}
