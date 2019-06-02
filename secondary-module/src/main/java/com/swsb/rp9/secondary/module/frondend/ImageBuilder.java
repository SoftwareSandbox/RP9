package com.swsb.rp9.secondary.module.frondend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;

public class ImageBuilder {

    private Dimension dimension;
    private String url;
    private boolean preserveRatio = true;
    private boolean smooth = true;
    private Position startingPosition;

    public static ImageBuilder image(){
        return new ImageBuilder();
    }

    public ImagePattern buildPattern(){
        return new ImagePattern(build());
    }

    public ImageView buildView() {
        ImageView imageView = new ImageView(build());
        imageView.setX(startingPosition.getX());
        imageView.setY(startingPosition.getY());
        return imageView;
    }

    private Image build() {
        return new Image(url, dimension.getWidth(), dimension.getHeight(), preserveRatio, smooth);
    }

    public ImageBuilder dimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public ImageBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ImageBuilder preserveRatio(boolean preserveRatio) {
        this.preserveRatio = preserveRatio;
        return this;
    }

    public ImageBuilder smooth(boolean smooth) {
        this.smooth = smooth;
        return this;
    }

    public ImageBuilder startingPosition(Position startingPosition) {
        this.startingPosition = startingPosition;
        return this;
    }
}
