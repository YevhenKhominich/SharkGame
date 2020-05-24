package entities;

import animation.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WaterUp {

    private SpriteAnimation waterAnimation;
    final private ImageView imageView = new ImageView(new Image("images/waterUp.png"));
    final private int count = 7;
    final private int columns = 7;
    final private int offsetX = 4;
    final private int offsetY = 0;
    final private int width = 60;
    final private int height = 76;

    public ImageView getImageView() {
        return imageView;
    }

    public WaterUp() {
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        waterAnimation = new SpriteAnimation(imageView, Duration.millis(1500), count, columns,
                offsetX, offsetY, width, height);

    }

    public SpriteAnimation getWaterAnimation() {
        return waterAnimation;
    }
}
