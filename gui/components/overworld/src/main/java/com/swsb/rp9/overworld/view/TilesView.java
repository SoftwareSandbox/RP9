package com.swsb.rp9.overworld.view;

import com.swsb.rp9.domain.api.OverworldState;
import com.swsb.rp9.domain.api.TileType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;

import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.overworld.OverworldDefaultView.RECTANGLE_SIZE;
import static com.swsb.rp9.overworld.OverworldDefaultView.toPosition;
import static com.swsb.rp9.overworld.RectangleBuilder.rectangle;
import static java.util.stream.Collectors.toList;

public class TilesView {

    private final Node view;
    private OverworldState overworldState;

    public TilesView(OverworldState overworldState) {
        this.overworldState = overworldState;
        this.view = new Group(overworldState.getTiles().entrySet().stream()
                .map(entry -> rectangle()
                        .color(toTexture(entry.getValue(), RECTANGLE_SIZE))
                        .dimension(square(RECTANGLE_SIZE))
                        .position(toPosition(entry.getKey()))
                        .build())
                .collect(toList()));
    }

    public void redraw(){

    }

    public Node getView() {
        return view;
    }


    private Paint toTexture(TileType tileType, int rectangleSize) {
        return com.swsb.rp9.overworld.TileType.valueOf(tileType.name()).toTexture(rectangleSize);
    }
}
