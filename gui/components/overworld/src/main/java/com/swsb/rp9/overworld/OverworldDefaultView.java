package com.swsb.rp9.overworld;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.core.Position;
import com.swsb.rp9.domain.api.Coordinate;
import com.swsb.rp9.domain.api.Direction;
import com.swsb.rp9.domain.api.OverworldState;
import com.swsb.rp9.overworld.view.CharacterView;
import com.swsb.rp9.overworld.view.ItemsView;
import com.swsb.rp9.overworld.view.MenuView;
import com.swsb.rp9.overworld.view.TilesView;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.Position.position;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_TWO;
import static com.swsb.rp9.domain.api.Direction.STAND_STILL;
import static com.swsb.rp9.overworld.view.CharacterView.NUMBER_OF_FRAMES_NEEDED_FOR_MOVE;
import static javafx.scene.input.KeyCode.*;

public class OverworldDefaultView extends GameView<OverworldState> {

    public static final int RECTANGLE_SIZE = 40;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;
    private static final Dimension DIMENSIONS = rectangle(SCENE_WIDTH, SCENE_HEIGHT);
    private static final int ITEM_OFFSET = 0;

    private int numberOfFramesProcessing = 0;
    private boolean processingEvent;
    private KeyEvent keyDown = null;

    private CharacterView characterView;
    private MenuView menuView;
    private ItemsView itemsView;
    private TilesView tilesView;

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
        characterView = new CharacterView(getRestrictedState());
        menuView = new MenuView(getRestrictedState());
        itemsView = new ItemsView(getRestrictedState());
        tilesView = new TilesView(getRestrictedState());

        return new Group(
                tilesView.getView(),
                itemsView.getView(),
                characterView.getView(),
                menuView.getView()
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

    @Override
    public void redraw() {
        handleKeyDown(keyDown);
        if (processingEvent) {
            numberOfFramesProcessing++;
        }
        itemsView.redraw();
        characterView.redraw();
        menuView.redraw();
    }

    private void handleKeyDown(KeyEvent event) {
        if (event == null) {
            return;
        }

        if (numberOfFramesProcessing > NUMBER_OF_FRAMES_NEEDED_FOR_MOVE) {
            processingEvent = false;
        }

        if (!processingEvent && !menuView.isVisible()) {
            processingEvent = true;
            numberOfFramesProcessing = 0;
            getRestrictedState().handleDirectionPressed(toDirection(event));
            if (getRestrictedState().collidedWithEnemy()) {
                registerTransitionSlot(TRANSITION_SLOT_TWO);
                getRestrictedState().collisionHandled();
            }
        }

        if (ESCAPE.equals(event.getCode())) {
            registerTransitionSlot(TRANSITION_SLOT_ONE);
            keyDown = null;
        }
        if (event.getCode().name().equals("M")) {
            menuView.onKeyPressed();
            keyDown = null;
        }
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
