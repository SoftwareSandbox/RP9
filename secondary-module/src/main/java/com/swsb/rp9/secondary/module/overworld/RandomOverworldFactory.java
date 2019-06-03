package com.swsb.rp9.secondary.module.overworld;

import com.swsb.rp9.secondary.module.api.Coordinate;
import com.swsb.rp9.secondary.module.api.Overworld;
import com.swsb.rp9.secondary.module.api.OverworldFactory;
import com.swsb.rp9.secondary.module.api.TileType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomOverworldFactory implements OverworldFactory {
    private static final Random RANDOM = new Random();

    @Override
    public Overworld createOverworld(int width, int height) {
        Map<Coordinate, TileType> tiles = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.put(Coordinate.coordinate(x, y), getRandomTileType());
            }
        }
        return new Overworld(tiles);
    }

    private TileType getRandomTileType() {
        return TileType.values()[RANDOM.nextInt(TileType.values().length)];
    }
}
