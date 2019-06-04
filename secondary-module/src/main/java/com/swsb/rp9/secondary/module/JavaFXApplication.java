package com.swsb.rp9.secondary.module;

import com.swsb.rp9.secondary.module.api.Coordinate;
import com.swsb.rp9.secondary.module.api.Overworld;
import com.swsb.rp9.secondary.module.api.OverworldFactory;
import com.swsb.rp9.secondary.module.api.TileType;
import com.swsb.rp9.secondary.module.frondend.Dimension;
import com.swsb.rp9.secondary.module.frondend.Hero;
import com.swsb.rp9.secondary.module.frondend.Position;
import com.swsb.rp9.secondary.module.overworld.WalledOverworldFactory;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

import static com.swsb.rp9.secondary.module.frondend.Dimension.rectangle;
import static com.swsb.rp9.secondary.module.frondend.Dimension.square;
import static com.swsb.rp9.secondary.module.frondend.ImageBuilder.image;
import static com.swsb.rp9.secondary.module.frondend.Position.position;
import static com.swsb.rp9.secondary.module.frondend.RectangleBuilder.rectangle;
import static com.swsb.rp9.secondary.module.frondend.SceneBuilder.scene;
import static java.util.stream.Collectors.toList;
import static javafx.scene.paint.Color.BEIGE;
import static javafx.scene.paint.Color.WHITESMOKE;

public class JavaFXApplication extends Application {

    private static final int RECTANGLE_SIZE = 40;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;
    private static final Dimension STANDARD_SCENE_DIMENSION = rectangle(SCENE_WIDTH, SCENE_HEIGHT);
    private static final int SIDEPANEL_WIDTH = 160;
    private Hero hero;

    private OverworldFactory overworldFactory = new WalledOverworldFactory();
    private Overworld overworld = overworldFactory.createOverworld((SCENE_WIDTH - SIDEPANEL_WIDTH) / RECTANGLE_SIZE, SCENE_HEIGHT / RECTANGLE_SIZE);


    void run() {
        launch();
    }

    @Override
    public void init() {
    }

    @Override
    public void start(Stage primaryStage) {
        hero = new Hero(image().url("com/swsb/rp9/secondary/module/sprite/fairy.png").dimension(square(RECTANGLE_SIZE)).startingPosition(position(280, 240)).buildView());
        MediaPlayer mediaPlayer = createMediaPlayer();
        Scene overworldScene = createOverworldScene();
        Scene startScreen = createStartScreenScene(newGameListener(primaryStage, overworldScene, mediaPlayer));

        primaryStage.setScene(startScreen);
        primaryStage.setTitle("RP9");
        primaryStage.show();
    }

    private MediaPlayer createMediaPlayer() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource("sound/mountain_trolls.mp3").toExternalForm()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        return mediaPlayer;
    }

    private EventHandler<? super KeyEvent> newGameListener(Stage primaryStage, Scene overworldScene, MediaPlayer mediaPlayer) {
        return event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    primaryStage.setScene(overworldScene);
                    mediaPlayer.stop();
                }
            };
    }

    private Scene createStartScreenScene(EventHandler<? super KeyEvent> newGameEventHandler) {
        VBox verticalMenu = createMenu(newGameEventHandler);
        TilePane startScreen = new TilePane(verticalMenu);
        startScreen.setBackground(createStartScreenBackground());
        startScreen.setAlignment(Pos.CENTER);

        Scene scene = new Scene(startScreen, STANDARD_SCENE_DIMENSION.getWidth(), STANDARD_SCENE_DIMENSION.getHeight());
        scene.getStylesheets().add(this.getClass().getResource("styles/start-screen.css").toExternalForm());
        return scene;
    }

    private Background createStartScreenBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("com/swsb/rp9/secondary/module/background/glacial_mountains_preview_lightened.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(0, 0, false, false, false, true));
        return new Background(backgroundImage);
    }

    private VBox createMenu(EventHandler<? super KeyEvent> newGameEventHandler) {
        ToggleGroup menuToggleGroup = new ToggleGroup();

        RadioButton newGameButton = new RadioButton("New game");
        newGameButton.setTextFill(WHITESMOKE);
        newGameButton.setSelected(true);
        newGameButton.setToggleGroup(menuToggleGroup);
        newGameButton.setOnKeyPressed(newGameEventHandler);

        RadioButton creditsButton = new RadioButton("Credits");
        creditsButton.setTextFill(WHITESMOKE);
        creditsButton.setToggleGroup(menuToggleGroup);

        return new VBox(newGameButton, creditsButton);
    }

    private Scene createOverworldScene() {
        overworld.setHero(hero);
        return scene()
                .nodes(createSidePanel(), overworld(), hero.getView())
//                .root(loadXmlFile("com/swsb/rp9/secondary/module/fxml/test.fxml"))
                .dimension(STANDARD_SCENE_DIMENSION)
                .color(BEIGE)
                .onKeyPressed(overworld.onKeyPressed())
                .build();
    }

    private Parent loadXmlFile(String xml) {
        try {
            final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            return FXMLLoader.load(contextClassLoader.getResource(xml));
        } catch (IOException e) {
            throw new RuntimeException("cannot find file" + xml);
        }
    }

    private Node createSidePanel() {
        return image().url("com/swsb/rp9/secondary/module/sprite/fairy.png").dimension(rectangle(80, 360)).startingPosition(position(40, 0)).buildView();
    }

    private Node overworld() {
        return new Group(overworld.getTiles().entrySet().stream()
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
            case WALL:
                return image().url("com/swsb/rp9/secondary/module/texture/wall.jpg").dimension(square(RECTANGLE_SIZE)).buildPattern();

        }
        throw new RuntimeException("Unknown tileType type" + tileType);
    }

    private Position toPosition(Coordinate coordinate) {
        return position((coordinate.getX() * RECTANGLE_SIZE) + SIDEPANEL_WIDTH, coordinate.getY() * RECTANGLE_SIZE);
    }

}
