package com.swsb.rp9.start.menu;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.List;

import static com.swsb.rp9.core.SceneTransitionPosition.POSITION_ONE;
import static com.swsb.rp9.core.SceneTransitionPosition.POSITION_TWO;

public class StartMenuView extends GameView {

    private static final Color BACKGROUND_COLOR = Color.DARKBLUE;
    private static final Dimension DIMENSIONS = Dimension.square(750);

    public StartMenuView() {
        super(DIMENSIONS, BACKGROUND_COLOR);
    }

    @Override
    protected List<Node> createGuiElements() {
        return List.of(
                newGameButton(),
                creditsButton());
    }

    private Node newGameButton() {
        Button btn = new Button("NEW GAME");
        btn.setOnMouseClicked(event -> registerSceneTransition(POSITION_ONE));
        btn.setLayoutY(50);
        return btn;
    }

    private Node creditsButton() {
        Button btn = new Button("CREDITS");
        btn.setOnMouseClicked(event -> registerSceneTransition(POSITION_TWO));
        btn.setLayoutY(100);
        return btn;
    }

}
