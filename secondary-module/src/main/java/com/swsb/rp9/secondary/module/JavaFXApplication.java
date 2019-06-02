package com.swsb.rp9.secondary.module;

import com.swsb.rp9.secondary.module.frondend.Dimension;
import com.swsb.rp9.secondary.module.frondend.Hero;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static com.swsb.rp9.secondary.module.frondend.Dimension.rectangle;
import static com.swsb.rp9.secondary.module.frondend.Dimension.square;
import static com.swsb.rp9.secondary.module.frondend.ImageBuilder.image;
import static com.swsb.rp9.secondary.module.frondend.Position.position;
import static com.swsb.rp9.secondary.module.frondend.SceneBuilder.scene;
import static javafx.scene.paint.Color.BEIGE;

public class JavaFXApplication extends Application {

    private static final int RECTANGLE_SIZE = 40;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;
    private static final Dimension STANDARD_SCENE_DIMENSION = rectangle(SCENE_WIDTH, SCENE_HEIGHT);
    private static final Random RANDOM = new Random();
    private static final List<Paint> colors = new ArrayList<>();
    private static final int SIDEPANEL_WIDTH = 160;
    private Hero hero;


    void run() {
        launch();
    }

    @Override
    public void init() {
        colors.add(image().url("textures/desert_sand2_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern());
        colors.add(image().url("textures/grass_green_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern());
        colors.add(image().url("textures/ground_cracks2y_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern());
        colors.add(image().url("textures/ground_mud2_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern());
        colors.add(image().url("textures/jungle_mntn2_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern());
        colors.add(image().url("textures/mntn_brown_h.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern());
        colors.add(image().url("textures/moss_plants_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern());
    }

    //Deze code is bweik lelijk, maar hopelijk kan je hier rap uit leren hoe iets werkt in javaFX
    //Je mag alles weggooien als je dat wil. Daar heb ik absoluut niks tegen
    @Override
    public void start(Stage primaryStage) {
        hero = new Hero(image().url("fairy.png").dimension(square(RECTANGLE_SIZE)).startingPosition(position(280, 240)).buildView());
        primaryStage.setScene(scene()
                .nodes(createSidepanel(), createGroupOfRectangles(), hero.getView())
                .dimension(STANDARD_SCENE_DIMENSION)
                .color(BEIGE)
                .onKeyPressed(hero.onKeyPressed())
                .build());
        primaryStage.setTitle("RP9");
        primaryStage.show();
    }

    private Node createSidepanel() {
        return image().url("fairy.png").dimension(rectangle(80, 360)).startingPosition(position(40, 0)).buildView();
    }

    private Node createGroupOfRectangles() {
        Collection<Node> rectangleList = new ArrayList<>();
        for (int x = 0; x < (SCENE_WIDTH - SIDEPANEL_WIDTH) / RECTANGLE_SIZE; x++) {
            for (int y = 0; y < SCENE_HEIGHT / RECTANGLE_SIZE; y++) {
                rectangleList.add(createStandardRectangle((x * RECTANGLE_SIZE) + SIDEPANEL_WIDTH, y * RECTANGLE_SIZE, getRandomColor(RANDOM)));
            }
        }
        return new Group(rectangleList);
    }

    private Node createStandardRectangle(int x, int y, Paint texture) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(RECTANGLE_SIZE);
        rectangle.setWidth(RECTANGLE_SIZE);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setFill(texture);
        return rectangle;
    }

    private Paint getRandomColor(Random random) {
        return colors.get(random.nextInt(colors.size()));
    }
}
