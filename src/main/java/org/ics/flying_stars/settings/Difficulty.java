package org.ics.flying_stars.settings;

public enum Difficulty {

    EASY(1.75),
    MEDIUM(1.5),
    HARD(1.25),
    EXTREME(1);

    final double difficultyLevel;

    Difficulty(double difficulty) {
        this.difficultyLevel = difficulty;
    }

    public double getDifficultyLevel() {
        return difficultyLevel;
    }
}
