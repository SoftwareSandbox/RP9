package com.swsb.rp9.overworld.domain.overworld.factory;

import com.swsb.rp9.overworld.domain.Coordinate;
import com.swsb.rp9.overworld.domain.overworld.Overworld;
import com.swsb.rp9.overworld.domain.overworld.TileType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.swsb.rp9.overworld.domain.Coordinate.coordinate;

public class RandomOverworldFactory implements OverworldFactory {
    private static final Random RANDOM = new Random();

    @Override
    public Overworld createOverworld(int width, int height) {
        Map<Coordinate, TileType> tiles = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.put(coordinate(x, y), getRandomTileType());
            }
        }
        return new Overworld(tiles, coordinate(5, 5));
    }

    private TileType getRandomTileType() {
        return TileType.values()[RANDOM.nextInt(TileType.values().length)];
    }
}
