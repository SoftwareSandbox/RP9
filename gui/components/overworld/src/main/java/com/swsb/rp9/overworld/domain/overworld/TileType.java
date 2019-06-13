package com.swsb.rp9.overworld.domain.overworld;

import javafx.scene.paint.Paint;

import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.overworld.domain.ImageBuilder.image;

public enum TileType {

    DESERT("com/swsb/rp9/overworld/sprites/tiles/desert_sand2_d.jpg"),
    GRASS("com/swsb/rp9/overworld/sprites/tiles/grass_green_d.jpg"),
    CRACKS("com/swsb/rp9/overworld/sprites/tiles/ground_cracks2y_d.jpg"),
    MUD("com/swsb/rp9/overworld/sprites/tiles/ground_mud2_d.jpg"),
    JUNGLE("com/swsb/rp9/overworld/sprites/tiles/jungle_mntn2_d.jpg"),
    MOUNTAIN("com/swsb/rp9/overworld/sprites/tiles/mntn_brown_h.jpg"),
    MOSS("com/swsb/rp9/overworld/sprites/tiles/moss_plants_d.jpg"),
    SNOW("com/swsb/rp9/overworld/sprites/tiles/snow2ice_d.jpg"),
    WALL("com/swsb/rp9/overworld/sprites/tiles/wall.jpg");

    private final String pathToTextureImg;

    TileType(String pathToTextureImg) {

        this.pathToTextureImg = pathToTextureImg;
    }

    public Paint toTexture(double size) {
        return image().url(pathToTextureImg).dimension(square(size)).buildPattern();
    }

    public boolean canMoveThrough() {
        return this != WALL;
    }
}
