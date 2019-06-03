package com.swsb.rp9.secondary.module.api;

import com.swsb.rp9.secondary.module.frondend.Hero;
import javafx.event.EventHandler;
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

    public EventHandler<? super KeyEvent> onKeyPressed() {
        return event -> {
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
        };
    }

    private void moveHero(Direction direction) {
        hero.move(direction);
    }
}
