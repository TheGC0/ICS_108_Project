package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LosingScreenUI extends AbstractUI{
    // making buttons
    private final Button tryingButton;
    private final Button settingsButton;
    private final Button backToMainMenuButton;

    public LosingScreenUI() {
        Label titleLabel = new Label("You Lost 💔");
        titleLabel.setFont(new Font("Arial", 90));
        titleLabel.setStyle("-fx-font-weight: bold");

        // setting the buttons for losing screen ui
        tryingButton = new Button("Try Again 💪");
        settingsButton = new Button("Settings ⚙");
        backToMainMenuButton= new Button("Back To Menu🏃‍♂️‍➡️");

        tryingButton.setPadding(new Insets(15, 120, 15, 120));
        settingsButton.setPadding(new Insets(15, 160, 15, 160));
        backToMainMenuButton.setPadding(new Insets(15, 100, 15, 100));

        tryingButton.setFont(new Font(50));
        settingsButton.setFont(new Font(50));
        backToMainMenuButton.setFont(new Font(50));

        VBox buttonsVbox = new VBox();
        buttonsVbox.getChildren().addAll(tryingButton, settingsButton, backToMainMenuButton);
        rootVBox.getChildren().addAll(titleLabel, buttonsVbox);
        buttonsVbox.setAlignment(Pos.CENTER);
        buttonsVbox.setSpacing(15);
        rootVBox.setSpacing(60);

    }

    // buttons getters
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
