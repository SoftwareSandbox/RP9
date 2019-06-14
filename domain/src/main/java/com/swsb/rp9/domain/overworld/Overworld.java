package com.swsb.rp9.domain.overworld;

import com.swsb.rp9.domain.Character;
import com.swsb.rp9.domain.api.Coordinate;
import com.swsb.rp9.domain.api.Direction;
import com.swsb.rp9.domain.api.TileType;

import java.util.Map;

public class Overworld {
    private final Map<Coordinate, TileType> tiles;
    private Character character;
    private boolean enemyCollision;

    public Overworld(Map<Coordinate, TileType> tiles, Coordinate heroStartingCoordinate, Character character) {
        this.tiles = tiles;
        this.character = character;
        this.character.setCoordinate(heroStartingCoordinate);
    }

    public Map<Coordinate, TileType> getTiles() {
        return tiles;
    }

    public void handleDirectionPressed(Direction directionPressed) {
        moveCharacter(directionPressed);
    }

    private void moveCharacter(Direction direction) {
        TileType destinationTile = tiles.get(character.getCoordinate().neighbourInDirection(direction));
        if (destinationTile.canMoveThrough()) {
            character.move(direction);
        }
        if (destinationTile.containsEnemy()) {
            this.enemyCollision = true;
        }
    }

    public Character getCharacter() {
        return character;
    }

    public boolean isCollidedWithEnemy() {
        return enemyCollision;
    }

    public void collisionHandled() {
        this.enemyCollision = false;
    }
}
