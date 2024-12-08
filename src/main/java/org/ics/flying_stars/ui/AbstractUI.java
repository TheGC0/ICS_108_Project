package org.ics.flying_stars.ui;

import javafx.scene.Parent;


public abstract class AbstractUI{
    protected Parent root;
    protected final Double SCREEN_WIDTH = 800.0;
    protected final Double SCREEN_HEIGHT = 600.0;

    public AbstractUI() {
        root = null;
    }

    public Parent getRoot() {
        return root;
    }
}
