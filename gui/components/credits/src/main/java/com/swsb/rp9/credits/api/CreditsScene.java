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
        sceneEvents();
    }

    @Override
    protected GameView createDefaultGameView() {
        return new CreditsDefaultView();
    }

    @Override
    public String getTitle() {
        return "Credits";
    }

    private void sceneEvents() {
        getScene()
                .setOnKeyPressed(event -> {
                    if (event.getCode().name().equals("B")) {
                        getGameView().registerSceneTransition(POSITION_ONE);
                    }
                });
    }
}
