package org.ics.flying_stars.engine.canvas;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Enum for allowed colors in this game
 */
public enum Colour {
    BLUE(Color.BLUE),
    GREEN(Color.DARKGREEN),
    RED(Color.RED),
    LIGHTGREEN(Color.LIGHTGREEN),
    PURPLE(Color.PURPLE),
    CYAN(Color.CYAN),
    WHITE(Color.WHITE),
    YELLOW(Color.YELLOW),
    ORANGE(Color.ORANGE),
    CHOCOLATE(Color.CHOCOLATE);

    final Color color;
    static Colour[] colors = new Colour[10];

    Colour(Color color){
        this.color = color;
    }

    // Get a shuffled array of colors
    public static Colour[] getShuffled(){
        List<Colour> shuffledColors = Arrays.asList(Colour.values());
        Collections.shuffle(shuffledColors);
        colors = shuffledColors.toArray(new Colour[0]);
        return colors;
        }
    }

