package org.ics.flying_stars.game.canvas;

import javafx.scene.canvas.GraphicsContext;

/**
 * An interface for objects that can be drawn on a canvas
 */
public interface Drawable {

    /**
     * Draw this object on a canvas
     * @param context The canvas's graphics context to draw this Drawable on
     */
    void draw(GraphicsContext context);
}
