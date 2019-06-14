package com.swsb.rp9.overworld;

import javafx.scene.paint.Paint;

import java.net.URL;

import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.core.ImageBuilder.image;

public enum ItemType {
    CANDLE("/com/swsb/rp9/overworld/sprites/items/candle.png"),
    WISP("/com/swsb/rp9/overworld/sprites/enemy/wisp.png");

    private final String pathToTextureImage;

    ItemType(String pathToTextureImage) {
        this.pathToTextureImage = pathToTextureImage;
    }


    public Paint toTexture(double size) {
        URL resource = getClass().getResource(pathToTextureImage);
        if (resource == null) {
            throw new RuntimeException("Texture not found: " + pathToTextureImage);
        }
        return image().url(resource.toExternalForm()).dimension(square(size)).buildPattern();
    }
}
