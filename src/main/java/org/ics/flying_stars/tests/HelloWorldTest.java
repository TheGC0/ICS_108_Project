package org.ics.flying_stars.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class HelloWorldTest extends Application {
    @Override
    public void start(Stage stage) {
        Label helloWorldLabel = new Label("Testing.... Hello World!");
        Scene scene = new Scene(helloWorldLabel);
        stage.setTitle("Hello World!");
        stage.setScene(scene);
        stage.setWidth(300);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
