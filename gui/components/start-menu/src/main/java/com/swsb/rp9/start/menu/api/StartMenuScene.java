package com.swsb.rp9.start.menu.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.start.menu.StartMenuView;
import javafx.scene.input.KeyCode;

public class StartMenuScene extends GameScene {

    // TODO: Temp fix (should not be static)
    private static final StartMenuView view = new StartMenuView();

    public StartMenuScene() {
        super(view.getDimension(), view.getBackgroundColor(), view.getNodes());
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
    }

    @Override
    public String getTitle() {
        return "StartMenu";
    }
}
