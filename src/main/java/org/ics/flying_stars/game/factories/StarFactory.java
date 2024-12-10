package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.entities.FlyingStar;

public class StarFactory {
    public final double PHI = 1.618033988749894;
    private final Vector2D center;

    public StarFactory(Vector2D center) {
        this.center = center;
    }

    public FlyingStar create(double angle, double velocityMagnitude) {
        Colour[] colors = Colour.getShuffled();

        Vector2D[] vertices = generateStarVertices(angle);

        FlyingStar star = new FlyingStar(colors, vertices);
        star.setVelocities(generateStarVertexVelocities(vertices, velocityMagnitude));

        return star;
    }

    private Vector2D[] generateStarVertices(double angle) {;
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

    private Vector2D[] generateStarVertexVelocities(Vector2D[] vertices, double velocityMagnitude) {
        Vector2D[] velocities = new Vector2D[10];
        Vector2D tempVertix;
        double currentAngle;
        for (int i = 0; i < vertices.length; i++) {
            tempVertix = vertices[i];
            currentAngle = this.center.getUnitVectorFrom(tempVertix).getAngle();
            if(i % 2 == 0){
                velocities[i] = Vector2D.radialVector2D(velocityMagnitude, currentAngle);
            }
            else{
                velocities[i] = Vector2D.radialVector2D(velocityMagnitude / Math.pow(PHI, 2), currentAngle);
            }
        }
        return velocities;
    }
}
