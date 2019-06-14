package com.swsb.rp9.domain.api;

import com.swsb.rp9.domain.GameState;

public abstract class RestrictedState {

    private GameState gameState;

    public RestrictedState(GameState gameState) {
        this.gameState = gameState;
    }

    protected GameState getGameState() {
        return gameState;
    }
}
