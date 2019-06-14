package com.swsb.rp9;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.credits.api.CreditsView;
import com.swsb.rp9.domain.api.PlaceholderState;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;

public class CreditsDefaultView extends GameView<PlaceholderState> implements CreditsView {

    private static final Dimension DIMENSIONS = rectangle(800, 640);

    private Node backLbl, creditsLbl;

    public CreditsDefaultView() {
        super(DIMENSIONS, new PlaceholderState());
    }

    @Override
    public String getTitle() {
        return "Credits";
    }

    @Override
    public String getStyleSheetLocation() {
        return getClass().getResource("/com/swsb/rp9/credits/styles/credits.css").toExternalForm();
    }

    @Override
    public void redraw() {
        exampleOfMovingLabel();
    }

    @Override
    protected void setOnKeyPressedForScene(KeyEvent event) {
        if (event.getCode().name().equals("B")) {
            registerTransitionSlot(TRANSITION_SLOT_ONE);
        }
    }

    @Override
    public Parent createGuiRootNode() {
        backLbl = createBackLabel();
        creditsLbl = createCreditsLabel();

        FlowPane pane = new FlowPane(
                backLbl,
                creditsLbl);
        pane.setBackground(createBackground());

        return pane;
    }

    @Override
    public int awesomeness() {
        return 9;
    }

    private Background createBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/com/swsb/rp9/credits/background/credits-background.png").toExternalForm()),
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
