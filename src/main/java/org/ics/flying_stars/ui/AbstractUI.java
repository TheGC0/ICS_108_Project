package org.ics.flying_stars.ui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;

public abstract class AbstractUI {
    private String mainLabel;
    private Image backGroundImage = null;
    private ImageView imageView;
    private Scene scene;
    private Group root;
    private final Double SCREEN_WIDTH = 800.0;
    private final Double SCREEN_HEIGHT = 600.0;


    public AbstractUI(String title, String imagePath) {
        this.mainLabel = title;
        try {
            this.backGroundImage = new Image(new FileInputStream(imagePath));
        }
        catch (Exception e) {
            System.out.println("Image not found");
            return;
        }
    }

    //adding a button
    public abstract void addButton(Button button);

    public void setMainLabelText(String title){
        Label mainLabel = new Label(title);
    }

    public void start(Stage stage){
        ImageView imageView = new ImageView(backGroundImage);

        this.imageView.setFitHeight(SCREEN_HEIGHT);
        this.imageView.setFitWidth(SCREEN_WIDTH);

        Label label = new Label(mainLabel);



        Group root = new Group();
        root.getChildren().add(imageView);



    }

}
