package com.swsb.rp9.credits.api;

import com.swsb.rp9.CreditsDefaultView;
import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;

import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;


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
    protected void registerTransitionSlotsForSceneEvents() {
        getScene().setOnKeyPressed(event -> {
            if (event.getCode().name().equals("B")) {
                getGameView().registerTransitionSlot(TRANSITION_SLOT_ONE);
            }
        });
    }

}
