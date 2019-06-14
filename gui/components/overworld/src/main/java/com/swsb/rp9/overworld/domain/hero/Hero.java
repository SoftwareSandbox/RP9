package com.swsb.rp9.overworld.domain.hero;

import com.swsb.rp9.overworld.domain.Coordinate;
import com.swsb.rp9.core.Direction;

public class Hero {
    private Coordinate coordinate;

    public Hero(Coordinate startingCoordinate) {
        this.coordinate = startingCoordinate;
    }

    public void move(Direction direction) {
        this.coordinate = coordinate.neighbourInDirection(direction);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
