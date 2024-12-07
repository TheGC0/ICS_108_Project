package org.ics.flying_stars.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LosingScreenUI extends AbstractUI{


    public LosingScreenUI(){
        super();

    }

    @Override
    public void addButtons(){

    }

    @Override
    public void setMainLabel(Label label){

    }

    public void start(Stage stage){

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);

        Label losingMessage = new Label("You lost!");
        losingMessage.setStyle("-fx-text-fill: red;");



    }
}
