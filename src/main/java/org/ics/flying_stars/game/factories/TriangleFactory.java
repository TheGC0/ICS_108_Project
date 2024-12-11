package org.ics.flying_stars.game.factories;

import org.ics.flying_stars.engine.geometry.Vector2D;

public class TriangleFactory extends AbstractObstacleFactory {

    public TriangleFactory(Vector2D center) {
        super(center);
    }

    @Override
    protected Vector2D[] generateVertices(double angle) {;
        Vector2D temp;
        Vector2D[] vertices = new Vector2D[3];
        for(int i = 0; i < vertices.length; i++){
            temp = Vector2D.radialVector2D(1, angle);
            temp.setXY(temp.getX() + center.getX(), temp.getY() + center.getY());
            vertices[i] = temp;
            angle += (Math.PI * 2 / 3);

        }
        return vertices;
    }
}
