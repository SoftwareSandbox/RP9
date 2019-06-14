package com.swsb.rp9.fight.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.fight.FightView;

import java.util.Optional;

public class FightScene extends GameScene {

    public FightScene() {
        super(null);
    }

    protected Optional<String> getBackgroundMusicResourceUrl() {
        return Optional.of("/com/swsb/rp9/fight/sound/chaos-and-despair.mp3");
    }

    @Override
    protected GameView createDefaultGameView() {
        return new FightView();
    }
}
