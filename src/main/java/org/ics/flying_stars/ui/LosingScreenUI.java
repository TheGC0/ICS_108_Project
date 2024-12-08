package org.ics.flying_stars.ui;

import javafx.scene.control.Button;

import static javafx.application.Application.launch;

public class LosingScreenUI extends AbstractUI{
    // making buttons
    private final Button tryingButton;
    private final Button settingsButton;
    private final Button backToMainMenuButton;

    public LosingScreenUI() {
        super("You Lost!!");


        // setting the buttons for losing screen ui
        Button tryAgain = new Button("Try Again");
        Button settings = new Button("Settings");
        Button backToMainMenu = new Button("Exit");

        // Setting the buttons locations
        rootGridPane.add(tryAgain, 1, 1);
        rootGridPane.add(settings, 1, 2);
        rootGridPane.add(backToMainMenu, 1, 3);

        // Initializing the buttons
        tryingButton = tryAgain;
        settingsButton = settings;
        backToMainMenuButton = backToMainMenu;

    }

    // buttons setters
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
