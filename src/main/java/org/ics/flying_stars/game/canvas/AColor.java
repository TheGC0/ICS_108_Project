package org.ics.flying_stars.game.canvas;

import javafx.scene.paint.Color;

public enum AColor {
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    PURPLE(Color.PURPLE),
    CYAN(Color.CYAN),
    BROWN(Color.BROWN),
    BLACK(Color.BLACK),
    WHITE(Color.WHITE),
    GRAY(Color.GRAY),;

    final Color color;

    AColor(Color color){
        this.color = color;
    }


}
