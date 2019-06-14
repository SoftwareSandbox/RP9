package com.swsb.rp9.domain.api;

import com.swsb.rp9.domain.GameState;

public class CharacterSelectionState extends RestrictedState {

    public CharacterSelectionState(GameState gameState) {
        super(gameState);
    }

    public void setCharacterName(String characterName) {
        getGameState().setCharacterName(characterName);
    }
}
