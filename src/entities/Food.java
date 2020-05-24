package entities;

import javafx.scene.image.ImageView;

public class Food {

    private ImageView imageView;
    private int bonus;

    public Food(ImageView imageView, int bonus) {
        this.imageView = imageView;
        this.bonus = bonus;
        this.imageView.setFitHeight(55);
        this.imageView.setFitWidth(60);
    }

    public Food(ImageView imageView, int bonus, int width, int height) {
        this.imageView = imageView;
        this.bonus = bonus;
        this.imageView.setFitHeight(width);
        this.imageView.setFitWidth(height);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getBonus() {
        return bonus;
    }

}
