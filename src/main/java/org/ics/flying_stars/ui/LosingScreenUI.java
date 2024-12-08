package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static javafx.application.Application.launch;

public class LosingScreenUI extends AbstractUI{
    private final Button tryingButton;
    private final Button settingsButton;
    private final Button exitButton;

    public LosingScreenUI() {
        super("You Lost!!");



        Button TryAgain = new Button("Try Again");
        Button Settings = new Button("Settings");
        Button Exit = new Button("Exit");

        rootGridPane.add(TryAgain, 1, 1);
        rootGridPane.add(Settings, 1, 2);
        rootGridPane.add(Exit, 1, 3);

        tryingButton = TryAgain;
        settingsButton = Settings;
        exitButton = Exit;

    }

    public Button tryingButton() {
        return tryingButton;
    }
    public Button settingsButton() {
        return settingsButton;
    }
    public Button exitButton() {
        return exitButton;
    }


}
