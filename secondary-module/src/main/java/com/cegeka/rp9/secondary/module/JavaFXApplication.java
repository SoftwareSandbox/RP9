package com.cegeka.rp9.secondary.module;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class JavaFXApplication extends Application {

    private static final int RECTANGLE_SIZE = 5;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;
    private static final Random RANDOM = new Random();
    private static final List<Color> colors = asList(Color.GREEN, Color.RED, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.PURPLE);

    public void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createGroupOfRectangles(), SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.setTitle("RP9");
        primaryStage.show();
    }

    private Group createGroupOfRectangles() {
        Collection<Node> rectangleList = new ArrayList<>();
        for (int x = 0; x < SCENE_WIDTH / RECTANGLE_SIZE; x++) {
            for (int y = 0; y < SCENE_HEIGHT / RECTANGLE_SIZE; y++) {
                rectangleList.add(createStandardRectangle(x * RECTANGLE_SIZE, y * RECTANGLE_SIZE, getRandomColor(RANDOM)));
            }
        }
        return new Group(rectangleList);
    }

    private Rectangle createStandardRectangle(int x, int y, Color color) {
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
