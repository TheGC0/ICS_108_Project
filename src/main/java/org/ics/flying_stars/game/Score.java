package org.ics.flying_stars.game;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
        hitsPercentage.setStyle("-fx-text-fill: white;");
        averageReactionTime.setStyle("-fx-text-fill: white;");
        hitsPercentage.setFont(new Font(10));
        averageReactionTime.setFont(new Font(10));
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
        hitsPercentage.setText( "Hits Percentage: %.0f %%".formatted(100 * (double) hits / (double) totalHits) );
        averageReactionTime.setText("Reaction Time: %.2f sec".formatted( time / (double) totalHits / 1000 ));
    }

    public void reset(){
        totalHits = 0;
        hits = 0;
        time = 0;
        hitsPercentage.setText( "Hits Percentage: " + 0 + "%%" );
        averageReactionTime.setText("Reaction Time: " + 0 + " sec");
    }

    @Override
    public Parent getRoot() {
        return layout;
    }
}
