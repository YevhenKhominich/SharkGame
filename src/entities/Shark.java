package entities;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import controllers.GameController;
import animation.SpriteAnimation;

public class Shark extends Pane {

    public SpriteAnimation animation;

    final private ImageView imageViewRightAndLeft = new ImageView(new Image("images/sharkLeftAndRight.png"));
    final private int count = 3;
    final private int columns = 3;
    final private int offsetX = 2;
    final private int offsetY = 0;
    final private int width = 152;
    final private int height = 63;

    private int score = 0;
    private Food removeBonus = null;

    public Shark() {
        imageViewRightAndLeft.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageViewRightAndLeft, Duration.millis(200), count, columns,
                offsetX, offsetY, width, height);

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
        for (int i = 0; i < GameController.foods.size(); i++) {
            if (this.getBoundsInParent().intersects(GameController.foods.get(i).getImageView().getBoundsInParent())) {
                removeBonus = GameController.foods.get(i);
                score += removeBonus.getBonus();
                GameController.label.setText("Points : " + score);
                GameController.foods.remove(removeBonus);
                GameController.root.getChildren().remove(removeBonus.getImageView());
            }

        }
    }

}
