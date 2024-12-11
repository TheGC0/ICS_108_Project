package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.geometry.Vector2D;

public class RandomPolygonFactory extends AbstractObstacleFactory{
    private final int SIDES = (int)(Math.random()*2) * 2 + 4;
    public final double PHI = 1.618033988749894;

    public RandomPolygonFactory(Vector2D center) {
        super(center);
    }

    @Override
    protected Vector2D[] generateVertices(double angle) {
        double currentRadius = 1;

        Vector2D temp;
        Vector2D[] vertices = new Vector2D[SIDES];
        for(int i = 0; i < vertices.length; i++){
            temp = Vector2D.radialVector2D(currentRadius, angle);
            temp.setXY(temp.getX() + center.getX(), temp.getY() + center.getY());
            vertices[i] = temp;
            angle += (2 * Math.PI / SIDES);
            if(i % 2 == 0){
                currentRadius -= 1 / PHI;
            }
            else{
                currentRadius += 1 / PHI;
            }
        }
        return vertices;
    }

    @Override
    protected Vector2D[] generateVertexVelocities(Vector2D[] vertices, double velocityMagnitude) {
        Vector2D[] velocities = VelocityFactory.generateVertexVelocities(center, vertices, velocityMagnitude, SIDES);
        for (int i = 0; i < velocities.length; i++) {
            if (i % 2 != 0) {
                velocities[i].scale(1 / Math.pow(PHI, 20 / SIDES));
            }
        }
        return velocities;
    }
}
