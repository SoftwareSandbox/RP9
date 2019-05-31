package com.cegeka.rp9.secondary.module;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static javafx.scene.input.KeyCode.*;
import static javafx.scene.paint.Color.*;

public class JavaFXApplication extends Application {

    private static final int RECTANGLE_SIZE = 40;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;
    private static final Random RANDOM = new Random();
    private static final List<Color> colors = asList(GREEN, RED, BLUE, ORANGE, YELLOW, PURPLE);
    private static final int SIDEPANEL_WIDTH = 160;
    private ImageView character;

    public void run() {
        launch();
    }

    //Deze code is bweik lelijk, maar hopelijk kan je hier rap uit leren hoe iets werkt in javaFX
    //Je mag alles weggooien als je dat wil. Daar heb ik absoluut niks tegen
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Group(createSidepanel(), createGroupOfRectangles()), SCENE_WIDTH, SCENE_HEIGHT, BEIGE);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(DOWN)) {
                character.setY(character.getY() + 40);
            }
            if (event.getCode().equals(RIGHT)) {
                character.setX(character.getX() + 40);
            }
            if (event.getCode().equals(UP)) {
                character.setY(character.getY() - 40);
            }
            if (event.getCode().equals(LEFT)) {
                character.setX(character.getX() - 40);
            }

        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("RP9");
        primaryStage.show();
    }

    private Node createSidepanel() {
        ImageView imageView = new ImageView(new Image("fairy.png", 80, 360, true, true));
        imageView.setX(40);
        return imageView;
    }

    private Node createGroupOfRectangles() {
        Collection<Node> rectangleList = new ArrayList<>();
        for (int x = 0; x < (SCENE_WIDTH - SIDEPANEL_WIDTH) / RECTANGLE_SIZE; x++) {
            for (int y = 0; y < SCENE_HEIGHT / RECTANGLE_SIZE; y++) {
                rectangleList.add(createStandardRectangle((x * RECTANGLE_SIZE) + SIDEPANEL_WIDTH, y * RECTANGLE_SIZE, getRandomColor(RANDOM)));
            }
        }
        character = new ImageView(new Image("fairy.png", 40, 40, true, true));
        character.setX(280);
        character.setY(240);
        rectangleList.add(character);
        return new Group(rectangleList);
    }

    private Node createStandardRectangle(int x, int y, Color color) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(RECTANGLE_SIZE);
        rectangle.setWidth(RECTANGLE_SIZE);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setFill(color);
        return rectangle;
    }

    private Color getRandomColor(Random random) {
        return colors.get(random.nextInt(colors.size()));
    }
}
