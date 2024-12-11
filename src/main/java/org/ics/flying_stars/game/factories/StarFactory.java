package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.geometry.Vector2D;

public class StarFactory extends AbstractObstacleFactory{
    public final double PHI = 1.618033988749894;

    public StarFactory(Vector2D center) {
        super(center);
    }

    @Override
    protected Vector2D[] generateVertices(double angle) {
        double currentRadius = 1;

        Vector2D temp;
        Vector2D[] vertices = new Vector2D[10];
        for(int i = 0; i < vertices.length; i++){
            temp = Vector2D.radialVector2D(currentRadius, angle);
            temp.setXY(temp.getX() + center.getX(), temp.getY() + center.getY());
            vertices[i] = temp;
            angle += (Math.PI / 5);
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
        Vector2D[] velocities = VelocityFactory.generateVertexVelocities(center, vertices, velocityMagnitude);
        for (int i = 0; i < velocities.length; i++) {
            if (i % 2 != 0) {
                velocities[i].scale(1 / Math.pow(PHI, 2));
            }
        }
        return velocities;
    }
}
