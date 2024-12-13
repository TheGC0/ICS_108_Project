package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.geometry.Vector2D;

// Factory for generating radial velocities for points from a given center
public class VelocityFactory {

    // Given a center point and an array of vertices, generate velocities pointing away from the center with the given magnitude
    public static Vector2D[] generateVertexVelocities(Vector2D center, Vector2D[] vertices, double velocityMagnitude) {
        Vector2D[] velocities = new Vector2D[vertices.length];
        Vector2D tempVertex;
        double currentAngle;
        for (int i = 0; i < vertices.length; i++) {
            tempVertex = vertices[i];
            currentAngle = center.getUnitVectorFrom(tempVertex).getAngle();
            velocities[i] = Vector2D.radialVector2D(velocityMagnitude, currentAngle);
            }
        return velocities;
    }
}

