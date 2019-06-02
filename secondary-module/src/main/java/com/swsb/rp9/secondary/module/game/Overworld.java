package com.swsb.rp9.secondary.module.game;

import java.util.Map;

public class Overworld {
    private final Map<Coordinate, TileType> tiles;

    public Overworld(Map<Coordinate, TileType> tiles) {
        this.tiles = tiles;
    }

    public Map<Coordinate, TileType> getTiles() {
        return tiles;
    }
}
