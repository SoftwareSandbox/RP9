package com.swsb.rp9.basicoverworld;

import com.swsb.rp9.basicoverworld.api.OverworldFactory;
import com.swsb.rp9.shared.Coordinate;
import com.swsb.rp9.shared.ItemType;
import com.swsb.rp9.shared.OverworldFactoryResult;
import com.swsb.rp9.shared.TileType;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static com.swsb.rp9.shared.Coordinate.coordinate;
import static com.swsb.rp9.shared.ItemType.CANDLE;
import static com.swsb.rp9.shared.TileType.*;

public class WalledOverworldFactory implements OverworldFactory {

    @Override
    public int getLoadOrder() {
        return 1;
    }

    @Override
    public OverworldFactoryResult createOverworld(int width, int height) {
        Map<Coordinate, TileType> tiles = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == width / 2 && y == 0) {
                    tiles.put(coordinate(x, y), WISP_ON_ICE_BORDER);
                } else if (x == 0 && y == 0) {
                    tiles.put(coordinate(x, y), ICE_CORNER_TOP_LEFT);
                } else if (y == 0 && x == width - 1) {
                    tiles.put(coordinate(x, y), ICE_CORNER_TOP_RIGHT);
                } else if (x == 0 && y == height - 1) {
                    tiles.put(coordinate(x, y), ICE_CORNER_BOTTOM_LEFT);
                } else if (x == width - 1 && y == height - 1) {
                    tiles.put(coordinate(x, y), ICE_CORNER_BOTTOM_RIGHT);
                } else if (x == 0) {
                    tiles.put(coordinate(x, y), ICE_SIDE_LEFT);
                } else if (x == width - 1) {
                    tiles.put(coordinate(x, y), ICE_SIDE_RIGHT);
                } else if (y == 0) {
                    tiles.put(coordinate(x, y), ICE_SIDE_TOP);
                } else if (y == height - 1) {
                    tiles.put(coordinate(x, y), ICE_SIDE_BOTTOM);
                } else {
                    tiles.put(coordinate(x, y), ICE);
                }
            }
        }
        HashMap<Coordinate, ItemType> items = new HashMap<>();
        IntStream.range(4, 12).forEach(i -> items.put(coordinate(i, 4), CANDLE));
        IntStream.range(4, 12).forEach(i -> items.put(coordinate(i, 6), CANDLE));
        IntStream.range(2, 10).forEach(i -> items.put(coordinate(7, i), CANDLE));
        IntStream.range(2, 10).forEach(i -> items.put(coordinate(9, i), CANDLE));
        return new OverworldFactoryResult(tiles, items, coordinate(1, 1));
    }

    public static OverworldFactory provides() {
        return new WalledOverworldFactory();
    }

}
