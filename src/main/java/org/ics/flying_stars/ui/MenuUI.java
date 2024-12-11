package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuUI extends AbstractUI{
    // making buttons
    private final Button playButton;
    private final Button settingsButton;
    private final Button exitButton;


    public MenuUI() {
        // setting the buttons for menu ui
        playButton = new Button("Play");
        settingsButton = new Button("Settings");
        exitButton = new Button("Exit");

        playButton.setPadding(new Insets(15, 200, 15, 200));
        settingsButton.setPadding(new Insets(15, 160, 15, 160));
        exitButton.setPadding(new Insets(15, 210, 15, 210));

        playButton.setFont(new Font(50));
        settingsButton.setFont(new Font(50));
        exitButton.setFont(new Font(50));

        VBox buttonsVbox = new VBox();
        HBox titleHbox = new HBox();
        buttonsVbox.setAlignment(Pos.CENTER);
        titleHbox.setAlignment(Pos.CENTER);
        buttonsVbox.setSpacing(15);

        Label titleLabel = new Label("Flying Stars");
        titleLabel.setFont(new Font("Arial", 80));
        titleLabel.setStyle("-fx-font-weight: bold");

        Label star1 = new Label("⭐");
        star1.setFont(new Font(110));
        star1.setTextFill(Color.web("#e5d400"));

        Label star2 = new Label("⭐");
        star2.setFont(new Font(110));
        star2.setTextFill(Color.web("#e5d400"));

        titleHbox.getChildren().addAll(star1, titleLabel, star2);
        buttonsVbox.getChildren().addAll(playButton, settingsButton, exitButton);

        rootVBox.getChildren().addAll(titleHbox, buttonsVbox);
        rootVBox.setSpacing(60);

    }

    // buttons getters
    public Button playButton() {return playButton;}
    public Button settingsButton() {return settingsButton;}
    public Button exitButton() {return exitButton;}

}
