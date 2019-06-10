package com.swsb.rp9.overworld;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.overworld.domain.Coordinate;
import com.swsb.rp9.overworld.domain.Position;
import com.swsb.rp9.overworld.domain.RectangleBuilder;
import com.swsb.rp9.overworld.domain.hero.Hero;
import com.swsb.rp9.overworld.domain.overworld.Overworld;
import com.swsb.rp9.overworld.domain.overworld.factory.OverworldFactory;
import com.swsb.rp9.overworld.domain.overworld.factory.WalledOverworldFactory;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;
import static com.swsb.rp9.overworld.domain.ImageBuilder.image;
import static com.swsb.rp9.overworld.domain.Position.position;
import static java.util.stream.Collectors.toList;

public class OverworldDefaultView extends GameView {

    private static final int RECTANGLE_SIZE = 40;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;
    private static final int SIDEPANEL_WIDTH = 160;
    private static final Dimension DIMENSIONS = rectangle(SCENE_WIDTH, SCENE_HEIGHT);

    private Hero hero;
    private Overworld overworld;

    public OverworldDefaultView() {
        super(DIMENSIONS);
    }

    @Override
    public String getTitle() {
        return "Overworld";
    }

    @Override
    public String getStyleSheetLocation() {
        return "com/swsb/rp9/overworld/styles/overworld.css";
    }

    @Override
    protected void setOnKeyPressedForScene(KeyEvent event) {
        overworld.onKeyPressed(event);

        if (event.getCode().name().equals("B")) {
            registerTransitionSlot(TRANSITION_SLOT_ONE);
        }
    }

    @Override
    public GameView redraw() {
        hero.heroStance();
        return this;
    }

    @Override
    protected Parent createGuiRootNode() {
        hero = createHero();
        overworld = createOverworld(hero);

        return new Group(
                createSidePanel(),
                createOverworldGroup(),
                hero.getView(),
                createBackLabel()
        );
    }

    private Label createBackLabel() {
        var label = new Label("Press B to go back!");
        label.setTextFill(Color.WHITE);
        return label;
    }

    private Hero createHero() {
        return new Hero(image()
                .url("com/swsb/rp9/overworld/sprites/hero/hero.png")
                .dimension(square(RECTANGLE_SIZE))
                .startingPosition(position(280, 240))
                .buildView());
    }

    private Overworld createOverworld(Hero hero) {
        OverworldFactory overworldFactory = new WalledOverworldFactory();
        var overworld = overworldFactory
                .createOverworld((SCENE_WIDTH - SIDEPANEL_WIDTH) / RECTANGLE_SIZE, SCENE_HEIGHT / RECTANGLE_SIZE);
        overworld.setHero(hero);
        return overworld;
    }

    private Node createSidePanel() {
        return image()
                .url("com/swsb/rp9/overworld/sprites/hero/hero.png")
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

    private Position toPosition(Coordinate coordinate) {
        return position((coordinate.getX() * RECTANGLE_SIZE) + SIDEPANEL_WIDTH, coordinate.getY() * RECTANGLE_SIZE);
    }

}
