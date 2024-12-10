package org.ics.flying_stars.game.entities;

import org.ics.flying_stars.engine.canvas.Colored;
import org.ics.flying_stars.engine.canvas.ColoredCircle;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.collision.colliders.colored.ColoredCircleCollider;
import org.ics.flying_stars.engine.geometry.Point;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.engine.sprites.Sprite;

public class Player extends Sprite implements Colored {
    public static final int SPEED_MULTIPLIER = 10;
    public static final int SIZE = 10;
    private final Point mousePosition;
    private final ColoredCircle circle;
    private Colour colour;


    public Player(Point startPos, Colour color) {
        this.circle = new ColoredCircle(SIZE, startPos, color);
        setCollider(new ColoredCircleCollider(circle, color));
        this.drawable = circle;

        colour = color;
        mousePosition = new Point(0,0);
    }

    public void setMousePos(double x, double y) {
        mousePosition.setXY(x, y);
    }

    @Override
    public void move(int physicsFrames) {
        Vector2D velocity = circle.getCenter().getUnitVectorFrom(mousePosition);
        double scale = mousePosition.distanceFrom(circle.getCenter()) / physicsFrames * SPEED_MULTIPLIER;
        velocity.scale(scale);
        circle.getCenter().setXY(velocity.getX() + circle.getX(), (velocity.getY() + circle.getY()));

    }

    @Override
    public void setColor(Colour color) {
        colour = color;
    }

    @Override
    public Colour getColor() {
        return colour;
    }
}
