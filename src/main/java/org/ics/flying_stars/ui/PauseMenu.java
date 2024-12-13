package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PauseMenu extends AbstractMenu {
    // Creating a private buttons to return them at the end
    private final Button resumeButton;
    private final Button restartButton;
    private final Button backToMainMenuButton;
    private final Button settingsButton;

    public PauseMenu() {
        // Create VBoxes to hold the items columns
        VBox buttonsVbox = new VBox();

        // Creating the buttons for pause menu ui
        resumeButton = new Button("Resume ▶");
        settingsButton = new Button("Settings ⚙");
        restartButton = new Button("Restart 🔁");
        backToMainMenuButton = new Button("Back To Menu");

        // Increase the size of the font of the buttons
        resumeButton.setFont(new Font(50));
        settingsButton.setFont(new Font(50));
        restartButton.setFont(new Font(50));
        backToMainMenuButton.setFont(new Font(50));

        // Adding a padding to the buttons to increase the size of the frame
        resumeButton.setPadding(new Insets(15, 120, 15, 120));
        settingsButton.setPadding(new Insets(15, 160, 15, 160));
        restartButton.setPadding(new Insets(15, 120, 15, 120));
        backToMainMenuButton.setPadding(new Insets(15, 100, 15, 100));

        // Creating a Label and increase its size and make it bold
        Label titleLabel = new Label("Game Is Paused ⏸");
        titleLabel.setFont(new Font("Arial", 80));
        titleLabel.setStyle("-fx-font-weight: bold");

        // Adding the labels and buttons to the containers (VBox)
        buttonsVbox.getChildren().addAll(resumeButton, restartButton, settingsButton, backToMainMenuButton);
        rootVBox.getChildren().addAll(titleLabel, buttonsVbox);

        // Set space between the items with in the same container
        buttonsVbox.setSpacing(15);
        rootVBox.setSpacing(60);

        // Set center alignment(position) for the VBox
        buttonsVbox.setAlignment(Pos.CENTER);

    }

    // buttons getters
    public Button resumeButton() {
        return resumeButton;
    }
    public Button restartButton() {
        return restartButton;
    }
    public Button backToMainMenuButton() {
        return backToMainMenuButton;
    }
    public Button settingsButton(){
        return settingsButton;
    }

}
