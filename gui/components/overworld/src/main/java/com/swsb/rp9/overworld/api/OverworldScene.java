package com.swsb.rp9.overworld.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.overworld.OverworldDefaultView;

import java.util.Optional;

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
    protected Optional<String> getBackgroundMusicResourceUrl() {
        return Optional.of("/com/swsb/rp9/overworld/sound/Philip_Aldous-A_World_Of_Snow.wav");
    }
}
