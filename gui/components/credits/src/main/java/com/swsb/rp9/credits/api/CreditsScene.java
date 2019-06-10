package com.swsb.rp9.credits.api;

import com.swsb.rp9.CreditsDefaultView;
import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;


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


}
