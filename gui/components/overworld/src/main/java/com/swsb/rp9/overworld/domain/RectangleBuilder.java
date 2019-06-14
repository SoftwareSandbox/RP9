package com.swsb.rp9.overworld.domain;

import com.swsb.rp9.core.Dimension;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class RectangleBuilder {
    private Dimension dimension;
    private Position position;
    private Paint paint;
    private Paint strokeColor;

    public static RectangleBuilder rectangle() {
        return new RectangleBuilder();
    }

    public Rectangle build(){
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(paint);
        if(position != null){
            rectangle.setX(position.getX());
            rectangle.setY(position.getY());
        }
        if(strokeColor != null){
            rectangle.setStroke(strokeColor);
            rectangle.setStrokeWidth(3);
        }
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

    public RectangleBuilder strokeColor(Paint strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }
}
