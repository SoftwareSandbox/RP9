package com.swsb.rp9.fight.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.fight.FightView;

public class FightScene extends GameScene {

    public FightScene() {
        super(null);
    }

    @Override
    protected GameView createDefaultGameView() {
        return new FightView();
    }
}
