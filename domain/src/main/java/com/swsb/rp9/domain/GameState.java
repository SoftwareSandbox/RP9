package com.swsb.rp9.domain;

import com.swsb.rp9.domain.api.Coordinate;
import com.swsb.rp9.domain.api.Direction;
import com.swsb.rp9.domain.api.ItemType;
import com.swsb.rp9.domain.api.TileType;
import com.swsb.rp9.domain.overworld.Overworld;
import com.swsb.rp9.domain.overworld.factory.OverworldFactory;
import com.swsb.rp9.domain.overworld.factory.WalledOverworldFactory;

import java.util.Map;

import static java.util.Objects.requireNonNullElseGet;

public final class GameState {

    private static GameState gameStateInstance;

    public static GameState getGameStateInstance() {
        return requireNonNullElseGet(gameStateInstance, () -> gameStateInstance = new GameState());
    }

    private Character character;
    private Overworld overworld;

    private GameState() {
        character = new Character();
        overworld = createOverworld(character);
    }

    private Overworld createOverworld(Character character) {
        OverworldFactory overworldFactory = new WalledOverworldFactory();
        return overworldFactory
                .createOverworld(16, 12, character);
    }

    public String getCharacterName() {
        return character.getName();
    }

    public void setCharacterName(String characterName) {
        this.character.setName(characterName);
    }

    public int getHitPoints() {
        return this.character.getHitPoints();
    }

    public void handleDirectionPressed(Direction direction) {
        overworld.handleDirectionPressed(direction);
    }

    public boolean isCollidedWithEnemy() {
        return overworld.isCollidedWithEnemy();
    }

    public void collisionHandled() {
        overworld.collisionHandled();
    }

    public Map<Coordinate, TileType> getTiles() {
        return overworld.getTiles();
    }

    public Coordinate getCharacterCoordinate() {
        return overworld.getCharacter().getCoordinate();
    }

    public Map<Coordinate, ItemType> getItems() {
        return overworld.getItems();
    }

    public boolean hasItemsChanged() {
        return overworld.hasItemsChanged();
    }
}
