package org.ics.flying_stars.game.entities;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.canvas.ColoredLine;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.collision.colliders.colored.MultipleColoredPolygonCollider;
import org.ics.flying_stars.engine.geometry.Polygon;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.engine.sprites.Sprite;

// Class for all obstacles that emerge in the game
public class FlyingObstacle extends Sprite {
    private final Vector2D[] vertices;
    private final ColoredLine[] lines;
    private Vector2D[] velocities;
    public final Colour[] colors;

    // Define each point and line color of this obstacle
    public FlyingObstacle(Colour[] colors, Vector2D[] points) {
        this.vertices = points;
        this.colors = colors;
        this.lines = new ColoredLine[points.length];
        this.velocities = new Vector2D[points.length];

        Polygon polygon = new Polygon(points);
        setCollider(new MultipleColoredPolygonCollider(polygon , colors));

        for (int i = 0; i < polygon.getEdges().length; i++)
            lines[i] = new ColoredLine(polygon.getEdges()[i], colors[i]);
    }

    @Override
    public void draw(GraphicsContext context) {
        // Draw each line
        for(ColoredLine line : lines) {
            line.draw(context);
        }
    }

    @Override
    public void move(int physicsFrames) {
        // Move each vertex according to its respective velocity
        for(int i = 0; i < lines.length; i++) {
            if (velocities[i] != null) {
                vertices[i].setX(vertices[i].getX() + velocities[i].getX() / physicsFrames);
                vertices[i].setY(vertices[i].getY() + velocities[i].getY() / physicsFrames);
            }
        }
    }

    // Set the velocities of each point
    public void setVelocities(Vector2D[] velocities) {
        this.velocities = velocities;

    }

    public Vector2D[] getVertices() {
        return vertices;
    }
    public Vector2D[] getVelocities() {
        return velocities;
    }

}
