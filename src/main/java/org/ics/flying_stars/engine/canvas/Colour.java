package org.ics.flying_stars.engine.canvas;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Colour {
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

    Colour(Color color){
        this.color = color;
    }

    public static Colour[] getShuffled(){
        List<Colour> shuffledColors = Arrays.asList(Colour.values());
        Collections.shuffle(shuffledColors);
        return shuffledColors.toArray(new Colour[0]);
    }

}
