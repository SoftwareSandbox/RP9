package com.swsb.rp9.domain.overworld;

import com.swsb.rp9.domain.Character;
import com.swsb.rp9.shared.*;

import java.util.Map;

public class Overworld {
    private final Map<Coordinate, TileType> tiles;
    private final Map<Coordinate, ItemType> items;

    private Character character;
    private boolean enemyCollision;

    public Overworld(OverworldFactoryResult overworldFactoryResult, Character character) {
        this.tiles = overworldFactoryResult.getTiles();
        this.items = overworldFactoryResult.getItems();
        this.character = character;
        this.character.setCoordinate(overworldFactoryResult.getCharacterStartingCoordinate());
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

    public Map<Coordinate, ItemType> getItems() {
        return items;
    }
}
