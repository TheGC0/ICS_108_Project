package org.ics.flying_stars.engine.canvas;

import javafx.scene.paint.Color;
import org.ics.flying_stars.settings.Difficulty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Colour {
    BLUE(Color.BLUE),
    GREEN(Color.DARKGREEN),
    RED(Color.RED),
    LIME(Color.LIME),
    PURPLE(Color.PURPLE),
    CYAN(Color.CYAN),
    WHITE(Color.WHITE),
    YELLOW(Color.YELLOW),
    ORANGE(Color.ORANGE),
    BURLYWOOD(Color.BURLYWOOD);

    final Color color;
    static Colour[] colors = new Colour[10];

    Colour(Color color){
        this.color = color;
    }

    public static Colour[] getShuffled(){
        List<Colour> shuffledColors = Arrays.asList(Colour.values());
        Collections.shuffle(shuffledColors);
        colors = shuffledColors.toArray(new Colour[0]);
        return colors;
        }
    }

