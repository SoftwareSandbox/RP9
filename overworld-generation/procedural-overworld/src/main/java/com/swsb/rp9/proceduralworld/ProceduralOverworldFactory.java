package com.swsb.rp9.proceduralworld;

import com.swsb.rp9.basicoverworld.api.OverworldFactory;
import com.swsb.rp9.shared.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import static com.swsb.rp9.shared.Coordinate.coordinate;
import static com.swsb.rp9.shared.ItemType.CANDLE;
import static com.swsb.rp9.shared.ItemType.WISP;
import static com.swsb.rp9.shared.TileType.*;


public class ProceduralOverworldFactory implements OverworldFactory {
    private static final Random RANDOM = new Random();

    @Override
    public OverworldFactoryResult createOverworld(int width, int height) {
        List<TileType> tilesSeed = generateSeedForMap();
        Map<Coordinate, TileType> tiles = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == 0 && y == 0) {
                    tiles.put(coordinate(x, y), WALL);
                } else if (y == 0 && x == width - 1) {
                    tiles.put(coordinate(x, y), WALL);
                } else if (x == 0 && y == height - 1) {
                    tiles.put(coordinate(x, y), WALL);
                } else if (x == width - 1 && y == height - 1) {
                    tiles.put(coordinate(x, y), WALL);
                } else if (x == 0) {
                    tiles.put(coordinate(x, y), WALL);
                } else if (x == width - 1) {
                    tiles.put(coordinate(x, y), WALL);
                } else if (y == 0) {
                    tiles.put(coordinate(x, y), WALL);
                } else if (y == height - 1) {
                    tiles.put(coordinate(x, y), WALL);
                } else {
                    tiles.put(coordinate(x, y), getWalkableTileType(tilesSeed));
                }
            }
        }
        HashMap<Coordinate, ItemType> items = new HashMap<>();
        IntStream.range(1, width - 1).forEach(i -> items.put(coordinate(i, (int) (5 * Math.random() + 1)), CANDLE));

        items.put(coordinate(3, 5 + (int) (Math.random() * 5)), WISP);
        return new OverworldFactoryResult(tiles, new ItemCollection(items), coordinate(1, 1));
    }

    @Override
    public int getLoadOrder() {
        return 2;
    }

    private TileType getWalkableTileType(List<TileType> tilesSeed) {
        return tilesSeed.get(decideNextTileIndex(tilesSeed));
    }

    private List<TileType> generateSeedForMap() {
        if ((int)(Math.random() * 2) == 1) {
            return List.of(
                    WALL,
                    GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS,
                    DESERT, DESERT, DESERT, DESERT, DESERT);
        } else {
            return List.of(
                    WALL,
                    GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS,
                    ICE, ICE, ICE, ICE);
        }
    }

    private int decideNextTileIndex(List<TileType> tiles) {
        return RANDOM.nextInt(tiles.size());
    }

    public static OverworldFactory provides() {
        return new ProceduralOverworldFactory();
    }
}
