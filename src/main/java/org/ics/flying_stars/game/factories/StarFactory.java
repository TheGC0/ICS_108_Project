package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.entities.Star;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StarFactory implements Factory<Star> {
    private Vector2D[] vertices;

    public StarFactory() {
        vertices = new Vector2D[10];
        generateAStar();


//        temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
//        vertices[0] = temp;
//
//        currentAngle += (Math.PI / 5);
//        currentRadius -= 0.5;
//        temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
//        vertices[1] = temp;
//
//        currentAngle += (Math.PI / 5);
//        currentRadius += 0.5;
//        temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
//        vertices[2] = temp;
//
//
//        currentAngle += (Math.PI / 5);
//        currentRadius -= 0.5;
//        temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
//        vertices[3] = temp;
//
//        currentAngle += (Math.PI / 5);
//        currentRadius += 0.5;
//        temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
//        vertices[4] = temp;
//
//        currentAngle += (Math.PI / 5);
//        currentRadius -= 0.5;
//        temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
//        vertices[5] = temp;
//
//        currentAngle += (Math.PI / 5);
//        currentRadius += 0.5;
//        temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
//        vertices[6] = temp;
//
//        currentAngle += (Math.PI / 5);
//        currentRadius -= 0.5;
//        temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
//        vertices[7] = temp;
//
//        currentAngle += (Math.PI / 5);
//        currentRadius += 0.5;
//        temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
//        vertices[8] = temp;
//
//        currentAngle += (Math.PI / 5);
//        currentRadius -= 0.5;
//        temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
//        vertices[9] = temp;
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

        // TODO Generate random colors

        Vector2D[] copiedVertices = new Vector2D[10];
        for (int i=0; i<10; i++) {
            copiedVertices[i] = new Vector2D(vertices[i].getX(), vertices[i].getY());
        }
        return new Star(colors, copiedVertices);
    }
    public void generateAStar(){
        vertices = new Vector2D[10];
        double currentAngle = 0;
        double currentRadius = 100;
        Vector2D temp;

        for(int i = 0; i < vertices.length; i++){
            temp = Vector2D.radialVector2D(currentRadius, currentAngle);
            temp.setXY(temp.getX() + 200, temp.getY() + 200);
            vertices[i] = temp;
            currentAngle += (Math.PI / 5);
            if(i % 2 == 0){
                currentRadius -= 61.8034;
            }
            else{
                currentRadius += 61.8034;
            }
        }
    }
}
