package org.ics.flying_stars.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.ics.flying_stars.settings.Difficulty;
import org.ics.flying_stars.settings.Settings;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.ics.flying_stars.settings.Shape;

public class SettingsMenu extends AbstractMenu {
    // Creating button that goes back to the main menu
    private final Button backToMainMenuButton;

    public SettingsMenu(Settings settings) {
        // Create HBoxes and VBoxes to hold the items either in row or columns
        HBox difficultyHbox = new HBox();
        HBox shapeHbox = new HBox();
        VBox difficultyVbox = new VBox();
        VBox shapeVbox = new VBox();

        // Creating difficulties and shapes buttons
        Button difficultyExtreme = new Button("Extreme");
        Button difficultyHard = new Button("Hard");
        Button difficultyMedium = new Button("Medium");
        Button difficultyEasy = new Button("Easy");
        Button shapeStar = new Button("Star");
        Button shapeTriangle = new Button("Triangle");
        Button shapeSquare = new Button("Square");
        Button shapeRandomPolygon = new Button("Random Polygon");
        backToMainMenuButton = new Button("Back To Menu");

        // Increase the size of the font of the buttons
        difficultyExtreme.setFont(new Font(20));
        difficultyHard.setFont(new Font(20));
        difficultyMedium.setFont(new Font(20));
        difficultyEasy.setFont(new Font(20));
        shapeStar.setFont(new Font(20));
        shapeTriangle.setFont(new Font(20));
        shapeSquare.setFont(new Font(20));
        shapeRandomPolygon.setFont(new Font(20));
        backToMainMenuButton.setFont(new Font(25));

        // Give buttons' texts colors
        backToMainMenuButton.setTextFill(Color.web("#000000"));
        difficultyExtreme.setTextFill(Color.web("#000000"));
        difficultyHard.setTextFill(Color.web("#ff1f00"));
        difficultyMedium.setTextFill(Color.web("#e5d400"));
        difficultyEasy.setTextFill(Color.web("#1bff00"));

        // Creating a Labels
        Label titleLabel = new Label("Settings");
        Label difficultyLabel = new Label("Difficulty");
        Label shapeLabel = new Label("Shape");

        // Increase the labels size and make the title bold text
        titleLabel.setFont(new Font("Arial", 80));
        titleLabel.setStyle("-fx-font-weight: bold");
        difficultyLabel.setFont(new Font(50));
        shapeLabel.setFont(new Font(50));

        // Adding the labels and buttons to the containers (HBox, VBox)
        difficultyHbox.getChildren().addAll(difficultyEasy, difficultyMedium, difficultyHard, difficultyExtreme);
        shapeHbox.getChildren().addAll(shapeStar, shapeTriangle, shapeSquare, shapeRandomPolygon);
        difficultyVbox.getChildren().addAll(difficultyLabel, difficultyHbox);
        shapeVbox.getChildren().addAll(shapeLabel, shapeHbox);
        rootVBox.getChildren().addAll(titleLabel, difficultyVbox, shapeVbox, backToMainMenuButton);

        // Set space between the items with in the same container
        rootVBox.setSpacing(65);
        difficultyHbox.setSpacing(20);
        difficultyVbox.setSpacing(40);
        shapeHbox.setSpacing(20);
        shapeVbox.setSpacing(40);

        // Set center alignment(position) for the HBox and VBox
        rootVBox.setAlignment(Pos.CENTER);
        difficultyHbox.setAlignment(Pos.CENTER);
        difficultyVbox.setAlignment(Pos.CENTER);
        shapeHbox.setAlignment(Pos.CENTER);
        shapeVbox.setAlignment(Pos.CENTER);

        // Add actions to buttons to get the difficulty
        difficultyExtreme.setOnAction(actionEvent -> settings.setDifficulty(Difficulty.EXTREME));
        difficultyHard.setOnAction(actionEvent -> settings.setDifficulty(Difficulty.HARD));
        difficultyMedium.setOnAction(actionEvent -> settings.setDifficulty(Difficulty.MEDIUM));
        difficultyEasy.setOnAction(actionEvent -> settings.setDifficulty(Difficulty.EASY));

        // Add actions to buttons to get the shape
        shapeStar.setOnAction(actionEvent -> settings.setShape(Shape.Star));
        shapeSquare.setOnAction(actionEvent -> settings.setShape(Shape.Square));
        shapeTriangle.setOnAction(actionEvent -> settings.setShape(Shape.Triangle));
        shapeRandomPolygon.setOnAction(actionEvent -> settings.setShape(Shape.RandomPolygon));
    }

    // Returning the back to main menu button to ues it in the MainApp.java
    public Button menuButton() {return backToMainMenuButton;}

}
