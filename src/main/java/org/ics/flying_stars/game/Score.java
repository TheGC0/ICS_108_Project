package org.ics.flying_stars.game;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.ics.flying_stars.ui.UI;

public class Score implements UI {
    private final Label hitsPercentage;
    private final Label averageReactionTime;
    private final VBox layout;
    private int totalHits = 0;
    private int hits = 0;
    private double time = 0;

    public Score() {
        layout = new VBox();
        hitsPercentage = new Label();
        averageReactionTime = new Label();
        layout.getChildren().addAll(hitsPercentage, averageReactionTime);
    }

    public void hit(double time) {
        hits++;
        this.time += time;
        totalHits++;
        update();
    }

    public void miss(double time) {
        this.time += time;
        totalHits++;
        update();
    }

    public void update(){
        hitsPercentage.setText( "" + (double) hits / (double) totalHits + "%%" );
        averageReactionTime.setText("" + time / (double) totalHits + " seconds");
    }

    public void reset(){
        totalHits = 0;
        hits = 0;
        time = 0;
        hitsPercentage.setText( "" + 0 + "%%" );
        averageReactionTime.setText("" + 0 + " seconds");
    }

    @Override
    public Parent getRoot() {
        return layout;
    }
}
