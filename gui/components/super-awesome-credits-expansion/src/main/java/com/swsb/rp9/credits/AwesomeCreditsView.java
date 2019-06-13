package com.swsb.rp9.credits;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.credits.view.api.CreditsView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Random;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;

public class AwesomeCreditsView extends GameView implements CreditsView {

    private static final Dimension DIMENSIONS = rectangle(640, 480);

    private Label backLbl, creditsLbl;

    public AwesomeCreditsView() {
        super(DIMENSIONS);
    }

    @Override
    public String getTitle() {
        return "Awesome Credits";
    }

    @Override
    public String getStyleSheetLocation() {
        return getClass().getResource("/com/swsb/rp9/superawesomecredits/styles/credits.css").toExternalForm();
    }

    @Override
    public void redraw() {
        creditsLbl.setRotate(creditsLbl.getRotate()+1);
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
        return 9001;
    }

    private Background createBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/com/swsb/rp9/superawesomecredits/background/awesomeness-background.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }

    private Label createBackLabel() {
        var label = new Label("Press B to go back! Onwards, to less awesomeness :-(");
        label.setTextFill(Color.WHITE);
        return label;
    }

    private Label createCreditsLabel() {
        var label = new Label("CREDITS: Some Cegeka people! 1 of them made this awesome expansion though");
        label.setTextFill(Color.DARKSLATEBLUE);
        label.setTranslateY(30);
        label.setTranslateX(30);
        return label;
    }

    public static AwesomeCreditsView provider() {
        return new AwesomeCreditsView();
    }
}
