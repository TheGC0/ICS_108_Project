package org.ics.flying_stars.game;

import javafx.scene.canvas.GraphicsContext;
import org.ics.flying_stars.engine.canvas.*;
import org.ics.flying_stars.engine.geometry.Vector2D;

public class HealthBar {

    private ColoredCircle[] healthBar;
    public static int health;

    public HealthBar(GraphicsContext graphicsContext) {
        startANewLife(graphicsContext);
    }

    public void startANewLife(GraphicsContext graphicsContext) {
        health = 3;
        healthBar = new ColoredCircle[health];
        for (int i = 0; i < health; i++) {
            healthBar[i] = new ColoredCircle(10,new Vector2D(100 + 30 *(i + 1), 30), Colour.RED);
            healthBar[i].draw(graphicsContext);
        }
    }

    public void takeDamage() {
        if (health > 0) {
            healthBar[health - 1].setColor(Colour.WHITE);
            health--;
        }
    }

    public void setHealthBar(ColoredCircle[] healthBar) {
        this.healthBar = healthBar;
    }

    public ColoredCircle[] getHealthBar() {
        return healthBar;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


}
