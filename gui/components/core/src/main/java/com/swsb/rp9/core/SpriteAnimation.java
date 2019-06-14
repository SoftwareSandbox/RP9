package com.swsb.rp9.core;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static javafx.util.Duration.millis;

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int count, int columns,
            int offsetX, int offsetY,
            int width, int height) {
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double tick) {
        final int index = Math.min((int) Math.floor(tick * count), count - 1);
        if (index != lastIndex) {
            moveViewPort(index);
            lastIndex = index;
        }
    }

    private void moveViewPort(int index) {
        imageView.setViewport(new Rectangle2D(calculateX(index), calculateY(index), width, height));
    }

    private int calculateY(int index) {
        return (index / columns) * height + offsetY;
    }

    private int calculateX(int index) {
        return (index % columns) * width + offsetX;
    }

    public static class Builder {
        private ImageView imageView;
        private Duration duration = millis(500);
        private int count = 3;
        private int columns = 3;
        private int offsetX = 0;
        private int offSetY = 0;
        private int width = 70;
        private int height = 70;
        private int startingRow;

        public static Builder spriteAnimation() {
            return new Builder();
        }

        public SpriteAnimation build() {
            if(startingRow != 0){
                offSetY = height * startingRow;
            }
            SpriteAnimation spriteAnimation = new SpriteAnimation(imageView, duration, count, columns, offsetX, offSetY, width, height);
            spriteAnimation.setCycleCount(INDEFINITE);
            return spriteAnimation;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder duration(Duration duration) {
            this.duration = duration;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder columns(int columns) {
            this.columns = columns;
            return this;
        }

        public Builder offsetX(int offsetX) {
            this.offsetX = offsetX;
            return this;
        }

        public Builder offSetY(int offSetY) {
            this.offSetY = offSetY;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder startingRow(int startingRow) {
            this.startingRow = startingRow;
            return this;
        }
    }
}
