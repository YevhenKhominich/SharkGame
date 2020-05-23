package entities;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.Main;
import sample.SpriteAnimation;

public class Shark extends Pane {

    private ImageView imageViewRightAndLeft = new ImageView(new Image("images/sharkLeftAndRight.png"));
    private int count = 3;
    private int columns = 3;
    private int score = 0;
    public ImageView removeRect = null;
    public SpriteAnimation animationRightAndLeft;

    public Shark() {
        imageViewRightAndLeft.setViewport(new Rectangle2D(2, 0, 152, 63));
        animationRightAndLeft = new SpriteAnimation(imageViewRightAndLeft, Duration.millis(200), count, columns,
                2, 0, 152, 63);

        getChildren().addAll(imageViewRightAndLeft);


    }

    public void moveX(int x) {
        boolean right = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
            isBonusEat();
        }
    }

    public void moveY(int y) {
        boolean down = y > 0;
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
