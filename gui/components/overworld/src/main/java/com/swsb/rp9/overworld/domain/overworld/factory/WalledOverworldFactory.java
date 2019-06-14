package com.swsb.rp9.overworld.domain.overworld.factory;

import com.swsb.rp9.overworld.domain.Coordinate;
import com.swsb.rp9.overworld.domain.overworld.Overworld;
import com.swsb.rp9.overworld.domain.overworld.TileType;

import java.util.HashMap;
import java.util.Map;

import static com.swsb.rp9.overworld.domain.Coordinate.coordinate;
import static com.swsb.rp9.overworld.domain.overworld.TileType.*;

public class WalledOverworldFactory implements OverworldFactory {

    @Override
    public Overworld createOverworld(int width, int height) {
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
        return new Overworld(tiles, coordinate(5, 5));
    }
}
