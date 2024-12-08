package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuUI extends AbstractUI{
    private final Button playButton;
    private final Button settingsButton;
    private final Button exitButton;


    public MenuUI() {
        super("Flying Stars");


        Button play = new Button("play");
        Button settings = new Button("Settings");
        Button exit = new Button("Exit");


        rootGridPane.add(play, 1, 1);
        rootGridPane.add(settings, 1, 2);
        rootGridPane.add(exit, 1, 3);

        playButton = play;
        settingsButton = settings;
        exitButton = exit;
    }
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
