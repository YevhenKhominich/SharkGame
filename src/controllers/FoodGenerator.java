package controllers;

import entities.Food;
import javafx.scene.image.ImageView;

public class FoodGenerator {

    private int bonusBreadPoint = 5;
    private int bonusMeatPoint = 10;

    public Food getRandomFood() {
        int random = (int) Math.floor(Math.random() * 100);
        if (random % 2 == 0) {
            return new Food(new ImageView("images/bread.png"), bonusBreadPoint);
        } else return new Food(new ImageView("images/meat.png"), bonusMeatPoint);
    }
}
