package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.geometry.Vector2D;

public class VelocityFactory {

    public static Vector2D[] generateVertexVelocities(Vector2D center, Vector2D[] vertices, double velocityMagnitude, int sides) {
        Vector2D[] velocities = new Vector2D[sides];
        Vector2D tempVertix;
        double currentAngle;
        for (int i = 0; i < vertices.length; i++) {
            tempVertix = vertices[i];
            currentAngle = center.getUnitVectorFrom(tempVertix).getAngle();
            velocities[i] = Vector2D.radialVector2D(velocityMagnitude, currentAngle);
            }
        return velocities;
    }
}

