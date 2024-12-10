package org.ics.flying_stars.game.entities;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.canvas.ColoredLine;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.collision.colliders.colored.MultipleColoredPolygonCollider;
import org.ics.flying_stars.engine.geometry.Polygon;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.engine.sprites.Sprite;

public class FlyingStar extends Sprite{
    private final Vector2D[] vertices;
    private final ColoredLine[] lines;
    private Vector2D[] velocities;
    public Colour[] colors;

    public FlyingStar(Colour[] colors, Vector2D[] points) {
        this.vertices = points;
        this.colors = colors;
        this.lines = new ColoredLine[points.length];
        this.velocities = new Vector2D[points.length];

        Polygon starPolygon = new Polygon(points);
        setCollider(new MultipleColoredPolygonCollider(starPolygon , colors));

        for (int i = 0; i < starPolygon.getEdges().length; i++)
            lines[i] = new ColoredLine(starPolygon .getEdges()[i], colors[i]);
    }

    @Override
    public void draw(GraphicsContext context) {
        for(ColoredLine line : lines) {
            line.draw(context);
        }
    }

    @Override
    public void move(int physicsFrames) {
        for(int i = 0; i < lines.length; i++) {
            if (velocities[i] != null) {
                vertices[i].setX(vertices[i].getX() + velocities[i].getX() / physicsFrames);
                vertices[i].setY(vertices[i].getY() + velocities[i].getY() / physicsFrames);
            }
        }
    }
    public Vector2D[] getVertices() {
        return vertices;
    }
    public Vector2D[] getVelocities() {
        return velocities;
    }

    public void setVelocities(Vector2D[] velocities) {
        this.velocities = velocities;

    }
}
