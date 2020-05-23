package entities;

import javafx.scene.image.ImageView;

public class Food {

    private ImageView imageView;
    private int bonus;

    public Food(ImageView imageView, int bonus) {
        this.imageView = imageView;
        this.imageView.setFitHeight(55);
        this.imageView.setFitWidth(60);
        this.bonus = bonus;
    }

    public ImageView getImageView() {
        return imageView;
    }


    public int getBonus() {
        return bonus;
    }

}
