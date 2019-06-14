package com.swsb.rp9.core;

import java.util.Objects;

public class Position {

    private final double x;
    private final double y;

    private Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Position position(double x, double y) {
        return new Position(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Position move(Position position) {
        return Position.position(x + position.getX(), y + position.getY());
    }

    public Position difference(Position position) {
        return Position.position(position.getX() - x, position.getY() - y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Double.compare(position.x, x) == 0 &&
                Double.compare(position.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Position limit(double max) {
        return Position.position(limit(x, max), limit(y, max));
    }

    private double limit(double number, double max) {
        if (number > max) {
            return max;
        }
        if (number < max * -1) {
            return max * -1;
        }
        return number;
    }

    public Direction getDirection() {
        if (x < 0) {
            return Direction.LEFT;
        }
        if (x > 0) {
            return Direction.RIGHT;
        }
        if (y < 0) {
            return Direction.UP;
        }
        if (y > 0) {
            return Direction.DOWN;
        }
        return Direction.STAND_STILL;
    }
}
