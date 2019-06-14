package com.swsb.rp9.overworld.domain.overworld;

import com.swsb.rp9.overworld.domain.Coordinate;
import com.swsb.rp9.core.Direction;
import com.swsb.rp9.overworld.domain.hero.Hero;

import java.util.Map;

public class Overworld {
    private final Map<Coordinate, TileType> tiles;
    private Hero hero;
    private boolean enemyCollision;

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
        TileType destinationTile = tiles.get(hero.getCoordinate().neighbourInDirection(direction));
        if (destinationTile.canMoveThrough()) {
            hero.move(direction);
        }
        if (destinationTile.containsEnemy()) {
            this.enemyCollision = true;
        }
    }

    public Hero getHero() {
        return hero;
    }

    public boolean collidedWithEnemy() {
        return enemyCollision;
    }

    public void collisionHandled() {
        this.enemyCollision = false;
    }
}
