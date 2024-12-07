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
    private Label mainLabel;
    private Image backGroundImage = null;
    private ImageView imageView;
    private Scene scene;
    private Group root;


    public AbstractUI(String title, String imagePath) {
        this.mainLabel = new Label(title);
        try {
            this.backGroundImage = new Image(new FileInputStream(imagePath));
        }

    }
    //adding a button
    public abstract void addButton(Button button);

    public void setMainLabel(Label label){
        mainLabel = label;

    }


}
