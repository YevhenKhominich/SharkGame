package entities;

import animation.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WaterDown {
    private SpriteAnimation waterAnimation;
    final private ImageView imageView = new ImageView(new Image("images/waterDown.png"));
    final private int count = 7;
    final private int columns = 7;
    final private int offsetX = 0;
    final private int offsetY = 0;
    final private int width = 30;
    final private int height = 80;

    public ImageView getImageView() {
        return imageView;
    }

    public WaterDown() {
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        waterAnimation = new SpriteAnimation(imageView, Duration.millis(1000), count, columns,
                offsetX, offsetY, width, height);

    }

    public SpriteAnimation getWaterAnimation() {
        return waterAnimation;
    }
}
