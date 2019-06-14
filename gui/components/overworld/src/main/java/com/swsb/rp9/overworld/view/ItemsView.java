package com.swsb.rp9.overworld.view;

import com.swsb.rp9.domain.api.ItemType;
import com.swsb.rp9.domain.api.OverworldState;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;

import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.core.Position.position;
import static com.swsb.rp9.overworld.OverworldDefaultView.RECTANGLE_SIZE;
import static com.swsb.rp9.overworld.OverworldDefaultView.toPosition;
import static com.swsb.rp9.overworld.RectangleBuilder.rectangle;
import static java.util.stream.Collectors.toList;

public class ItemsView {

    private static final double ITEM_OFFSET = 0;
    private final Group view;
    private OverworldState overworldState;

    public ItemsView(OverworldState overworldState) {
        this.overworldState = overworldState;
        this.view = new Group();
    }

    public void redraw(){
        if(overworldState.hasItemsChanged()){
            view.getChildren().clear();
            view.getChildren().addAll(
                    overworldState.getItems().entrySet().stream()
                            .map(entry -> rectangle()
                                    .color(toTexture(entry.getValue(), RECTANGLE_SIZE))
                                    .dimension(square(RECTANGLE_SIZE))
                                    .position(toPosition(entry.getKey()).move(position(ITEM_OFFSET, ITEM_OFFSET)))
                                    .build())
                            .collect(toList()));
        }
    }

    public Node getView() {
        return view;
    }

    private Paint toTexture(ItemType itemType, int rectangleSize) {
        return com.swsb.rp9.overworld.ItemType.valueOf(itemType.name()).toTexture(rectangleSize);
    }
}
