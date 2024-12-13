package org.ics.flying_stars.settings;

// Enum for each difficulty that defines the time between each obstacle spawn
public enum Difficulty {

    EASY(1.75),
    MEDIUM(1.5),
    HARD(1.25),
    EXTREME(1);

    final double difficultyLevel;

    // private constructor for each difficulty
    Difficulty(double difficulty) {
        this.difficultyLevel = difficulty;
    }

    // Return the difficulty level which defines the time between each obstacle spawn
    public double getDifficultyLevel() {
        return difficultyLevel;
    }
}
