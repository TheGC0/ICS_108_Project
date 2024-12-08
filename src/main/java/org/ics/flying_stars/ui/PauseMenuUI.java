package org.ics.flying_stars.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PauseMenuUI extends AbstractUI{
    private final Button resumeButton;
    private final Button restartButton;
    private final Button exitButton;
    private final Button settingsButton;

    public PauseMenuUI() {
        super("Pause");


        Button resume = new Button("Resume");
        Button settings = new Button("Settings");
        Button restart = new Button("Restart");
        Button exit = new Button("Exit");


        rootGridPane.add(resume, 1, 1);
        rootGridPane.add(settings, 1, 2);
        rootGridPane.add(restart, 1, 3);
        rootGridPane.add(exit, 1, 4);

        resumeButton = resume;
        settingsButton = settings;
        restartButton = restart;
        exitButton = exit;
    }
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
