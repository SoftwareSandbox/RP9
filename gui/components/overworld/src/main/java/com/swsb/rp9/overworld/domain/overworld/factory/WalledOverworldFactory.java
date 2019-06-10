package com.swsb.rp9.overworld.domain.overworld.factory;

import com.swsb.rp9.overworld.domain.Coordinate;
import com.swsb.rp9.overworld.domain.overworld.Overworld;
import com.swsb.rp9.overworld.domain.overworld.TileType;

import java.util.HashMap;
import java.util.Map;

import static com.swsb.rp9.overworld.domain.Coordinate.coordinate;
import static com.swsb.rp9.overworld.domain.overworld.TileType.WALL;

public class WalledOverworldFactory implements OverworldFactory {

    @Override
    public Overworld createOverworld(int width, int height) {
        Map<Coordinate, TileType> tiles = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y ++){
                if(x == 0 || x == width - 1 || y == 0 || y == height -1){
                    tiles.put(coordinate(x, y), WALL);
                } else {
                    tiles.put(coordinate(x, y), TileType.DESERT);
                }
            }
        }
        return new Overworld(tiles);
    }
}
