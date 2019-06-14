package com.swsb.rp9.characterselection;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.core.TransitionSlot;
import com.swsb.rp9.domain.api.CharacterSelectionState;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_TWO;
import static javafx.scene.paint.Color.BLACK;

public class CharacterSelectionView extends GameView<CharacterSelectionState> {

    private static final Dimension DIMENSIONS = rectangle(800, 640);

    private TextField characterNameTextField;
    private String selectedSprite;

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
        GridPane characterScreen = createGridPane();

        characterScreen.setGridLinesVisible(true);

        addSpriteSelectionToGridPane(characterScreen);
        addCharacterNameAndStartButton(characterScreen);
        characterScreen.setBackground(createStartScreenBackground());
        return characterScreen;
    }

    private GridPane createGridPane() {
        GridPane characterScreen = new GridPane();
        characterScreen.setVgap(10);
        characterScreen.setAlignment(Pos.CENTER);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(30);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(30);
        characterScreen.getColumnConstraints().addAll(col1,col2,col3);
        return characterScreen;
    }

    private void addSpriteSelectionToGridPane(GridPane characterScreen) {
        ToggleGroup menuToggleGroup = new ToggleGroup();

        RadioButton character1 = new RadioButton("Character 1");
        RadioButton character2 = new RadioButton("Character 2");
        RadioButton character3 = new RadioButton("Character 3");
        ImageView oratio = new ImageView(new Image(getClass().getResource("/com/swsb/rp9/characterselection/characters/Oratio-the-Mercenary.png").toExternalForm()));
        oratio.setViewport(new Rectangle2D(0,0, 70, 70));
        ImageView ouzo = new ImageView(new Image(getClass().getResource("/com/swsb/rp9/characterselection/characters/Ouzo-the-Wolf-Bard.png").toExternalForm()));
        ouzo.setViewport(new Rectangle2D(0,0, 70, 70));
        ImageView prime = new ImageView(new Image(getClass().getResource("/com/swsb/rp9/characterselection/characters/Prime-the-Great-Sage.png").toExternalForm()));
        prime.setViewport(new Rectangle2D(0,0, 70, 70));
        character1.setGraphic(oratio);
        character2.setGraphic(ouzo);
        character3.setGraphic(prime);
        character1.setToggleGroup(menuToggleGroup);
        character2.setToggleGroup(menuToggleGroup);
        character3.setToggleGroup(menuToggleGroup);
        menuToggleGroup.selectToggle(character2);

        GridPane.setValignment(character1, VPos.CENTER);
        GridPane.setHalignment(character1, HPos.CENTER);
        GridPane.setValignment(character2, VPos.CENTER);
        GridPane.setHalignment(character2, HPos.CENTER);
        GridPane.setValignment(character3, VPos.CENTER);
        GridPane.setHalignment(character3, HPos.CENTER);
        characterScreen.addRow(0, character1, character2, character3);
    }

    private void addCharacterNameAndStartButton(GridPane characterScreen) {
        TextField characterNameInputField = createCharacterNameInputField();
        Button startGameButton = createStartGameButton();
        GridPane.setValignment(characterNameInputField, VPos.CENTER);
        GridPane.setHalignment(characterNameInputField, HPos.CENTER);
        GridPane.setValignment(startGameButton, VPos.CENTER);
        GridPane.setHalignment(startGameButton, HPos.CENTER);
        characterScreen.add(characterNameInputField, 0,1,3,1);
        characterScreen.add(startGameButton, 1,2,1,1);
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
