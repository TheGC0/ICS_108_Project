package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainMenu extends AbstractMenu {
    // Creating a private buttons to return them at the end
    private final Button playButton;
    private final Button networkGameButton;
    private final Button settingsButton;
    private final Button exitButton;

    public MainMenu() {
        // Create HBoxes and VBoxes to hold the items either in row or columns
        VBox buttonsVbox = new VBox();
        HBox titleHbox = new HBox();

        // Creating buttons for menu ui
        playButton = new Button("Play");
        networkGameButton = new Button("Network Game");
        settingsButton = new Button("Settings");
        exitButton = new Button("Exit");

        // Increase the size of the font of the buttons
        playButton.setFont(new Font(50));
        networkGameButton.setFont(new Font(50));
        settingsButton.setFont(new Font(50));
        exitButton.setFont(new Font(50));

        // Adding a padding to the buttons to increase the size of the frame
        playButton.setPadding(new Insets(15, 200, 15, 200));
        networkGameButton.setPadding(new Insets(15, 200, 15, 200));
        settingsButton.setPadding(new Insets(15, 160, 15, 160));
        exitButton.setPadding(new Insets(15, 210, 15, 210));

        // Creating a Label and increase its size and make it bold
        Label titleLabel = new Label("Flying Stars");
        titleLabel.setFont(new Font("Arial", 80));
        titleLabel.setStyle("-fx-font-weight: bold");

        // Creating a stars emoji and increase their size and give them a color
        Label star1 = new Label("⭐");
        star1.setFont(new Font(110));
        star1.setTextFill(Color.web("#e5d400"));
        Label star2 = new Label("⭐");
        star2.setFont(new Font(110));
        star2.setTextFill(Color.web("#e5d400"));

        // Adding the labels and buttons to the containers (HBox, VBox)
        titleHbox.getChildren().addAll(star1, titleLabel, star2);
        buttonsVbox.getChildren().addAll(playButton,networkGameButton, settingsButton, exitButton);
        rootVBox.getChildren().addAll(titleHbox, buttonsVbox);

        // Set space between the items with in the same container
        buttonsVbox.setSpacing(15);
        rootVBox.setSpacing(60);

        // Set center alignment(position) for the HBox and VBox
        buttonsVbox.setAlignment(Pos.CENTER);
        titleHbox.setAlignment(Pos.CENTER);

    }

    // Returning the back the buttons to ues them in the MainApp.java
    public Button playButton() {return playButton;}
    public Button settingsButton() {return settingsButton;}
    public Button exitButton() {return exitButton;}

    public Button networkGameButton() {
        return networkGameButton;
    }
}
