package org.ics.flying_stars.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ics.flying_stars.ui.LosingScreenMenu;
import org.ics.flying_stars.ui.MainMenu;
import org.ics.flying_stars.ui.PauseMenu;

// testing if the one piece is realll!!!!!!!!
public class TestingAbdo extends Application {

    @Override
    public void start(Stage stage) {
        MainMenu menu = new MainMenu();
        LosingScreenMenu losing = new LosingScreenMenu();
        PauseMenu pause = new PauseMenu();
        Scene scene = new Scene(menu.getRoot(), 500, 600);

        stage.setScene(scene);
        stage.setTitle("Testing");
        stage.show();


        menu.settingsButton().setOnAction(event -> System.out.println("Settings"));
        menu.exitButton().setOnAction(event -> stage.close());
        menu.playButton().setOnAction(event -> {

            scene.setRoot(pause.getRoot());
            pause.backToMainMenuButton().setOnAction(e -> scene.setRoot(menu.getRoot()));
            pause.resumeButton().setOnAction(e -> {
                scene.setRoot(losing.getRoot());
                losing.backToMainMenuButton().setOnAction(ev -> scene.setRoot(menu.getRoot()));
            });
            pause.restartButton().setOnAction(e -> scene.setRoot(losing.getRoot()));
        });

    }

    public static void main(String[] args) {launch(args);}


}
