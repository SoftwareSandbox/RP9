package com.swsb.rp9.start.menu.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.start.menu.StartMenuView;
import javafx.scene.input.KeyCode;

public class StartMenuScene extends GameScene {

    public StartMenuScene() {
        this(null);
    }

    public StartMenuScene(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void setOnSceneCompleteEventHandler() {
        getScene().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.N)) {
                getSceneTransitionState()
                        .markAsReadyForTransition()
                        .transitionToChild(0);
            }
        });
        getScene().setOnMouseClicked(event ->
                getSceneTransitionState()
                        .markAsReadyForTransition()
                        .transitionToChild(1)
        );
    }

    @Override
    protected GameView createDefaultGameView() {
        return new StartMenuView();
    }

    @Override
    public String getTitle() {
        return "StartMenu";
    }
}
