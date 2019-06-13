package com.swsb.rp9.characterselection;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.core.TransitionSlot;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import static com.swsb.rp9.core.Dimension.rectangle;

public class CharacterSelectionView extends GameView {

    private static final Dimension DIMENSIONS = rectangle(640, 480);

    public CharacterSelectionView() {
        super(DIMENSIONS);
    }

    @Override
    public void redraw() {

    }

    @Override
    public String getTitle() {
        return "Select your character";
    }

    @Override
    public String getStyleSheetLocation() {
        return getClass().getResource("/com/swsb/rp9/characterselection/styles/character-selection.css").toExternalForm();
    }

    @Override
    protected Parent createGuiRootNode() {
        return createCharacterSelectionScreen();
    }

    private Parent createCharacterSelectionScreen() {
        TilePane characterScreen = new TilePane(createStartGameButton());
        characterScreen.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        characterScreen.setAlignment(Pos.CENTER);
        return characterScreen;
    }

    private Button createStartGameButton() {
        Button startGameButton = new Button("Start game");
        startGameButton.setOnKeyPressed(
                event -> {
                    if (KeyCode.ENTER.equals(event.getCode())) {
                        registerTransitionSlot(TransitionSlot.TRANSITION_SLOT_ONE);
                    }
                }
        );
        return startGameButton;
    }
}
