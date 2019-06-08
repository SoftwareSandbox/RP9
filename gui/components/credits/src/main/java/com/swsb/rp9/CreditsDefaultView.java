package com.swsb.rp9;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;

public class CreditsDefaultView extends GameView {

    private static final Dimension DIMENSIONS = Dimension.square(750);
    private static final Color BACKGROUND_COLOR = Color.BLACK;

    public CreditsDefaultView() {
        super(DIMENSIONS, BACKGROUND_COLOR);
    }

    @Override
    protected List<Node> createGuiElements() {
        return List.of(
                generateLabel(),
                generateCredits());
    }

    private Node generateLabel() {
        Label label = new Label("Press B to go back!");
        label.setTextFill(Color.WHITE);
        return label;
    }

    private Node generateCredits() {
        Label label = new Label("CREDITS: Some Cegeka people! 6 in total");
        label.setTextFill(Color.YELLOW);
        label.setTranslateY(50);
        return label;
    }

}
