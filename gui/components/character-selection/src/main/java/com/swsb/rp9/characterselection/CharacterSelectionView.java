package com.swsb.rp9.characterselection;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.core.TransitionSlot;
import com.swsb.rp9.domain.api.CharacterSelectionState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import static com.swsb.rp9.core.Dimension.rectangle;

public class CharacterSelectionView extends GameView<CharacterSelectionState> {

    private static final Dimension DIMENSIONS = rectangle(640, 480);

    private TextField characterNameTextField;

    public CharacterSelectionView() {
        super(DIMENSIONS, new CharacterSelectionState());
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
        TilePane characterScreen = new TilePane(
                createCharacterNameInputField(),
                createStartGameButton()
        );
        characterScreen.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        characterScreen.setAlignment(Pos.CENTER);
        return characterScreen;
    }

    private TextField createCharacterNameInputField() {
        characterNameTextField = new TextField("Your name...");
        return characterNameTextField;
    }

    private Button createStartGameButton() {
        Button startGameButton = new Button("Start game");
        startGameButton.setOnKeyPressed(
                event -> {
                    if (KeyCode.ENTER.equals(event.getCode())) {
                        getRestrictedState().setCharacterName(characterNameTextField.getText());
                        registerTransitionSlot(TransitionSlot.TRANSITION_SLOT_ONE);
                    }
                }
        );
        return startGameButton;
    }
}
