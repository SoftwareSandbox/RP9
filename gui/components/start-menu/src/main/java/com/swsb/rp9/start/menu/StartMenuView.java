package com.swsb.rp9.start.menu;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;

public class StartMenuView extends GameView {

    private static final Color BACKGROUND_COLOR = Color.DARKBLUE;
    private static final Dimension DIMENSIONS = Dimension.square(750);

    public StartMenuView() {
        super(DIMENSIONS, BACKGROUND_COLOR);
    }

    @Override
    protected List<Node> createGuiElements() {
        return List.of(generateLabel());
    }

    private Node generateLabel() {
        Label label = new Label("Press N to go to Overworld, Click the mouse to go to the credits");
        label.setTextFill(Color.WHITE);
        return label;
    }

}
