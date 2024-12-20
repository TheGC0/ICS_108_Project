package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.entities.FlyingObstacle;

// Abstract class for obstacle factories (spawners)
public abstract class ObstacleFactory {
    protected final Vector2D center;

    public ObstacleFactory(Vector2D center) {
        this.center = center;
    }

    // Create an obstacle
    public FlyingObstacle create(double angle, double velocityMagnitude) {
        // Randomise edge colors
        Colour[] colors = Colour.getShuffled();

        return create(angle, velocityMagnitude, colors);
    }

    // Create an obstacle given the colors
    public FlyingObstacle create(double angle, double velocityMagnitude, Colour[] colors) {
        // Generate vertices
        Vector2D[] vertices = generateVertices(angle);

        // Create obstacle and set velocities
        FlyingObstacle flyingObstacle = new FlyingObstacle(colors, vertices);
        flyingObstacle.setVelocities(generateVertexVelocities(vertices, velocityMagnitude));

        return flyingObstacle;
    }



    protected abstract Vector2D[] generateVertices(double angle);

    // Generate velocities for each vertex
    protected Vector2D[] generateVertexVelocities(Vector2D[] vertices, double velocityMagnitude) {
        return (VelocityFactory.generateVertexVelocities(center, vertices, velocityMagnitude));
    }
}
