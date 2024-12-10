package org.ics.flying_stars.game;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.canvas.*;
import org.ics.flying_stars.engine.geometry.Vector2D;

public class HealthBar implements Drawable {

    private ColoredCircle[] healthBar;
    public static int health;

    public HealthBar() {
        startANewLife();
    }

    public void startANewLife() {
        health = 3;
        healthBar = new ColoredCircle[health];
        for (int i = 0; i < health; i++) {
            healthBar[i] = new ColoredCircle(10,new Vector2D(100 + 30 *(i + 1), 30), Colour.RED);
        }
    }

    public void takeDamage() {
        if (health > 0) {
            health--;
        }
    }


    public int getHealth() {
        return health;
    }


    @Override
    public void draw(GraphicsContext context) {
        for (int i = 0; i < health; i++) {
            healthBar[i].draw(context);
        }
    }
}
