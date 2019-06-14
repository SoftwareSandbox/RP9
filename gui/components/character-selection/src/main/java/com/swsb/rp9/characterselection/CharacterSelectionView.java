package com.swsb.rp9.characterselection;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.core.TransitionSlot;
import com.swsb.rp9.domain.api.CharacterSelectionState;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

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
        characterScreen.setBackground(createStartScreenBackground());
        characterScreen.setAlignment(Pos.CENTER);
        return characterScreen;
    }

    private TextField createCharacterNameInputField() {
        characterNameTextField = new TextField("Your name...");
        characterNameTextField.setPrefWidth(DIMENSIONS.getWidth() - 20);
        characterNameTextField.setAlignment(Pos.CENTER);
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
        startGameButton.setOnMouseClicked(
                event -> {
                    getRestrictedState().setCharacterName(characterNameTextField.getText());
                    registerTransitionSlot(TransitionSlot.TRANSITION_SLOT_ONE);
                }
        );
        return startGameButton;
    }

    private Background createStartScreenBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/com/swsb/rp9/characterselection/background/glacial_mountains_preview_lightened.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(0, 0, false, false, false, true));
        return new Background(backgroundImage);
    }
}
