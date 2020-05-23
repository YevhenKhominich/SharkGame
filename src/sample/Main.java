package sample;

import entities.Shark;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    private FoodBuilder foodBuilder = new FoodBuilder();

    public static int points;
    public static Label label = new Label();
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    public static ArrayList<ImageView> foods = new ArrayList<>();

    Image image = new Image("images/sharkLeftAndRight.png");
    ImageView imageView = new ImageView(image);
    Shark player = new Shark(imageView);
    public static Pane root = new Pane();

    public void bonus() {
        int random = (int) Math.floor(Math.random() * 100);
        int x = (int) Math.floor(Math.random() * 600);
        if (random == 5) {
            ImageView food = foodBuilder.getBread().getImageView();
            food.setTranslateX(x);
            food.setTranslateY(0);
            foods.add(food);
            root.getChildren().addAll(food);
        }
    }

    public void update() {


        foods.forEach(bonus -> bonus.setTranslateY(bonus.getTranslateY() + 1));

        if (isPressed(KeyCode.UP)) {
            player.animation.play();
            player.animation.setOffsetY(285);
            player.moveY(-2);
        } else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveY(2);
        } else if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(67);
            player.moveX(2);
        } else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveX(-2);
        } else {

            player.animation.stop();
        }
    }

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        label.setText("Points : ");
        label.setTranslateX(10);
        label.setTranslateY(10);
        root.setPrefSize(800, 600);
        root.getChildren().add(new ImageView("images/background.png"));
        root.getChildren().addAll(player, label);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                bonus();
            }
        };
        timer.start();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}

