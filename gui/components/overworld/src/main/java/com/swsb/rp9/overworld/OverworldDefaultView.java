package com.swsb.rp9.overworld;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.List;

public class OverworldDefaultView extends GameView {

    private static final Color BACKGROUND_COLOR = Color.YELLOW;
    private static final Dimension DIMENSIONS = Dimension.square(500);

    public OverworldDefaultView() {
        super(DIMENSIONS, BACKGROUND_COLOR);
    }

    @Override
    protected List<Node> createGuiElements() {
        return List.of(generateButton());
    }

    private Node generateButton() {
        return new Button("Click somewhere (not on the button) to go back.");
    }

}
