package org.ics.flying_stars.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ics.flying_stars.settings.Settings;
import org.ics.flying_stars.ui.SettingsMenu;

// testing is unreal!!!!!!!!
public class TestingAli extends Application {

    @Override
    public void start(Stage stage) {
        SettingsMenu settings = new SettingsMenu(new Settings());
        Scene scene = new Scene(settings.getRoot(), 1280, 720);
        stage.setScene(scene);
        stage.show();

//
    }

    public static void main(String[] args) {launch(args);}
}
