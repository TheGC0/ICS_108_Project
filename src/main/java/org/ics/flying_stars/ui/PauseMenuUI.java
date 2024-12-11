package org.ics.flying_stars.ui;

import javafx.scene.control.Button;

public class PauseMenuUI extends AbstractUI{
    // making buttons
    private final Button resumeButton;
    private final Button restartButton;
    private final Button backToMainMenuButton;
    private final Button settingsButton;

    public PauseMenuUI() {

        // setting the buttons for pause menu ui
        Button resume = new Button("Resume");
        Button settings = new Button("Settings");
        Button restart = new Button("Restart");
        Button backToMainMenu = new Button("Back to main menu");

        // Setting the buttons locations

        // Initializing the buttons
        resumeButton = resume;
        settingsButton = settings;
        restartButton = restart;
        backToMainMenuButton = backToMainMenu;
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
