package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HostGameMenu extends AbstractMenu {
    private final Button startGameButton;
    private final Button backToMainMenuButton;

    private final Label networkGameStatusLabel;


    public HostGameMenu() {
        // Creating buttons for menu ui
        startGameButton = new Button("Start Game");
        backToMainMenuButton = new Button("Back");


        // Increase the size of the font of the buttons
        startGameButton.setFont(new Font(50));
        backToMainMenuButton.setFont(new Font(50));


        // Adding a padding to the buttons to increase the size of the frame
        startGameButton.setPadding(new Insets(15, 200, 15, 200));
        backToMainMenuButton.setPadding(new Insets(15, 210, 15, 210));

        // Creating a Label and increase its size and make it bold
        Label titleLabel = new Label("Waiting for Players");
        titleLabel.setFont(new Font("Arial", 80));
        titleLabel.setStyle("-fx-font-weight: bold");
        networkGameStatusLabel = new Label("Server Address:");

        rootVBox.setSpacing(60);
        rootVBox.getChildren().addAll(titleLabel, networkGameStatusLabel, startGameButton, backToMainMenuButton);
    }

    public Button getStartGameButton() {
        return startGameButton;
    }

    public Button getBackToMainMenuButton() {
        return backToMainMenuButton;
    }

    public Label getNetworkGameStatusLabel() {
        return networkGameStatusLabel;
    }
}
