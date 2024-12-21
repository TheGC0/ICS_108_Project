package org.ics.flying_stars;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import org.ics.flying_stars.game.Game;
import org.ics.flying_stars.settings.Settings;
import org.ics.flying_stars.ui.MainMenu;
import org.ics.flying_stars.ui.NetworkGameMenu;
import org.ics.flying_stars.ui.SettingsMenu;

import java.net.SocketException;
import java.util.Locale;
import java.util.Optional;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        Locale.setDefault(Locale.US);
        // Creating the canvas
        Canvas canvas = new Canvas();
        canvas.setWidth(850);
        canvas.setHeight(850);

        // Creating the settings object with default settings
        Settings settings = new Settings();

        // Creating the menus
        MainMenu menuUI = new MainMenu();
        SettingsMenu settingsUI = new SettingsMenu(settings);
        NetworkGameMenu networkGameUI = new NetworkGameMenu();

        // Creating the Game object
        Game game = new Game(settings, canvas);

        // Let the "return to menu button" inside the Game object return to the main menu
        game.backToMainMenuLoseButton().setOnAction(event -> stage.setScene(menuUI.getRoot().getScene()));

        // Set the stage scene to the main menu
        stage.setScene(new Scene(menuUI.getRoot()));
        stage.setWidth(850);
        stage.setHeight(850);

        // Booleans for each menu, so that only 1 scene is created for each menu (switches to true once a scene for the menu is created)
        final boolean[] seen = {false, false, false, false, false};

        // Let the "play" button start the game and switch the scene to the game
        menuUI.playButton().setOnAction(event -> {

            // Switch scene (create scene if not created for the menu
            if(!seen[0]){
                stage.setScene(new Scene(game.getRoot()));
                seen[0] = true;
            }
            else{ stage.setScene(game.getRoot().getScene()); }

            // Start the game
            game.start();
        });

        // Let the "settings" button show the settings menu and switch the scene
        menuUI.settingsButton().setOnAction(event -> {
            // Switch scene (create scene if not created for the menu
            if(!seen[1]){
                stage.setScene(new Scene(settingsUI.getRoot()));
                seen[1] = true;
            } else {
                stage.setScene(settingsUI.getRoot().getScene());
            }
        });

        // Let the "network game" button switch to the network game menu
        menuUI.networkGameButton().setOnAction(event -> {
            // Switch scene (create scene if not created for the menu
            if(!seen[2]){
                stage.setScene(new Scene(networkGameUI.getRoot()));
                seen[2] = true;
            } else {
                stage.setScene(networkGameUI.getRoot().getScene());
            }
        });

        networkGameUI.hostGameButton().setOnAction(event -> {
            try {
                networkGameUI.hostGame();
                networkGameUI.joinGame("");
            } catch (SocketException e) {
                return;
            }
            // Switch scene (create scene if not created for the menu
            if(!seen[3]){
                stage.setScene(new Scene(networkGameUI.getHostGameMenu().getRoot()));
                seen[3] = true;
            } else {
                stage.setScene(networkGameUI.getHostGameMenu().getRoot().getScene());
            }

        });

        networkGameUI.joinGameButton().setOnAction(event -> {
            TextInputDialog textInputDialog = new TextInputDialog("");
            Optional<String> result = textInputDialog.showAndWait();
            String host = result.orElse("");

            try {
                networkGameUI.joinGame(host);
            } catch (SocketException e) {
                return;
            }
            // Switch scene (create scene if not created for the menu
            if(!seen[4]){
                stage.setScene(new Scene(networkGameUI.getJoinGameMenu().getRoot()));
                seen[4] = true;
            } else {
                stage.setScene(networkGameUI.getJoinGameMenu().getRoot().getScene());
            }

        });

        networkGameUI.getShowNetworkGameHiddenButton().setOnAction(event -> {
            stage.setScene(new Scene(networkGameUI.getNetworkGame().getRoot()));
            networkGameUI.getNetworkGame().backToMainMenuLoseButton().setOnAction(e -> menuUI.getRoot().getScene());
        });

        // Let the "exit" button exit the application
        menuUI.exitButton().setOnAction(event -> stage.close());

        // Let the settings "menu" button return to the main menu
        settingsUI.menuButton().setOnAction(event -> stage.setScene((menuUI.getRoot()).getScene()));

        // Let the network "menu" button return to the main menu
        networkGameUI.menuButton().setOnAction(event -> stage.setScene((menuUI.getRoot()).getScene()));

        // Let the host game "back" button return to the network menu
        networkGameUI.getHostGameMenu().getBackToMainMenuButton().setOnAction(event -> {
            networkGameUI.getNetworkGameServer().stopServer();
            stage.setScene(networkGameUI.getRoot().getScene());
        });

        // Let the join game "back" button return to the network menu
        networkGameUI.getJoinGameMenu().getBackToMainMenuButton().setOnAction(event -> {
            networkGameUI.getNetworkGame().stopNetworkGame();
            stage.setScene(networkGameUI.getRoot().getScene());
        });

        // Show the stage
        stage.show();

        stage.setOnCloseRequest(event -> {
            if (networkGameUI.getNetworkGameServer() != null) {
                networkGameUI.getNetworkGameServer().stopServer();
            }
            if (networkGameUI.getNetworkGame() != null) {
                networkGameUI.getNetworkGame().stopNetworkGame();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
