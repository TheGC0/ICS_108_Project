package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LosingScreenMenu extends AbstractMenu {
    // Creating a private buttons to return them at the end
    private final Button tryingButton;
    private final Button settingsButton;
    private final Button backToMainMenuButton;

    public LosingScreenMenu() {
        // Create VBoxes to hold the items columns
        VBox buttonsVbox = new VBox();

        // Creating the buttons for losing screen ui
        tryingButton = new Button("Try Again 💪");
        settingsButton = new Button("Settings ⚙");
        backToMainMenuButton= new Button("Back To Menu🏃‍♂️‍➡️");

        // Increase the size of the font of the buttons
        tryingButton.setFont(new Font(50));
        settingsButton.setFont(new Font(50));
        backToMainMenuButton.setFont(new Font(50));

        // Adding a padding to the buttons to increase the size of the frame
        tryingButton.setPadding(new Insets(15, 120, 15, 120));
        settingsButton.setPadding(new Insets(15, 160, 15, 160));
        backToMainMenuButton.setPadding(new Insets(15, 100, 15, 100));

        // Creating a Label and increase its size and make it bold
        Label titleLabel = new Label("You Lost 💔");
        titleLabel.setFont(new Font("Arial", 90));
        titleLabel.setStyle("-fx-font-weight: bold");

        // Adding the labels and buttons to the containers (VBox)
        buttonsVbox.getChildren().addAll(tryingButton, backToMainMenuButton);
        rootVBox.getChildren().addAll(titleLabel, buttonsVbox);

        // Set center alignment(position) for the VBox
        buttonsVbox.setAlignment(Pos.CENTER);

        // Set space between the items with in the same container
        buttonsVbox.setSpacing(15);
        rootVBox.setSpacing(60);

    }

    // Returning the back the buttons to ues them in the MainApp.java
    public Button tryingButton() {
        return tryingButton;
    }
    public Button settingsButton() {
        return settingsButton;
    }
    public Button backToMainMenuButton() {
        return backToMainMenuButton;
    }

}
