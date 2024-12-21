package org.ics.flying_stars.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.ics.flying_stars.network.NetworkGame;
import org.ics.flying_stars.network.NetworkGameClient;
import org.ics.flying_stars.network.NetworkGameServer;
import org.ics.flying_stars.settings.Settings;

import java.net.SocketException;

public class NetworkGameMenu extends AbstractMenu {
    private final Button hostGameButton;
    private final Button joinGameButton;
    private final Button backToMainMenuButton;

    private final Button showNetworkGameHiddenButton;

    private final HostGameMenu hostGameMenu;
    private final JoinGameMenu joinGameMenu;

    private NetworkGame networkGame;
    private NetworkGameServer networkGameServer;

    public NetworkGameMenu() {
        // Creating buttons for menu ui
        hostGameButton = new Button("Host Game");
        joinGameButton = new Button("Join Game");
        backToMainMenuButton = new Button("Back");


        // Increase the size of the font of the buttons
        hostGameButton.setFont(new Font(50));
        joinGameButton.setFont(new Font(50));
        backToMainMenuButton.setFont(new Font(50));


        // Adding a padding to the buttons to increase the size of the frame
        hostGameButton.setPadding(new Insets(15, 200, 15, 200));
        joinGameButton.setPadding(new Insets(15, 160, 15, 160));
        backToMainMenuButton.setPadding(new Insets(15, 210, 15, 210));

        // Creating a Label and increase its size and make it bold
        Label titleLabel = new Label("Network Game");
        titleLabel.setFont(new Font("Arial", 80));
        titleLabel.setStyle("-fx-font-weight: bold");

        rootVBox.setSpacing(60);
        rootVBox.getChildren().addAll(titleLabel, hostGameButton, joinGameButton, backToMainMenuButton);

        // Create sub menus
        hostGameMenu = new HostGameMenu();
        joinGameMenu = new JoinGameMenu();

        showNetworkGameHiddenButton = new Button();
    }

    public Button hostGameButton() {
        return hostGameButton;
    }

    public Button joinGameButton() {
        return joinGameButton;
    }

    public Button menuButton() {
        return backToMainMenuButton;
    }

    public HostGameMenu getHostGameMenu() {
        return hostGameMenu;
    }

    public JoinGameMenu getJoinGameMenu() {
        return joinGameMenu;
    }

    public void hostGame() throws SocketException {
        // Create and start game server
        networkGameServer = new NetworkGameServer(new Settings());
        networkGameServer.start();

        hostGameMenu.getStartGameButton().setOnAction(event -> {
            System.out.println("Starting network game");
            networkGameServer.stopWaitingForPlayers();
        });
    }

    public void joinGame(String address) throws SocketException {
        // Creating the canvas
        Canvas canvas = new Canvas();
        canvas.setWidth(850);
        canvas.setHeight(850);

        networkGame = new NetworkGame(null, canvas, address);

        // check client server status periodically
        final boolean[] flag = {false};
        Timeline observeNetworkGameState = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    // Update join/host game state TODO
                    if (flag[0]) {
                        return;
                    }
                    System.out.println(networkGame.state());
                    if (networkGame.state() == NetworkGameClient.State.ONGOING) {
                        networkGame.start();
                        getShowNetworkGameHiddenButton().fire();
                        flag[0] = true;
                    } else if (networkGame.state() == NetworkGameClient.State.COULD_NOT_JOIN) {
                        joinGameMenu.getBackToMainMenuButton().fire();
                        flag[0] = true;
                    }
                })
        );
        observeNetworkGameState.setCycleCount(20);
        observeNetworkGameState.play();

    }

    public NetworkGame getNetworkGame() {
        return networkGame;
    }

    public Button getShowNetworkGameHiddenButton() {
        return showNetworkGameHiddenButton;
    }

    public NetworkGameServer getNetworkGameServer() {
        return networkGameServer;
    }
}
