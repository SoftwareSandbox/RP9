package com.swsb.rp9.shared;

import java.util.Map;

public class OverworldFactoryResult {

    private final Map<Coordinate, TileType> tiles;
    private final Map<Coordinate, ItemType> items;
    private final Coordinate characterStartingCoordinate;

    public OverworldFactoryResult(Map<Coordinate, TileType> tiles, Map<Coordinate, ItemType> items, Coordinate characterStartingCoordinate) {
        this.tiles = tiles;
        this.items = items;
        this.characterStartingCoordinate = characterStartingCoordinate;
    }

    public Map<Coordinate, TileType> getTiles() {
        return tiles;
    }

    public Map<Coordinate, ItemType> getItems() {
        return items;
    }

    public Coordinate getCharacterStartingCoordinate() {
        return characterStartingCoordinate;
    }
}
