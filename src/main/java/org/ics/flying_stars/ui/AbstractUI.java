package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public abstract class AbstractUI{
    protected GridPane rootGridPane;
    public AbstractUI(String mainTitle) {
        rootGridPane = new GridPane();
        rootGridPane.setPadding(new Insets(100,100,100,100));
        rootGridPane.setHgap(50);
        rootGridPane.setVgap(50);

        Label message = new Label(mainTitle);
        rootGridPane.add(message, 1, 0);
    }

    public Parent getRoot() {
        return rootGridPane;
    }
}
