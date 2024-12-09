package org.ics.flying_stars.settings;

public class Settings {
    private Difficulty difficulty = Difficulty.EASY;
    private Shape shape;

    public Settings() {
        this.difficulty = Difficulty.MEDIUM;
        this.shape = Shape.Star;
    }


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
