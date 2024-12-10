package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.geometry.Point;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.game.entities.Star;

public class StarFactory implements Factory<Star> {
    private Point[] vertices;

    public StarFactory() {
        vertices = new Point[10];
        double currentAngle = 0;
        double currentRadius = 100;
        Point temp;

        for(int i = 0; i < vertices.length; i++){
            temp = Vector2D.radialVector2D(currentRadius, currentAngle).toPoint();
            temp.setXY(temp.getX() + 200, temp.getY() + 200);
            vertices[i] = temp;
            currentAngle += (Math.PI / 5);
            if(i % 2 == 0){
                currentRadius -= 50;
            }
            else{
                currentRadius += 50;
            }
        }


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

    public Point[] getVertices() {
        return vertices;
    }

    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }

    @Override
    public Star create() {
        Colour[] colors = new Colour[]{
                Colour.RED,
                Colour.BLACK,
                Colour.GREEN,
                Colour.RED,
                Colour.RED,
                Colour.BLUE,
                Colour.RED,
                Colour.RED,
                Colour.BROWN,
                Colour.RED};

        // TODO Generate random colors
        Point[] copiedVertices = new Point[10];
        for (int i=0; i<10; i++) {
            copiedVertices[i] = new Point(vertices[i].getX(), vertices[i].getY());
        }
        return new Star(colors, copiedVertices);
    }
}
