package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.entities.Star;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StarFactory implements Factory<Star> {
    private Vector2D[] vertices;
    private final Vector2D CENTER;
    private final double PHI = 1.618033988749894;

    public StarFactory(Vector2D CENTER) {
        vertices = new Vector2D[10];
        this.CENTER = CENTER;
        generateAStar();

    }

    public Vector2D[] getVertices() {
        return vertices;
    }

    public void setVertices(Vector2D[] vertices) {
        this.vertices = vertices;
    }

    @Override
    public Star create() {
        Colour[] colors = Colour.getShuffled();


        Vector2D[] copiedVertices = new Vector2D[10];
        for (int i=0; i<10; i++) {
            copiedVertices[i] = new Vector2D(vertices[i].getX(), vertices[i].getY());
        }
        return new Star(colors, copiedVertices);
    }
    public void generateAStar(){
        vertices = new Vector2D[10];
        double currentAngle = -Math.PI/2;
        double currentRadius = 1;
        Vector2D temp;

        for(int i = 0; i < vertices.length; i++){
            temp = Vector2D.radialVector2D(currentRadius, currentAngle);
            temp.setXY(temp.getX() + CENTER.getX(), temp.getY() + CENTER.getY());
            vertices[i] = temp;
            currentAngle += (Math.PI / 5);
            if(i % 2 == 0){
                currentRadius -= 1 / PHI;
            }
            else{
                currentRadius += 1 / PHI;
            }
        }
    }

    public Vector2D[] Velocities(double velocityMagnitude) {
        Vector2D[] velocities = new Vector2D[10];
        Vector2D tempVertix;
        double currentAngle = 0;
        for (int i = 0; i < vertices.length; i++) {
            tempVertix = vertices[i];
            currentAngle = this.CENTER.getUnitVectorFrom(tempVertix).getAngle();
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
