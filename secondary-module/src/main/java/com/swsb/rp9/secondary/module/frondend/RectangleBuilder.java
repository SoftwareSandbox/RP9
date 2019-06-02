package com.swsb.rp9.secondary.module.frondend;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class RectangleBuilder {
    private Dimension dimension;
    private Position position;
    private Paint paint;

    public static RectangleBuilder rectangle() {
        return new RectangleBuilder();
    }

    public Rectangle build(){
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(paint);
        rectangle.setX(position.getX());
        rectangle.setY(position.getY());
        rectangle.setHeight(dimension.getHeight());
        rectangle.setWidth(dimension.getWidth());
        return rectangle;
    }

    public RectangleBuilder dimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public RectangleBuilder position(Position position) {
        this.position = position;
        return this;
    }

    public RectangleBuilder color(Paint paint) {
        this.paint = paint;
        return this;
    }
}
