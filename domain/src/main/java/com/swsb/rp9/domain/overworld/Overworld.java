package com.swsb.rp9.domain.overworld;

import com.swsb.rp9.domain.Character;
import com.swsb.rp9.domain.api.Coordinate;
import com.swsb.rp9.domain.api.Direction;
import com.swsb.rp9.domain.api.ItemType;
import com.swsb.rp9.domain.api.TileType;

import java.util.Map;

public class Overworld {
    private final Map<Coordinate, TileType> tiles;
    private final ItemCollection items;


    private Character character;
    private boolean enemyCollision;

    public Overworld(Map<Coordinate, TileType> tiles, Map<Coordinate, ItemType> items, Coordinate heroStartingCoordinate, Character character) {
        this.tiles = tiles;
        this.items = new ItemCollection(items);
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
        Coordinate moveToCoordinate = character.getCoordinate().neighbourInDirection(direction);
        TileType destinationTile = tiles.get(moveToCoordinate);
        if (destinationTile.canMoveThrough()) {
            character.move(direction);
        }
        if (destinationTile.containsEnemy()) {
            this.enemyCollision = true;
        }
        ItemType itemType = items.get(moveToCoordinate);
        if(itemType != null){
            items.remove(moveToCoordinate);
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
