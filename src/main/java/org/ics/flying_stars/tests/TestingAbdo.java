package org.ics.flying_stars.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ics.flying_stars.ui.LosingScreenUI;


public class TestingAbdo extends Application {

    @Override
    public void start(Stage stage) {
        LosingScreenUI losingScreenUI = new LosingScreenUI();
        losingScreenUI.tryingButton().setOnAction(event -> {
            System.out.println("Button pressed");

        });
        Scene scene = new Scene(losingScreenUI.getRoot(), 1920, 1000);
        stage.setScene(scene);
        stage.setTitle("Testing");
        stage.show();


    }

    public static void main(String[] args) {launch(args);}


}
