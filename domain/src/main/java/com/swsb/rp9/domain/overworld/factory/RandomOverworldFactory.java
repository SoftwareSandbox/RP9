package com.swsb.rp9.domain.overworld.factory;

import com.swsb.rp9.domain.Character;
import com.swsb.rp9.domain.api.Coordinate;
import com.swsb.rp9.domain.api.TileType;
import com.swsb.rp9.domain.overworld.Overworld;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.swsb.rp9.domain.api.Coordinate.coordinate;

public class RandomOverworldFactory implements OverworldFactory {
    private static final Random RANDOM = new Random();

    @Override
    public Overworld createOverworld(int width, int height, Character character) {
        Map<Coordinate, TileType> tiles = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.put(coordinate(x, y), getRandomTileType());
            }
        }
        return new Overworld(tiles, coordinate(5, 5), character);
    }

    private TileType getRandomTileType() {
        return TileType.values()[RANDOM.nextInt(TileType.values().length)];
    }
}
