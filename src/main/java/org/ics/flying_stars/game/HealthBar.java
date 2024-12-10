package org.ics.flying_stars.game;

import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.ics.flying_stars.ui.UI;


public class HealthBar implements UI {

    private final Circle[] healthBar;
    private final HBox bar;
    public static int health;

    public HealthBar() {
        health = 3;
        bar = new HBox();
        healthBar = new Circle[health];
        for (int i = 0; i < health; i++) {
            healthBar[i] = new Circle(10, Color.RED);
        }
        startANewLife();
    }

    public void startANewLife() {
        health = 3;
        bar.getChildren().clear();
        for (int i = 0; i < health; i++) {
            bar.getChildren().add(healthBar[i]);
        }
    }

    public void takeDamage() {
        if (health > 0) {
            health--;
            bar.getChildren().remove(healthBar[health]);
        }
    }


    public int getHealth() {
        return health;
    }

    @Override
    public Parent getRoot() {
        return bar;
    }
}
