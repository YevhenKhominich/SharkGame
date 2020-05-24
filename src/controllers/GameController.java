package controllers;

import entities.Food;
import entities.Shark;
import entities.WaterDown;
import entities.WaterUp;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController extends Application {

    public static Pane root = new Pane();
    public static Label label = new Label();
    public static List<Food> foods = new ArrayList<>();

    private List<WaterDown> waterDownAnimation = new ArrayList<>();
    private List<WaterUp> waterUpAnimation = new ArrayList<>();

    private Map<KeyCode, Boolean> keys = new HashMap<>();
    private FoodGenerator foodGenerator = new FoodGenerator();
    private Shark player = new Shark();

    private AnimationTimer timer = new AnimationTimer() {
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

        initRoot();
        initLabel();
        initWaterDownAnimation();
        initWaterUpAnimation();

        waterDownAnimation.forEach(waterDown -> waterDown.getWaterAnimation().play());
        waterUpAnimation.forEach(waterUp -> waterUp.getWaterAnimation().play());

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });

        timer.start();
        primaryStage.setTitle("Shark Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void initRoot() {
        root.setPrefSize(800, 600);
        root.getChildren().add(new ImageView("images/background.png"));
        root.getChildren().addAll(player, label);
    }

    private void initLabel() {
        label.setFont(Font.font("Calibri Bold Italic", FontPosture.ITALIC, 30));
        label.setTextFill(Paint.valueOf("#2E3348"));
        label.setText("Points : ");
        label.setTranslateX(10);
        label.setTranslateY(10);
    }

    private void initWaterDownAnimation() {

        int positionX = 30;

        for (int i = 0; i < 7; i++) {
            waterDownAnimation.add(new WaterDown());
        }

        for (WaterDown waterDown : waterDownAnimation) {
            ImageView waterImageView = waterDown.getImageView();
            waterImageView.setTranslateY(400);
            waterImageView.setTranslateX(positionX);
            positionX += 120;
            root.getChildren().add(waterImageView);
        }

    }

    private void initWaterUpAnimation() {

        int positionX = 25;

        for (int i = 0; i < 7; i++) {
            waterUpAnimation.add(new WaterUp());
        }

        for (WaterUp waterUp : waterUpAnimation) {
            ImageView waterImageView = waterUp.getImageView();
            waterImageView.setTranslateY(20);
            waterImageView.setTranslateX(positionX);
            positionX += 120;
            root.getChildren().add(waterImageView);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}

