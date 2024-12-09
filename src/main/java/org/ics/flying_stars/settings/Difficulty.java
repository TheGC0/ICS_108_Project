package org.ics.flying_stars.settings;

public enum Difficulty {

    EASY(1),
    MEDIUM(2),
    HARD(3),
    EXTREME(4);

    final int difficultyLevel;

    Difficulty(int difficulty) {
        this.difficultyLevel = difficulty;
    }
}
