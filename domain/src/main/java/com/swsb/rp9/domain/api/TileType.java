package com.swsb.rp9.domain.api;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public enum TileType {

    DESERT(),
    GRASS(),
    CRACKS(),
    MUD(),
    JUNGLE(),
    MOUNTAIN(),
    MOSS(),
    SNOW(),
    WALL(),

    ICE(),
    ICE_SIDE_TOP(TileCharacteristics.IMPASSABLE),
    ICE_SIDE_LEFT(TileCharacteristics.IMPASSABLE),
    ICE_SIDE_RIGHT(TileCharacteristics.IMPASSABLE),
    ICE_SIDE_BOTTOM(TileCharacteristics.IMPASSABLE),
    ICE_CORNER_TOP_LEFT(TileCharacteristics.IMPASSABLE),
    ICE_CORNER_TOP_RIGHT(TileCharacteristics.IMPASSABLE),
    ICE_CORNER_BOTTOM_LEFT(TileCharacteristics.IMPASSABLE),
    ICE_CORNER_BOTTOM_RIGHT(TileCharacteristics.IMPASSABLE),
    ICE_INNER_CORNER(),

    WISP_ON_ICE_BORDER(TileCharacteristics.IMPASSABLE, TileCharacteristics.ENEMY);

    private final List<TileCharacteristics> characteristics = new ArrayList<>();

    TileType() {
    }

    TileType(TileCharacteristics... characteristics) {
        this.characteristics.addAll(asList(characteristics));
    }

    public boolean canMoveThrough() {
        return !characteristics.contains(TileCharacteristics.IMPASSABLE);
    }

    public boolean containsEnemy() {
        return characteristics.contains(TileCharacteristics.ENEMY);
    }

    private enum TileCharacteristics {
        IMPASSABLE,
        ENEMY
    }
}
