package org.ics.flying_stars;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.ics.flying_stars.game.Game;
import org.ics.flying_stars.settings.Settings;
import org.ics.flying_stars.ui.LosingScreenUI;
import org.ics.flying_stars.ui.MenuUI;
import org.ics.flying_stars.ui.PauseMenuUI;
import org.ics.flying_stars.ui.SettingsUI;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas();
        canvas.setWidth(850);
        canvas.setHeight(850);
//        pane.setBackground(Background.fill(Color.BLACK));
        MenuUI menuUI = new MenuUI();
        Settings settings = new Settings();
        SettingsUI settingsUI = new SettingsUI(settings );
        LosingScreenUI losingScreenUI = new LosingScreenUI();
        PauseMenuUI pauseMenuUI = new PauseMenuUI();
        Game game = new Game(settings, canvas);



        stage.setScene(new Scene(menuUI.getRoot()));
        stage.setWidth(850);
        stage.setHeight(850);

        final boolean[] seen = {false, false, false, false};

        menuUI.playButton().setOnAction(event -> {


            if(!seen[0]){
                stage.setScene(new Scene(game.getRoot()));
                seen[0] = true;
            }
            else{ stage.setScene(game.getRoot().getScene()); }


            game.start();
        });
        menuUI.settingsButton().setOnAction(event -> {
            if(!seen[1]){
                stage.setScene(new Scene(settingsUI.getRoot()));
                seen[1] = true;
            }
            stage.setScene(settingsUI.getRoot().getScene());
            settingsUI.menuButton().setOnAction(event1 -> {
                stage.setScene((menuUI.getRoot()).getScene());
            });
        });
        menuUI.exitButton().setOnAction(event -> {
            stage.close();
        });

        game.backToMainMenuLoseButton().setOnAction(event -> {
            stage.setScene(menuUI.getRoot().getScene());
        });











        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
