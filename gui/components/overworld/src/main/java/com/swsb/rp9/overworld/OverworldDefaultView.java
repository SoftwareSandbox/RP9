package com.swsb.rp9.overworld;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.List;

import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;

public class OverworldDefaultView extends GameView {

    private static final Color BACKGROUND_COLOR = Color.YELLOW;
    private static final Dimension DIMENSIONS = Dimension.square(500);

    public OverworldDefaultView() {
        super(DIMENSIONS, BACKGROUND_COLOR);
    }

    @Override
    public String getTitle() {
        return "Overworld";
    }

    @Override
    public GameView redraw() {
        return this;
    }

    @Override
    protected List<Node> createGuiElements() {
        return List.of(backButton());
    }

    private Node backButton() {
        Button btn = new Button("GO BACK");
        btn.setOnMouseClicked(event -> registerTransitionSlot(TRANSITION_SLOT_ONE));
        return btn;
    }

}
