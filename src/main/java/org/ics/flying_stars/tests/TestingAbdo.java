package org.ics.flying_stars.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ics.flying_stars.ui.LosingScreenUI;
import org.ics.flying_stars.ui.MenuUI;
import org.ics.flying_stars.ui.PauseMenuUI;
import org.ics.flying_stars.ui.SettingsUI;

// testing if the one piece is realll!!!!!!!!
public class TestingAbdo extends Application {

    @Override
    public void start(Stage stage) {
        MenuUI menu = new MenuUI();
        Scene scene = new Scene(menu.getRoot(), 500, 600);

        stage.setScene(scene);
        stage.setTitle("Testing");
        stage.show();


        menu.settingsButton().setOnAction(event -> System.out.println("Settings"));
        menu.exitButton().setOnAction(event -> stage.close());
        menu.playButton().setOnAction(event -> {
            PauseMenuUI pause = new PauseMenuUI();
            scene.setRoot(pause.getRoot());
        });

    }

    public static void main(String[] args) {launch(args);}


}
