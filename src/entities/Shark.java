package entities;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.Main;
import sample.SpriteAnimation;

public class Shark extends Pane {

    ImageView imageView;
    int count = 3;
    int columns = 3;
    int offsetX = 2;
    int offsetY = 0;
    int width = 152;
    int height = 63;
    int score = 0;
    ImageView removeRect = null;
    public SpriteAnimation animation;

    public Shark(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, 120, 120));
        animation = new SpriteAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    public void moveX(int x) {
        boolean right = x > 0 ? true : false;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
            isBonusEat();
        }
    }

    public void moveY(int y) {
        boolean down = y > 0 ? true : false;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
            isBonusEat();
        }
    }

    public void isBonusEat() {
        Main.foods.forEach((rect) -> {
            if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                removeRect = rect;
                score++;
                Main.label.setText("Points : " + score);
            }

        });

        Main.foods.remove(removeRect);
        Main.root.getChildren().remove(removeRect);
    }
}
