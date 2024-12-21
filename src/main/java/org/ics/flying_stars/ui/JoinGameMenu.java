package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class JoinGameMenu extends AbstractMenu {
    private final Button backToMainMenuButton;

    private final Label networkGameStatusLabel;


    public JoinGameMenu() {
        // Creating buttons for menu ui
        backToMainMenuButton = new Button("Back");


        // Increase the size of the font of the buttons
        backToMainMenuButton.setFont(new Font(50));


        // Adding a padding to the buttons to increase the size of the frame
        backToMainMenuButton.setPadding(new Insets(15, 210, 15, 210));

        // Creating a Label and increase its size and make it bold
        Label titleLabel = new Label("Waiting for Players");
        titleLabel.setFont(new Font("Arial", 80));
        titleLabel.setStyle("-fx-font-weight: bold");
        networkGameStatusLabel = new Label("Server Address:");

        rootVBox.setSpacing(60);
        rootVBox.getChildren().addAll(titleLabel, networkGameStatusLabel, backToMainMenuButton);
    }

    public Button getBackToMainMenuButton() {
        return backToMainMenuButton;
    }

    public Label getNetworkGameStatusLabel() {
        return networkGameStatusLabel;
    }
}
