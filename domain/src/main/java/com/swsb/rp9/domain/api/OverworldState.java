package com.swsb.rp9.domain.api;

import com.swsb.rp9.domain.GameState;

public class OverworldState extends RestrictedState {

    public OverworldState(GameState gameState) {
        super(gameState);
    }

    public String getCharacterName() {
        return getGameState().getCharacterName();
    }

    public int getHitPoints() {
        return getGameState().getHitpoints();
    }


}
