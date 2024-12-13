package org.ics.flying_stars.ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
// Abstract UI for menus
public abstract class AbstractMenu implements UI {
    // making the pane
    protected final VBox rootVBox;

    // Constructor
    public AbstractMenu() {
        rootVBox = new VBox();
        rootVBox.setAlignment(Pos.CENTER);
    }

    // To use the pane in the capsular
    public Parent getRoot() {
        return rootVBox;
    }
}
