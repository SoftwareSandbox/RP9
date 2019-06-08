package com.swsb.rp9.credits.api;

import com.swsb.rp9.CreditsDefaultView;
import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import javafx.scene.input.KeyCode;

public class CreditsScene extends GameScene {

    public CreditsScene() {
        this(null);
    }

    public CreditsScene(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void setOnSceneCompleteEventHandler() {
        getScene().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.B)) {
                getSceneTransitionState()
                        .markAsReadyForTransition()
                        .transitionToChild(0);
            }
        });
    }

    @Override
    protected GameView createDefaultGameView() {
        return new CreditsDefaultView();
    }

    @Override
    public String getTitle() {
        return "Credits";
    }
}
