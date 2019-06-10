package com.swsb.rp9;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static com.swsb.rp9.core.Dimension.rectangle;

public class CreditsDefaultView extends GameView {

    private static final Dimension DIMENSIONS = rectangle(640, 480);

    private Node backLbl, creditsLbl;

    public CreditsDefaultView() {
        super(DIMENSIONS);
    }

    @Override
    public String getTitle() {
        return "Credits";
    }

    @Override
    public String getStyleSheetLocation() {
        return "com/swsb/rp9/credits/styles/credits.css";
    }

    @Override
    public GameView redraw() {
        exampleOfMovingLabel();
        return this;
    }

    @Override
    protected Parent createGuiRootNode() {
        backLbl = createBackLabel();
        creditsLbl = createCreditsLabel();

        FlowPane pane = new FlowPane(
                backLbl,
                creditsLbl);
        pane.setBackground(createBackground());

        return pane;
    }

    private Background createBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("com/swsb/rp9/credits/background/credits-background.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(0, 0, false, false, false, true));
        return new Background(backgroundImage);
    }

    private Label createBackLabel() {
        var label = new Label("Press B to go back!");
        label.setTextFill(Color.WHITE);
        return label;
    }

    private Label createCreditsLabel() {
        var label = new Label("CREDITS: Some Cegeka people! 6 in total");
        label.setTextFill(Color.GHOSTWHITE);
        label.setTranslateY(50);
        return label;
    }

    private void exampleOfMovingLabel() {
        creditsLbl.setTranslateY((DIMENSIONS.getHeight() / 2 - 25 + 5 * Math.random()));
        creditsLbl.setTranslateX((DIMENSIONS.getWidth() / 2 - 100 + 5 * Math.random()));
    }

}
