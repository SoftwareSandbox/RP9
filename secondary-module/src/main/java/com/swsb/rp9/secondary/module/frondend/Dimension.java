package com.swsb.rp9.secondary.module.frondend;

public class Dimension {
    private final double width;
    private final double height;

    private Dimension(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public static Dimension rectangle(double width, double height) {
        return new Dimension(width, height);
    }

    public static Dimension square(double side) {
        return new Dimension(side, side);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
