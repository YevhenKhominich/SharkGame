package controllers;

import entities.Food;
import javafx.scene.image.ImageView;

public class FoodGenerator {

    private int bonusBreadPoint = 5;
    private int bonusMeatPoint = 10;
    private int bonusFishMeatPoint = 15;
    private int bonusWormPoint = 20;

    public Food getRandomFood() {
        int random = (int) Math.floor(Math.random() * 100);
        if (random % 4 == 0) {
            return new Food(new ImageView("images/fishmeat.png"), bonusFishMeatPoint);
        }
        if (random % 3 == 0) {
            return new Food(new ImageView("images/worm.png"), bonusWormPoint, 90, 90);
        }
        if (random % 2 == 0) {
            return new Food(new ImageView("images/bread.png"), bonusBreadPoint);
        } else return new Food(new ImageView("images/meat.png"), bonusMeatPoint);
    }
}
