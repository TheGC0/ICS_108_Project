package org.ics.flying_stars.game.entities;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.game.engine.canvas.ColorableLine;
import org.ics.flying_stars.game.engine.canvas.Colour;
import org.ics.flying_stars.game.engine.collision.colliders.PolygonCollider;
import org.ics.flying_stars.game.engine.collision.colliders.colored.MultipleColoredPolygonCollider;
import org.ics.flying_stars.game.engine.geometry.Point;
import org.ics.flying_stars.game.engine.geometry.Polygon;
import org.ics.flying_stars.game.engine.geometry.Vector2D;
import org.ics.flying_stars.game.engine.sprites.Sprite;

public class Star extends Sprite{
    private Vector2D[] velocities;
    public ColorableLine[] lines;
    public Colour[] colors;
    private Point[] vertices;

    public Star(Colour[] colors,Point[] points) {

        this.vertices = points;
        this.colors = colors;
        this.lines = new ColorableLine[points.length];
        this.velocities = new Vector2D[points.length];

        Polygon polygon = new Polygon(points);
        this.collider = new PolygonCollider(polygon);

        for (int i = 0; i < polygon.getEdges().length; i++)
            lines[i] = new ColorableLine(polygon.getEdges()[i], colors[i]);
    }

    @Override
    public void draw(GraphicsContext context) {
        for(ColorableLine line : lines) {
            line.draw(context);
        }
    }

    @Override
    public void move(int physicsFrames) {
        for(int i = 0; i < lines.length; i++) {
            if (vertices[i] == null) {
                continue;
            } else {
                vertices[i].setX(vertices[i].getX() + velocities[i].getX() / physicsFrames);
                vertices[i].setY(vertices[i].getY() + velocities[i].getY() / physicsFrames);
            }
        }
    }


    public Vector2D[] getVelocities() {
        return velocities;
    }

    public void setVelocities(Vector2D[] velocities) {
        this.velocities = velocities;

    }
}
