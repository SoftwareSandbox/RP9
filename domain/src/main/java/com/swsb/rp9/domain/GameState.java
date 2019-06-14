package com.swsb.rp9.domain;

public class GameState {

    private Character character;

    public GameState(Character character) {
        this.character = character;
    }

    public String getCharacterName() {
        return character.getName();
    }

    public void setCharacterName(String characterName) {
        this.character.setName(characterName);
    }

    public int getHitpoints() {
        return this.character.getHitPoints();
    }
}
