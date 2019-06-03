package com.swsb.rp9.secondary.module;

import com.swsb.rp9.secondary.module.frondend.Dimension;
import com.swsb.rp9.secondary.module.frondend.Hero;
import com.swsb.rp9.secondary.module.frondend.Position;
import com.swsb.rp9.secondary.module.game.Coordinate;
import com.swsb.rp9.secondary.module.game.OverworldFactory;
import com.swsb.rp9.secondary.module.game.RandomOverworldFactory;
import com.swsb.rp9.secondary.module.game.TileType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import static com.swsb.rp9.secondary.module.frondend.Dimension.rectangle;
import static com.swsb.rp9.secondary.module.frondend.Dimension.square;
import static com.swsb.rp9.secondary.module.frondend.ImageBuilder.image;
import static com.swsb.rp9.secondary.module.frondend.Position.position;
import static com.swsb.rp9.secondary.module.frondend.RectangleBuilder.rectangle;
import static com.swsb.rp9.secondary.module.frondend.SceneBuilder.scene;
import static java.util.stream.Collectors.toList;
import static javafx.scene.paint.Color.BEIGE;

public class JavaFXApplication extends Application {

    private static final int RECTANGLE_SIZE = 40;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;
    private static final Dimension STANDARD_SCENE_DIMENSION = rectangle(SCENE_WIDTH, SCENE_HEIGHT);
    private static final int SIDEPANEL_WIDTH = 160;
    private Hero hero;

    private OverworldFactory overworldFactory = new RandomOverworldFactory();


    void run() {
        launch();
    }

    @Override
    public void init() {
    }

    //Deze code is bweik lelijk, maar hopelijk kan je hier rap uit leren hoe iets werkt in javaFX
    //Je mag alles weggooien als je dat wil. Daar heb ik absoluut niks tegen
    @Override
    public void start(Stage primaryStage) {
        hero = new Hero(image().url("com/swsb/rp9/secondary/module/sprite/fairy.png").dimension(square(RECTANGLE_SIZE)).startingPosition(position(280, 240)).buildView());
        primaryStage.setScene(scene()
                .nodes(createSidePanel(), overworld(), hero.getView())
                .dimension(STANDARD_SCENE_DIMENSION)
                .color(BEIGE)
                .onKeyPressed(hero.onKeyPressed())
                .build());
        primaryStage.setTitle("RP9");
        primaryStage.show();
    }

    private Node createSidePanel() {
        return image().url("com/swsb/rp9/secondary/module/sprite/fairy.png").dimension(rectangle(80, 360)).startingPosition(position(40, 0)).buildView();
    }

    private Node overworld() {
        return new Group(overworldFactory.createOverworld((SCENE_WIDTH - SIDEPANEL_WIDTH) / RECTANGLE_SIZE, SCENE_HEIGHT / RECTANGLE_SIZE).getTiles().entrySet().stream()
                .map(entry -> rectangle()
                                .color(toTexture(entry.getValue()))
                                .dimension(square(RECTANGLE_SIZE))
                                .position(toPosition(entry.getKey()))
                                .build())
                .collect(toList()));
    }

    private Paint toTexture(TileType tileType) {
        switch (tileType) {
            case DESERT:
                return image().url("com/swsb/rp9/secondary/module/texture/desert_sand2_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern();
            case GRASS:
                return image().url("com/swsb/rp9/secondary/module/texture/grass_green_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern();
            case CRACKS:
                return image().url("com/swsb/rp9/secondary/module/texture/ground_cracks2y_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern();
            case MUD:
                return image().url("com/swsb/rp9/secondary/module/texture/ground_mud2_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern();
            case JUNGLE:
                return image().url("com/swsb/rp9/secondary/module/texture/jungle_mntn2_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern();
            case MOUNTAIN:
                return image().url("com/swsb/rp9/secondary/module/texture/mntn_brown_h.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern();
            case MOSS:
                return image().url("com/swsb/rp9/secondary/module/texture/moss_plants_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern();
            case SNOW:
                return image().url("com/swsb/rp9/secondary/module/texture/snow2ice_d.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern();
        }
        throw new RuntimeException("Unknown tileType type" + tileType);
    }

    private Position toPosition(Coordinate coordinate) {
        return position((coordinate.getX() * RECTANGLE_SIZE) + SIDEPANEL_WIDTH, coordinate.getY() * RECTANGLE_SIZE);
    }

}
