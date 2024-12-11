package org.ics.flying_stars.ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;


public abstract class AbstractUI{
    // making the pane
    protected final VBox rootVBox;

    // Constructor
    public AbstractUI() {
        rootVBox = new VBox();
        rootVBox.setAlignment(Pos.CENTER);
    }

    // To use the pane in the capsular
    public Parent getRoot() {
        return rootVBox;
    }
}
