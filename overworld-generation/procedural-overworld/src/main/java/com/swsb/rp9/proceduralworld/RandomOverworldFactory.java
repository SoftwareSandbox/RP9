package com.swsb.rp9.proceduralworld;

import com.swsb.rp9.basicoverworld.api.OverworldFactory;
import com.swsb.rp9.shared.Coordinate;
import com.swsb.rp9.shared.ItemCollection;
import com.swsb.rp9.shared.OverworldFactoryResult;
import com.swsb.rp9.shared.TileType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.swsb.rp9.shared.Coordinate.coordinate;


public class RandomOverworldFactory implements OverworldFactory {
    private static final Random RANDOM = new Random();

    @Override
    public OverworldFactoryResult createOverworld(int width, int height) {
        Map<Coordinate, TileType> tiles = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.put(coordinate(x, y), getRandomTileType());
            }
        }
        return new OverworldFactoryResult(tiles, new ItemCollection(Collections.emptyMap()), coordinate(5, 5));
    }

    @Override
    public int getLoadOrder() {
        return 0;
    }

    private TileType getRandomTileType() {
        return TileType.values()[RANDOM.nextInt(TileType.values().length)];
    }

    public static OverworldFactory provides() {
        return new RandomOverworldFactory();
    }
}
