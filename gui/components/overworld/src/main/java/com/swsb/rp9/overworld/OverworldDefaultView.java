package com.swsb.rp9.overworld;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;

public class OverworldDefaultView extends GameView {

    private static final Dimension DIMENSIONS = Dimension.square(500);

    public OverworldDefaultView() {
        super(DIMENSIONS);
    }

    @Override
    public String getTitle() {
        return "Overworld";
    }

    @Override
    public String getStyleSheetLocation() {
        return null;
    }

    @Override
    public GameView redraw() {
        return this;
    }

    @Override
    protected Parent createGuiRootNode() {
        return backButton();
    }

    private Button backButton() {
        Button btn = new Button("GO BACK");
        btn.setOnMouseClicked(event -> registerTransitionSlot(TRANSITION_SLOT_ONE));
        return btn;
    }

}
