package com.swsb.rp9.overworld.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.overworld.OverworldDefaultView;

public class OverworldScene extends GameScene {

    public OverworldScene() {
        this(null);
    }

    public OverworldScene(GameView gameView) {
        super(gameView);
    }

    @Override
    protected GameView createDefaultGameView() {
        return new OverworldDefaultView();
    }

    @Override
    public String getTitle() {
        return "Overworld";
    }


}
