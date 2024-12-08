package org.ics.flying_stars.ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static javafx.application.Application.launch;

public class LosingScreenUI extends AbstractUI{
    private final Button tryingButton;

    public LosingScreenUI() {
        super();
        GridPane gridPane = new GridPane();

        Label losingMessage = new Label("You lost!");
        losingMessage.setStyle("-fx-text-fill: red;");

        Button TryAgain = new Button("Try Again");
        Button Settings = new Button("Settings");
        Button Exit = new Button("Exit");

        gridPane.setPadding(new Insets(100,250,100,250));
        gridPane.setHgap(50);
        gridPane.setVgap(50);

        gridPane.add(losingMessage, 0, 1);
        gridPane.add(TryAgain, 1, 1);
        gridPane.add(Settings, 2, 1);
        gridPane.add(Exit, 3, 1);

        root = gridPane;
        tryingButton = TryAgain;
    }

    public static void main(String[] args) { launch(args); }

    public Button tryingButton() {
        return tryingButton;
    }
}
