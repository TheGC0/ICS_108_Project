package org.ics.flying_stars.game;

public class HealthBar {
    public int currentHealth = 0;


    public HealthBar() {
        startAnewLife();
    }

    public void startAnewLife() {
        currentHealth = 3;
        showHealthBar(currentHealth);

    }
    public void showHealthBar(int currentHealth) {

    }

    public void getDamage(){
        currentHealth--;
        showHealthBar(currentHealth);
    }


}
