package com.swsb.rp9.domain.overworld;

import com.swsb.rp9.domain.Character;
import com.swsb.rp9.shared.*;

import java.util.Map;

import static com.swsb.rp9.shared.ItemType.CANDLE;
import static com.swsb.rp9.shared.ItemType.WISP;

public class Overworld {
    private final Map<Coordinate, TileType> tiles;
    private final ItemCollection items;


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
        Coordinate moveToCoordinate = character.getCoordinate().neighbourInDirection(direction);
        TileType destinationTile = tiles.get(moveToCoordinate);
        if (destinationTile.canMoveThrough()) {
            character.move(direction);
        }

        ItemType itemType = items.get(moveToCoordinate);
        if (itemType == CANDLE) {
            character.addCandlePoint();
            items.remove(moveToCoordinate);
        }

        if (itemType == WISP) {
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
        return items.getItems();
    }

    public boolean hasItemsChanged() {
        return items.isChanged();
    }
}
