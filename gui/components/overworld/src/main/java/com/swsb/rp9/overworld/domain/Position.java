package com.swsb.rp9.overworld.domain;

public class Position {

    private final double x;
    private final double y;

    private Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Position position(double x, double y){
        return new Position(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
