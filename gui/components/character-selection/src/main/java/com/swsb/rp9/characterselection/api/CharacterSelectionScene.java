package com.swsb.rp9.characterselection.api;

import com.swsb.rp9.characterselection.CharacterSelectionView;
import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;

public class CharacterSelectionScene extends GameScene {

    public CharacterSelectionScene() {
        super(null);
    }

    @Override
    protected GameView createDefaultGameView() {
        return new CharacterSelectionView();
    }
}
