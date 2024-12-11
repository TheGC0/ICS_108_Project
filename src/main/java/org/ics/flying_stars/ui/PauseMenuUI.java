package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PauseMenuUI extends AbstractUI{
    // making buttons
    private final Button resumeButton;
    private final Button restartButton;
    private final Button backToMainMenuButton;
    private final Button settingsButton;

    public PauseMenuUI() {
        Label titleLabel = new Label("Game Is Paused ⏸");
        titleLabel.setFont(new Font("Arial", 80));
        titleLabel.setStyle("-fx-font-weight: bold");


        // setting the buttons for pause menu ui
        resumeButton = new Button("Resume ▶");
        settingsButton = new Button("Settings ⚙");
        restartButton = new Button("Restart 🔁");
        backToMainMenuButton = new Button("Back To Menu");

        resumeButton.setPadding(new Insets(15, 120, 15, 120));
        settingsButton.setPadding(new Insets(15, 160, 15, 160));
        restartButton.setPadding(new Insets(15, 120, 15, 120));
        backToMainMenuButton.setPadding(new Insets(15, 100, 15, 100));

        resumeButton.setFont(new Font(50));
        settingsButton.setFont(new Font(50));
        restartButton.setFont(new Font(50));
        backToMainMenuButton.setFont(new Font(50));

        VBox buttonsVbox = new VBox();
        buttonsVbox.getChildren().addAll(resumeButton, restartButton, settingsButton, backToMainMenuButton);
        rootVBox.getChildren().addAll(titleLabel, buttonsVbox);
        buttonsVbox.setAlignment(Pos.CENTER);
        buttonsVbox.setSpacing(15);
        rootVBox.setSpacing(60);
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
