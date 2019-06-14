package com.swsb.rp9.domain.api;

import com.swsb.rp9.domain.GameState;

import static com.swsb.rp9.domain.GameState.getGameStateInstance;

public abstract class RestrictedState {

    private GameState gameState;

    RestrictedState() {
        this.gameState = getGameStateInstance();
    }

    GameState getGameState() {
        return gameState;
    }
}
