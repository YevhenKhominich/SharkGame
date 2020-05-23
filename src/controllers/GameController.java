package controllers;

import entities.Food;
import entities.Shark;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController extends Application {

    public static Pane root = new Pane();
    public static Label label = new Label();
    public static ArrayList<Food> foods = new ArrayList<>();

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private FoodGenerator foodGenerator = new FoodGenerator();
    private Shark player = new Shark();
    private boolean gameStopped = false;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            update();
            bonus();
        }
    };

    //generate bonus food
    private void bonus() {
        int random = (int) Math.floor(Math.random() * 100);
        int x = (int) Math.floor(Math.random() * 600);
        if (random == 5) {
            Food food = foodGenerator.getRandomFood();
            ImageView foodImageView = food.getImageView();
            foodImageView.setTranslateX(x);
            foodImageView.setTranslateY(0);
            foods.add(food);
            root.getChildren().addAll(foodImageView);
        }
    }

    //update moving animation
    private void update() {

//        if (isPressed(KeyCode.SPACE)) {
//            gameStopped = true;
//        }
//
//        if (gameStopped) {
//            timer.stop();
//        }
        foods.forEach(bonus -> bonus.getImageView().setTranslateY(bonus.getImageView().getTranslateY() + 1));

        if (isPressed(KeyCode.UP)) {
            player.animation.play();
            player.moveY(-2);
        } else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.moveY(2);
        } else if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(67);
            player.moveX(2);
        } else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveX(-2);
        } else player.animation.stop();
    }


    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    @Override
    public void start(Stage primaryStage) {
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
        timer.start();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Shark Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}

