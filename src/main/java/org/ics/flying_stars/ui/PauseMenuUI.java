package org.ics.flying_stars.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PauseMenuUI extends AbstractUI{
    // making buttons
    private final Button resumeButton;
    private final Button restartButton;
    private final Button exitButton;
    private final Button settingsButton;

    public PauseMenuUI() {
        super("Pause");

        // setting the buttons for pause menu ui
        Button resume = new Button("Resume");
        Button settings = new Button("Settings");
        Button restart = new Button("Restart");
        Button exit = new Button("Exit");

        // Setting the buttons locations
        rootGridPane.add(resume, 1, 1);
        rootGridPane.add(settings, 1, 2);
        rootGridPane.add(restart, 1, 3);
        rootGridPane.add(exit, 1, 4);

        // Initializing the buttons
        resumeButton = resume;
        settingsButton = settings;
        restartButton = restart;
        exitButton = exit;
    }

    // buttons setters
    public Button resumeButton() {
        return resumeButton;
    }
    public Button restartButton() {
        return restartButton;
    }
    public Button exitButton() {
        return exitButton;
    }
    public Button settingsButton(){
        return settingsButton;
    }



}
