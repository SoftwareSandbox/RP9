package com.swsb.rp9.overworld.domain.overworld;

import com.swsb.rp9.overworld.domain.Coordinate;
import com.swsb.rp9.overworld.domain.Direction;
import com.swsb.rp9.overworld.domain.hero.Hero;

import java.util.Map;

public class Overworld {
    private final Map<Coordinate, TileType> tiles;
    private Hero hero;

    public Overworld(Map<Coordinate, TileType> tiles, Coordinate heroStartingCoordinate) {
        this.tiles = tiles;
        this.hero = new Hero(heroStartingCoordinate);
    }

    public Map<Coordinate, TileType> getTiles() {
        return tiles;
    }

    public void handleDirectionPressed(Direction directionPressed) {
        moveHero(directionPressed);
    }

    private void moveHero(Direction direction) {
        hero.move(direction);
    }

    public Hero getHero() {
        return hero;
    }
}
