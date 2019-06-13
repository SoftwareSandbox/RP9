package com.swsb.rp9.start.menu.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.start.menu.StartMenuDefaultView;

import java.util.Optional;

public class StartMenuScene extends GameScene {

    public StartMenuScene() {
        this(null);
    }

    private StartMenuScene(GameView gameView) {
        super(gameView);
    }

    @Override
    protected GameView createDefaultGameView() {
        return new StartMenuDefaultView();
    }

    @Override
    protected Optional<String> getBackgroundMusicResourceUrl() {
        return Optional.of("/com/swsb/rp9/start/menu/sound/Peter_Batemon-Fantastic_Space.mp3");
    }

}
