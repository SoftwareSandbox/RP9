package com.swsb.rp9.domain;

import static java.util.Objects.requireNonNullElseGet;

public final class GameState {

    private static GameState gameStateInstance;

    public static GameState getGameStateInstance() {
        return requireNonNullElseGet(gameStateInstance, () -> gameStateInstance = new GameState());
    }

    private Character character;

    private GameState() {
        character = new Character();
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
