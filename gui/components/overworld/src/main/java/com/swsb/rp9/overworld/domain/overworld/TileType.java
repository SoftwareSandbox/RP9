package com.swsb.rp9.overworld.domain.overworld;

import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.core.ImageBuilder.image;
import static java.util.Arrays.asList;

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
    ICE_SIDE_TOP("/com/swsb/rp9/overworld/sprites/tiles/ice-side.png", TileCharacteristics.IMPASSABLE),
    ICE_SIDE_LEFT("/com/swsb/rp9/overworld/sprites/tiles/ice-side.png", 270, TileCharacteristics.IMPASSABLE),
    ICE_SIDE_RIGHT("/com/swsb/rp9/overworld/sprites/tiles/ice-side.png", 90, TileCharacteristics.IMPASSABLE),
    ICE_SIDE_BOTTOM("/com/swsb/rp9/overworld/sprites/tiles/ice-side.png", 180, TileCharacteristics.IMPASSABLE),
    ICE_CORNER_TOP_LEFT("/com/swsb/rp9/overworld/sprites/tiles/ice-corner.png", TileCharacteristics.IMPASSABLE),
    ICE_CORNER_TOP_RIGHT("/com/swsb/rp9/overworld/sprites/tiles/ice-corner.png", 90, TileCharacteristics.IMPASSABLE),
    ICE_CORNER_BOTTOM_LEFT("/com/swsb/rp9/overworld/sprites/tiles/ice-corner.png", 270, TileCharacteristics.IMPASSABLE),
    ICE_CORNER_BOTTOM_RIGHT("/com/swsb/rp9/overworld/sprites/tiles/ice-corner.png", 180, TileCharacteristics.IMPASSABLE),
    ICE_INNER_CORNER("/com/swsb/rp9/overworld/sprites/tiles/ice-inner-corner.png"),

    WISP_ON_ICE_BORDER("/com/swsb/rp9/overworld/sprites/tiles/wisp_on_ice_border.png", TileCharacteristics.IMPASSABLE, TileCharacteristics.ENEMY);

    private final String pathToTextureImg;
    private final double rotate;
    private final List<TileCharacteristics> characteristics = new ArrayList<>();

    TileType(String pathToTextureImg) {
        this.pathToTextureImg = pathToTextureImg;
        this.rotate = 0;
    }

    TileType(String pathToTextureImg, TileCharacteristics... characteristics) {
        this.pathToTextureImg = pathToTextureImg;
        this.rotate = 0;
        this.characteristics.addAll(asList(characteristics));
    }

    TileType(String pathToTextureImg, double rotate) {
        this.pathToTextureImg = pathToTextureImg;
        this.rotate = rotate;
    }

    TileType(String pathToTextureImg, double rotate, TileCharacteristics... characteristics) {
        this.pathToTextureImg = pathToTextureImg;
        this.rotate = rotate;
        this.characteristics.addAll(asList(characteristics));
    }

    public Paint toTexture(double size) {
        URL resource = getClass().getResource(pathToTextureImg);
        if (resource == null) {
            throw new RuntimeException("Texture not found: " + pathToTextureImg);
        }
        return image().url(resource.toExternalForm()).dimension(square(size)).rotate(rotate).buildPattern();
    }

    public boolean canMoveThrough() {
        return !characteristics.contains(TileCharacteristics.IMPASSABLE);
    }

    public boolean containsEnemy() {
        return characteristics.contains(TileCharacteristics.ENEMY);
    }

    private enum TileCharacteristics {
        IMPASSABLE,
        ENEMY;
    }
}
