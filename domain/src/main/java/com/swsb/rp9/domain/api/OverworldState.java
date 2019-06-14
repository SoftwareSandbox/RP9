package com.swsb.rp9.domain.api;

import com.swsb.rp9.shared.Coordinate;
import com.swsb.rp9.shared.Direction;
import com.swsb.rp9.shared.ItemType;
import com.swsb.rp9.shared.TileType;

import java.util.Map;

public class OverworldState extends RestrictedState {

    public String getCharacterName() {
        return getGameState().getCharacterName();
    }

    public int getHitPoints() {
        return getGameState().getHitPoints();
    }

    public void handleDirectionPressed(Direction direction) {
        getGameState().handleDirectionPressed(direction);
    }

    public boolean collidedWithEnemy() {
        return getGameState().isCollidedWithEnemy();
    }

    public void collisionHandled() {
        getGameState().collisionHandled();
    }

    public Map<Coordinate, TileType> getTiles() {
        return getGameState().getTiles();
    }

    public Coordinate getHeroCoordinate() {
        return getGameState().getCharacterCoordinate();
    }

    public Map<Coordinate, ItemType> getItems() {
        return getGameState().getItems();
    }

    public boolean hasItemsChanged() {
        return getGameState().hasItemsChanged();
    }
}
