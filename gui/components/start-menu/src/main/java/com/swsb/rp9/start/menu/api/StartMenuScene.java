package com.swsb.rp9.start.menu.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.start.menu.StartMenuView;

public class StartMenuScene extends GameScene {

    public StartMenuScene() {
        this(null);
    }

    public StartMenuScene(GameView gameView) {
        super(gameView);
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
