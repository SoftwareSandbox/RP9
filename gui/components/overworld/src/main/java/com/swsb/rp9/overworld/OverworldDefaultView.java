package com.swsb.rp9.overworld;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.overworld.domain.Coordinate;
import com.swsb.rp9.overworld.domain.Direction;
import com.swsb.rp9.overworld.domain.Position;
import com.swsb.rp9.overworld.domain.RectangleBuilder;
import com.swsb.rp9.overworld.domain.hero.Hero;
import com.swsb.rp9.overworld.domain.overworld.Overworld;
import com.swsb.rp9.overworld.domain.overworld.factory.OverworldFactory;
import com.swsb.rp9.overworld.domain.overworld.factory.WalledOverworldFactory;
import com.swsb.rp9.overworld.view.HeroView;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;
import static com.swsb.rp9.overworld.domain.Direction.STAND_STILL;
import static com.swsb.rp9.overworld.domain.ImageBuilder.image;
import static com.swsb.rp9.overworld.domain.Position.position;
import static com.swsb.rp9.overworld.view.HeroView.NUMBER_OF_FRAMES_NEEDED_FOR_MOVE;
import static java.util.stream.Collectors.toList;
import static javafx.scene.input.KeyCode.*;

public class OverworldDefaultView extends GameView {

    public static final int RECTANGLE_SIZE = 40;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;
    private static final int SIDEPANEL_WIDTH = 160;
    private static final Dimension DIMENSIONS = rectangle(SCENE_WIDTH, SCENE_HEIGHT);

    private Overworld overworld;
    private HeroView heroView;
    private int numberOfFramesProcessing = 0;
    private boolean processingEvent;
    private KeyEvent keyDown = null;

    public OverworldDefaultView() {
        super(DIMENSIONS);
    }

    @Override
    public String getTitle() {
        return "Overworld";
    }

    @Override
    public String getStyleSheetLocation() {
        return getClass().getResource("/com/swsb/rp9/overworld/styles/overworld.css").toExternalForm();
    }

    @Override
    protected void setOnKeyPressedForScene(KeyEvent event) {
        this.keyDown = event;
    }

    @Override
    protected void setOnKeyRelease(KeyEvent event) {
        this.keyDown = null;
    }

    private void handleKeyDown(KeyEvent event) {
        if(event == null){
            return;
        }
        if(numberOfFramesProcessing > NUMBER_OF_FRAMES_NEEDED_FOR_MOVE) {
            processingEvent = false;
        }

        if(!processingEvent) {
            processingEvent = true;
            numberOfFramesProcessing = 0;
            overworld.handleDirectionPressed(toDirection(event));
        }

        if (event.getCode().name().equals("B")) {
            registerTransitionSlot(TRANSITION_SLOT_ONE);
            keyDown = null;
        }
    }


    @Override
    public void redraw() {
        handleKeyDown(keyDown);
        if(processingEvent) {
            numberOfFramesProcessing++;
        }

        heroView.redraw();
    }

    @Override
    protected Parent createGuiRootNode() {
        overworld = createOverworld();
        heroView = createHeroView(overworld.getHero());

        return new Group(
                createSidePanel(),
                createOverworldGroup(),
                heroView.getView(),
                createBackLabel()
        );
    }

    private Label createBackLabel() {
        var label = new Label("Press B to go back!");
        label.setTextFill(Color.WHITE);
        return label;
    }

    private HeroView createHeroView(Hero hero) {
        return new HeroView(hero);
    }

    private Overworld createOverworld() {
        OverworldFactory overworldFactory = new WalledOverworldFactory();
        return overworldFactory
                .createOverworld((SCENE_WIDTH - SIDEPANEL_WIDTH) / RECTANGLE_SIZE, SCENE_HEIGHT / RECTANGLE_SIZE);
    }

    private Node createSidePanel() {
        return image()
                .url(getClass().getResource("/com/swsb/rp9/overworld/sprites/hero/hero.png").toExternalForm())
                .dimension(rectangle(80, 360))
                .startingPosition(position(40, 0))
                .buildView();
    }

    private Node createOverworldGroup() {
        return new Group(overworld.getTiles().entrySet().stream()
                .map(entry -> RectangleBuilder.rectangle()
                        .color(entry.getValue().toTexture(RECTANGLE_SIZE))
                        .dimension(square(RECTANGLE_SIZE))
                        .position(toPosition(entry.getKey()))
                        .build())
                .collect(toList()));
    }

    public static Position toPosition(Coordinate coordinate) {
        return position((coordinate.getX() * RECTANGLE_SIZE) + SIDEPANEL_WIDTH, coordinate.getY() * RECTANGLE_SIZE);
    }

    private Direction toDirection(KeyEvent event) {
        if (event.getCode().equals(DOWN)) {
            return Direction.DOWN;
        }
        if (event.getCode().equals(RIGHT)) {
            return Direction.RIGHT;
        }
        if (event.getCode().equals(UP)) {
            return Direction.UP;
        }
        if (event.getCode().equals(LEFT)) {
            return Direction.LEFT;
        }
        return STAND_STILL;
    }
}
