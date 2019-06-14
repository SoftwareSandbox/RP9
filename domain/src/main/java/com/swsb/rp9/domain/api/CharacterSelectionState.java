package com.swsb.rp9.domain.api;

public class CharacterSelectionState extends RestrictedState {

    public void setCharacterName(String characterName) {
        getGameState().setCharacterName(characterName);
    }

    public void setCharacterType(CharacterType characterType) {
        getGameState().setCharacterType(characterType);
    }
}
