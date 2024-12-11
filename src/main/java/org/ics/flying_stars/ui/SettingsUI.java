package org.ics.flying_stars.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.ics.flying_stars.settings.Settings;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.Slider;

public class SettingsUI extends AbstractUI {
    VBox MainVbox = new VBox();

    public SettingsUI() {
        super("Settings");


        HBox difficultyHbox = new HBox();
        HBox shapeHbox = new HBox();
        VBox difficultyVbox = new VBox();
        VBox shapeVbox = new VBox();

        Button difficultyExtreme = new Button("Extreme");
        Button difficultyHard = new Button("Hard");
        Button difficultyMedium = new Button("Medium");
        Button difficultyEasy = new Button("Easy");
        Button shapeStar = new Button("Star");
        Button shapeCircle = new Button("Circle");
        Button shapeTriangle = new Button("Triangle");
        Button shapeSquare = new Button("Square");

        difficultyExtreme.setTextFill(Color.web("#000000"));
        difficultyHard.setTextFill(Color.web("#ff1f00"));
        difficultyMedium.setTextFill(Color.web("#e5d400"));
        difficultyEasy.setTextFill(Color.web("#1bff00"));
        difficultyExtreme.setFont(new Font(20));
        difficultyHard.setFont(new Font(20));
        difficultyMedium.setFont(new Font(20));
        difficultyEasy.setFont(new Font(20));


        Label titleLabel = new Label("Settings");
        Label difficultyLabel = new Label("Difficulty");
        Label shapeLabel = new Label("Shape");

        titleLabel.setFont(new Font("Arial", 80));
        difficultyLabel.setFont(new Font(50));
        shapeLabel.setFont(new Font(50));

        difficultyHbox.getChildren().addAll(difficultyEasy, difficultyMedium, difficultyHard, difficultyExtreme);
        shapeHbox.getChildren().addAll(shapeStar, shapeTriangle, shapeCircle, shapeSquare);
        difficultyVbox.getChildren().addAll(difficultyLabel, difficultyHbox);
        shapeVbox.getChildren().addAll(shapeLabel, shapeHbox);
        MainVbox.getChildren().addAll(titleLabel, difficultyVbox, shapeVbox);

        MainVbox.setSpacing(80);
        difficultyHbox.setSpacing(20);
        difficultyVbox.setSpacing(40);
        shapeHbox.setSpacing(20);
        shapeVbox.setSpacing(40);

        // Set center alignment for the HBox
        MainVbox.setAlignment(Pos.CENTER);
        difficultyHbox.setAlignment(Pos.CENTER);
        difficultyVbox.setAlignment(Pos.CENTER);
        shapeHbox.setAlignment(Pos.CENTER);
        shapeVbox.setAlignment(Pos.CENTER);

    }

    public VBox get_vbox(){
        return MainVbox;
    }

}
