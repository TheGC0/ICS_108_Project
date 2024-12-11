package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public abstract class AbstractUI{
    // making the pane
    protected final GridPane rootGridPane;

    // Constructor
    public AbstractUI(String mainTitle) {

        rootGridPane = new GridPane();

        // Setting dimensions
        rootGridPane.setPadding(new Insets(100,100,100,100));
        rootGridPane.setHgap(50);
        rootGridPane.setVgap(50);

        // Setting the main message for the ui
        Label message = new Label(mainTitle);
        rootGridPane.add(message, 1, 0);
    }

    // To use the pane in the capsular
    public Parent getRoot() {
        return rootGridPane;
    }
}
