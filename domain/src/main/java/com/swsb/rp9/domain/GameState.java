package com.swsb.rp9.domain;

import com.swsb.rp9.basicoverworld.api.OverworldFactory;
import com.swsb.rp9.domain.overworld.Overworld;
import com.swsb.rp9.shared.Coordinate;
import com.swsb.rp9.shared.Direction;
import com.swsb.rp9.shared.ItemType;
import com.swsb.rp9.shared.TileType;

import java.util.Comparator;
import java.util.Map;
import java.util.ServiceLoader;

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
        OverworldFactory overworldFactory = determineOverworldFactory();
        return new Overworld(
                overworldFactory
                        .createOverworld(16, 12),
                character);
    }

    private OverworldFactory determineOverworldFactory() {
        return ServiceLoader.load(OverworldFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .max(Comparator.comparingInt(OverworldFactory::getLoadOrder))
                .orElseThrow(() -> new RuntimeException("No overworld factories were provided!"));
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
}
