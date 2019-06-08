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

    private Node backLbl, creditsLbl;

    public CreditsDefaultView() {super(DIMENSIONS, BACKGROUND_COLOR); }

    @Override
    protected List<Node> createGuiElements() {
        backLbl =  createBackLabel();
        creditsLbl = createCreditsLabel();

        return List.of(
                backLbl,
                creditsLbl);
    }

    @Override
    public GameView redraw() {
        exampleOfMovingLabel();
        return this;
    }

    private Node createBackLabel() {
        var label = new Label("Press B to go back!");
        label.setTextFill(Color.WHITE);
        return label;
    }

    private Node createCreditsLabel() {
        var label = new Label("CREDITS: Some Cegeka people! 6 in total");
        label.setTextFill(Color.YELLOW);
        label.setTranslateY(50);
        return label;
    }

    private void exampleOfMovingLabel() {
        creditsLbl.setTranslateY((DIMENSIONS.getHeight() * Math.random()));
        creditsLbl.setTranslateX((DIMENSIONS.getWidth() * Math.random()));
    }

}
