package sample;

import entities.Bread;
import entities.Food;
import javafx.scene.image.ImageView;

public class FoodBuilder {

    public Food getBread() {
        return new Bread(new ImageView("images/bread.png"), 10);
    }
}
