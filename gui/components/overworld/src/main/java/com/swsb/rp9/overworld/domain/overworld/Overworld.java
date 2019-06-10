package com.swsb.rp9.overworld.domain.overworld;

import com.swsb.rp9.overworld.domain.Coordinate;
import com.swsb.rp9.overworld.domain.Direction;
import com.swsb.rp9.overworld.domain.hero.Hero;
import javafx.scene.input.KeyEvent;

import java.util.Map;

import static javafx.scene.input.KeyCode.*;

public class Overworld {
    private final Map<Coordinate, TileType> tiles;
    private Hero hero;

    public Overworld(Map<Coordinate, TileType> tiles) {
        this.tiles = tiles;
    }

    public Map<Coordinate, TileType> getTiles() {
        return tiles;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void onKeyPressed(KeyEvent event) {
        if (event.getCode().equals(DOWN)) {
            moveHero(Direction.DOWN);
        }
        if (event.getCode().equals(RIGHT)) {
            moveHero(Direction.RIGHT);
        }
        if (event.getCode().equals(UP)) {
            moveHero(Direction.UP);
        }
        if (event.getCode().equals(LEFT)) {
            moveHero(Direction.LEFT);
        }
    }

    private void moveHero(Direction direction) {
        hero.move(direction);
    }
}
