package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.entities.FlyingObstacle;

public abstract class AbstractObstacleFactory {
    protected final Vector2D center;

    public AbstractObstacleFactory(Vector2D center) {
        this.center = center;
    }

    public FlyingObstacle create(double angle, double velocityMagnitude) {
        Colour[] colors = Colour.getShuffled();

        Vector2D[] vertices = generateVertices(angle);

        FlyingObstacle flyingObstacle = new FlyingObstacle(colors, vertices);
        flyingObstacle.setVelocities(generateVertexVelocities(vertices, velocityMagnitude));

        return flyingObstacle;
    }

    protected abstract Vector2D[] generateVertices(double angle);

    protected Vector2D[] generateVertexVelocities(Vector2D[] vertices, double velocityMagnitude) {
        return (VelocityFactory.generateVertexVelocities(center, vertices, velocityMagnitude));
    }
}
