package com.swsb.rp9.overworld;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.domain.api.OverworldState;
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
import static com.swsb.rp9.overworld.domain.Position.position;
import static com.swsb.rp9.overworld.view.HeroView.NUMBER_OF_FRAMES_NEEDED_FOR_MOVE;
import static java.util.stream.Collectors.toList;
import static javafx.scene.input.KeyCode.*;

public class OverworldDefaultView extends GameView<OverworldState> {

    public static final int RECTANGLE_SIZE = 40;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;
    private static final Dimension DIMENSIONS = rectangle(SCENE_WIDTH, SCENE_HEIGHT);

    private Overworld overworld;
    private HeroView heroView;
    private int numberOfFramesProcessing = 0;
    private boolean processingEvent;
    private KeyEvent keyDown = null;

    private Label characterNameLabel;
    private Label hitPointsLabel;

    public OverworldDefaultView() {
        super(DIMENSIONS, new OverworldState());
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
    protected Parent createGuiRootNode() {
        overworld = createOverworld();
        heroView = createHeroView(overworld.getHero());

        return new Group(
                createOverworldGroup(),
                heroView.getView(),
                createCharacterNameLabel(),
                createHitPointsLabel()
        );
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

        if (ESCAPE.equals(event.getCode())) {
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
        characterNameLabel.setText(getRestrictedState().getCharacterName());
        hitPointsLabel.setText("HP: " + getRestrictedState().getHitPoints());
    }

    private Node createCharacterNameLabel() {
        characterNameLabel = new Label(getRestrictedState().getCharacterName());
        characterNameLabel.setTextFill(Color.WHITE);
        return characterNameLabel;
    }

    private Node createHitPointsLabel() {
        hitPointsLabel = new Label("HP: " + getRestrictedState().getHitPoints());
        hitPointsLabel.setTextFill(Color.WHITE);
        hitPointsLabel.setLayoutY(40);
        return hitPointsLabel;
    }

    private HeroView createHeroView(Hero hero) {
        return new HeroView(hero);
    }

    private Overworld createOverworld() {
        OverworldFactory overworldFactory = new WalledOverworldFactory();
        return overworldFactory
                .createOverworld(SCENE_WIDTH / RECTANGLE_SIZE, SCENE_HEIGHT / RECTANGLE_SIZE);
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
        return position((coordinate.getX() * RECTANGLE_SIZE), coordinate.getY() * RECTANGLE_SIZE);
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
