package org.ics.flying_stars.game;

import javafx.scene.control.Label;

public class Score {
    private Label hitsPercentage;
    private Label averageReactionTime;
    private int totalHits = 0;
    private int hits = 0;
    private double time = 0;

    public Score() {
        hitsPercentage = new Label();
        averageReactionTime = new Label();
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

    public Label getHitsPercentage() {
        return hitsPercentage;
    }

    public void setHitsPercentage(Label hitsPercentage) {
        this.hitsPercentage = hitsPercentage;
    }

    public Label getAverageReactionTime() {
        return averageReactionTime;
    }

    public void setAverageReactionTime(Label averageReactionTime) {
        this.averageReactionTime = averageReactionTime;
    }

}
