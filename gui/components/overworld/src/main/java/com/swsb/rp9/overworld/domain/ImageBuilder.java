package com.swsb.rp9.overworld.domain;

import com.swsb.rp9.core.Dimension;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;

public class ImageBuilder {

    private Dimension dimension;
    private String url;
    private boolean preserveRatio = true;
    private boolean smooth = true;
    private Position startingPosition;
    private double rotate;

    public static ImageBuilder image() {
        return new ImageBuilder();
    }

    public ImagePattern buildPattern() {
        if (rotate != 0) {
            return new ImagePattern(buildView().snapshot(new SnapshotParameters(), null));
        }
        return new ImagePattern(build());
    }

    public ImageView buildView() {
        ImageView imageView = new ImageView(build());
        if(startingPosition != null){
            imageView.setX(startingPosition.getX());
            imageView.setY(startingPosition.getY());
        }
        if (rotate != 0) {
            imageView.setRotate(rotate);
        }
        return imageView;
    }

    private Image build() {
        if(dimension != null){
            return new Image(url, dimension.getWidth(), dimension.getHeight(), preserveRatio, smooth);
        }
        return new Image(url);
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

    public ImageBuilder rotate(double rotate) {
        this.rotate = rotate;
        return this;
    }
}
