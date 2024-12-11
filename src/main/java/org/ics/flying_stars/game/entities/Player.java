package org.ics.flying_stars.game.entities;

import org.ics.flying_stars.engine.canvas.Colored;
import org.ics.flying_stars.engine.canvas.ColoredCircle;
import org.ics.flying_stars.engine.canvas.Colour;
import org.ics.flying_stars.engine.collision.colliders.colored.ColoredCircleCollider;
import org.ics.flying_stars.engine.geometry.Vector2D;
import org.ics.flying_stars.engine.sprites.Sprite;

public class Player extends Sprite implements Colored {
    public static final int SPEED_MULTIPLIER = 15;
    public static final int SIZE = 10;
    private final Vector2D mousePosition;
    private final ColoredCircle circle;
    private final ColoredCircleCollider coloredCircleCollider;
    private Colour colour;
    private boolean isInvincible = false;
    private double timeNeeded;


    public Player(Vector2D startPos, Colour color) {
        this.circle = new ColoredCircle(SIZE, startPos, color);
        this.coloredCircleCollider = new ColoredCircleCollider(circle, color);
        this.drawable = circle;

        colour = color;
        mousePosition = new Vector2D(0,0);

        setCollider(coloredCircleCollider);
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
        if(isInvincible && timeNeeded < System.currentTimeMillis()) {
            isInvincible = false;
        }
    }

    @Override
    public void setColor(Colour color) {
        coloredCircleCollider.setColor(color);
        circle.setColor(color);
        colour = color;

    }

    @Override
    public Colour getColor() {
        return colour;
    }

    public boolean isInvincible() { return isInvincible; }


    public void getHit(){
        isInvincible = true;
        double time = System.currentTimeMillis();
        timeNeeded = time + 5000;

        }

}
