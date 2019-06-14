package com.swsb.rp9.domain.api;

import java.util.Objects;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate coordinate(int x, int y) {
        return new Coordinate(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate neighbourInDirection(Direction direction) {
        switch (direction) {
            case UP:
                return coordinate(x, y - 1);
            case DOWN:
                return coordinate(x, y + 1);
            case RIGHT:
                return coordinate(x + 1, y);
            case LEFT:
                return coordinate(x - 1, y);
            case STAND_STILL:
                return coordinate(x, y);
            default:
                throw new RuntimeException(String.format("Don't know neighbour in %s direction", direction));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
