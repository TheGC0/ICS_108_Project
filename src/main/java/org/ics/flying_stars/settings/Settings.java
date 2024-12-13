package org.ics.flying_stars.settings;

// Settings class that stores the different game settings (difficulty and shape)
public class Settings {
    private Difficulty difficulty;
    private Shape shape;

    // Default settings
    public Settings() {
        this.difficulty = Difficulty.MEDIUM;
        this.shape = Shape.Star;
    }

    // Setters and getter for each setting

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
