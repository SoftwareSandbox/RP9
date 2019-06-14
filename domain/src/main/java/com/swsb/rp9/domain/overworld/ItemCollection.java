package com.swsb.rp9.domain.overworld;

import com.swsb.rp9.domain.api.Coordinate;
import com.swsb.rp9.domain.api.ItemType;

import java.util.Map;

public class ItemCollection {

    private final Map<Coordinate, ItemType> items;
    private boolean changed;

    public ItemCollection(Map<Coordinate, ItemType> items) {
        this.items = items;
        this.changed = true;
    }

    public void remove(Coordinate coordinate) {
        items.remove(coordinate);
        changed = true;
    }

    public ItemType get(Coordinate coordinate) {
        changed = false;
        return items.get(coordinate);
    }

    public boolean isChanged() {
        return changed;
    }

    public Map<Coordinate, ItemType> getItems() {
        return items;
    }
}
