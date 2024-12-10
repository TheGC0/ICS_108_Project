package org.ics.flying_stars.ui;

import javafx.scene.control.Button;

public class MenuUI extends AbstractUI{
    // making buttons
    private final Button playButton;
    private final Button settingsButton;
    private final Button exitButton;


    public MenuUI() {
        super("Flying Stars");

        // setting the buttons for menu ui
        Button play = new Button("play");
        Button settings = new Button("Settings");
        Button exit = new Button("Exit");

        // Setting the buttons locations
        rootGridPane.add(play, 1, 1);
        rootGridPane.add(settings, 1, 2);
        rootGridPane.add(exit, 1, 3);

        // Initializing the buttons
        playButton = play;
        settingsButton = settings;
        exitButton = exit;
    }

    // buttons getters
    public Button playButton() {
        return playButton;
    }
    public Button settingsButton() {
        return settingsButton;
    }
    public Button exitButton() {
        return exitButton;
    }

}
