package org.ics.flying_stars.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LosingScreenUI extends AbstractUI{
    @Override
    public void addButton(java.awt.Button button) {

    }

    public LosingScreenUI(String title, String imagePath) {
        super(title, imagePath);
    }

    @Override
    public void addButton(Button button){

    }

    public void start(Stage stage){

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);

        Label losingMessage = new Label("You lost!");
        losingMessage.setStyle("-fx-text-fill: red;");



    }
}
