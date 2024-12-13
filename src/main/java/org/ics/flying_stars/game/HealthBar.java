package org.ics.flying_stars.game;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.ics.flying_stars.ui.UI;


// Ui to show a health bar
public class HealthBar implements UI {
    private final Circle[] healthBar;
    private final HBox bar;
    public static int health;

    public HealthBar() {
        // Set health to
        health = 3;

        // Set root layout to an HBox
        bar = new HBox();

        // Create 3 red circles for each heart
        healthBar = new Circle[health];
        for (int i = 0; i < health; i++) {
            healthBar[i] = new Circle(10, Color.RED);
        }

        // Add the hearts to the root layout
        startANewLife();
    }

    // Reset health and hearts
    public void startANewLife() {
        health = 3;
        bar.getChildren().clear();
        for (int i = 0; i < health; i++) {
            bar.getChildren().add(healthBar[i]);
        }
    }

    // Reduce health and remove a heart from the layout
    public void takeDamage() {
        if (health > 0) {
            health--;
            bar.getChildren().remove(healthBar[health]);
        }
    }

    // Return current health
    public int getHealth() {
        return health;
    }

    @Override
    public Parent getRoot() {
        return bar;
    }
}
