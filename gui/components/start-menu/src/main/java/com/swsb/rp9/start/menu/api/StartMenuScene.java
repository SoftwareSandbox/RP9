package com.swsb.rp9.start.menu.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.start.menu.StartMenuDefaultView;

public class StartMenuScene extends GameScene {

    public StartMenuScene() {
        this(null);
    }

    public StartMenuScene(GameView gameView) {
        super(gameView);
    }

    @Override
    protected GameView createDefaultGameView() {
        return new StartMenuDefaultView();
    }

}
