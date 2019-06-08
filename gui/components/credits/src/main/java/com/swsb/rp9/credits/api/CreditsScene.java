package com.swsb.rp9.credits.api;

import com.swsb.rp9.CreditsDefaultView;
import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;

import static com.swsb.rp9.core.SceneTransitionPosition.POSITION_ONE;

public class CreditsScene extends GameScene {

    public CreditsScene() {
        this(null);
    }

    public CreditsScene(GameView gameView) {
        super(gameView);
    }

    @Override
    protected GameView createDefaultGameView() {
        return new CreditsDefaultView();
    }

    @Override
    public String getTitle() {
        return "Credits";
    }

    @Override
    protected void registerSceneTransitionsForSceneEvents() {
        getScene().setOnKeyPressed(event -> {
            if (event.getCode().name().equals("B")) {
                getGameView().registerSceneTransition(POSITION_ONE);
            }
        });
    }

}
