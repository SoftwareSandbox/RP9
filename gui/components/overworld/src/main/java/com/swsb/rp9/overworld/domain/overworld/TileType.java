package com.swsb.rp9.overworld.domain.overworld;

import javafx.scene.paint.Paint;

import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.overworld.domain.ImageBuilder.image;

public enum TileType {

    DESERT("/com/swsb/rp9/overworld/sprites/tiles/desert_sand2_d.jpg"),
    GRASS("/com/swsb/rp9/overworld/sprites/tiles/grass_green_d.jpg"),
    CRACKS("/com/swsb/rp9/overworld/sprites/tiles/ground_cracks2y_d.jpg"),
    MUD("/com/swsb/rp9/overworld/sprites/tiles/ground_mud2_d.jpg"),
    JUNGLE("/com/swsb/rp9/overworld/sprites/tiles/jungle_mntn2_d.jpg"),
    MOUNTAIN("/com/swsb/rp9/overworld/sprites/tiles/mntn_brown_h.jpg"),
    MOSS("/com/swsb/rp9/overworld/sprites/tiles/moss_plants_d.jpg"),
    SNOW("/com/swsb/rp9/overworld/sprites/tiles/snow2ice_d.jpg"),
    WALL("/com/swsb/rp9/overworld/sprites/tiles/wall.jpg"),

    ICE("/com/swsb/rp9/overworld/sprites/tiles/ice-normal.png"),
    ICE_SIDE_TOP("/com/swsb/rp9/overworld/sprites/tiles/ice-side.png", false),
    ICE_SIDE_LEFT("/com/swsb/rp9/overworld/sprites/tiles/ice-side.png", 270, false),
    ICE_SIDE_RIGHT("/com/swsb/rp9/overworld/sprites/tiles/ice-side.png", 90, false),
    ICE_SIDE_BOTTOM("/com/swsb/rp9/overworld/sprites/tiles/ice-side.png", 180, false),
    ICE_CORNER_TOP_LEFT("/com/swsb/rp9/overworld/sprites/tiles/ice-corner.png", false),
    ICE_CORNER_TOP_RIGHT("/com/swsb/rp9/overworld/sprites/tiles/ice-corner.png", 90, false),
    ICE_CORNER_BOTTOM_LEFT("/com/swsb/rp9/overworld/sprites/tiles/ice-corner.png", 270, false),
    ICE_CORNER_BOTTOM_RIGHT("/com/swsb/rp9/overworld/sprites/tiles/ice-corner.png", 180, false),
    ICE_INNER_CORNER("/com/swsb/rp9/overworld/sprites/tiles/ice-inner-corner.png");

    private final String pathToTextureImg;
    private final double rotate;
    private final boolean canMoveThrough;

    TileType(String pathToTextureImg) {
        this.pathToTextureImg = pathToTextureImg;
        this.rotate = 0;
        this.canMoveThrough = true;
    }

    TileType(String pathToTextureImg, boolean canMoveThrough) {
        this.pathToTextureImg = pathToTextureImg;
        this.rotate = 0;
        this.canMoveThrough = canMoveThrough;
    }

    TileType(String pathToTextureImg, double rotate) {
        this.pathToTextureImg = pathToTextureImg;
        this.rotate = rotate;
        this.canMoveThrough = true;
    }


    TileType(String pathToTextureImg, double rotate, boolean canMoveThrough) {
        this.pathToTextureImg = pathToTextureImg;
        this.rotate = rotate;
        this.canMoveThrough = canMoveThrough;
    }

    public Paint toTexture(double size) {
        return image().url(getClass().getResource(pathToTextureImg).toExternalForm()).dimension(square(size)).rotate(rotate).buildPattern();
    }

    public boolean canMoveThrough() {
        return canMoveThrough;
    }
}
