package com.swsb.rp9.characterselection;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.core.SpriteAnimation;
import com.swsb.rp9.core.TransitionSlot;
import com.swsb.rp9.domain.api.CharacterSelectionState;
import com.swsb.rp9.domain.api.CharacterType;
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

import java.util.HashMap;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.SpriteAnimation.Builder.spriteAnimation;
import static com.swsb.rp9.domain.api.CharacterType.*;

public class CharacterSelectionView extends GameView<CharacterSelectionState> {

    private static final Dimension DIMENSIONS = rectangle(800, 640);
    public static final String DEFAULT_CHAR_NAME = "Your name...";
    public static final String ORATIO = "Oratio";
    public static final String OUZO = "Ouzo";
    public static final String PRIME = "Prime";

    private TextField characterNameTextField;
    private String selectedSprite;
    private ToggleGroup menuToggleGroup;
    private RadioButton character1;
    private RadioButton character2;
    private RadioButton character3;
    private TextField characterNameInputField;

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

        addSpriteSelectionToGridPane(characterScreen);
        addCharacterNameAndStartButton(characterScreen);
        characterScreen.setBackground(createStartScreenBackground());
        return characterScreen;
    }

    private GridPane createGridPane() {
        GridPane characterScreen = new GridPane();
        characterScreen.setVgap(20);
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
        menuToggleGroup = new ToggleGroup();
        HashMap<Toggle, SpriteAnimation> animationsMap= new HashMap<>();

        String char1Sprite = "/com/swsb/rp9/characterselection/characters/Oratio-the-Mercenary.png";
        character1 = createCharacterSelectionRadioButton(menuToggleGroup, animationsMap, ORATIO, char1Sprite);
        character2 = createCharacterSelectionRadioButton(menuToggleGroup, animationsMap, OUZO, "/com/swsb/rp9/characterselection/characters/Ouzo-the-Wolf-Bard.png");
        character3 = createCharacterSelectionRadioButton(menuToggleGroup, animationsMap, PRIME, "/com/swsb/rp9/characterselection/characters/Prime-the-Great-Sage.png");
        menuToggleGroup.selectToggle(character2);
        animationsMap.get(character2).play();
        menuToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            selectedCharacterAnimation(animationsMap, oldValue, newValue);
        });

        GridPane.setValignment(character2, VPos.CENTER);
        GridPane.setHalignment(character2, HPos.CENTER);
        GridPane.setValignment(character3, VPos.CENTER);
        GridPane.setHalignment(character3, HPos.CENTER);
        characterScreen.addRow(0, character1, character2, character3);
    }

    private RadioButton createCharacterSelectionRadioButton(ToggleGroup menuToggleGroup, HashMap<Toggle, SpriteAnimation> animationsMap, String charName, String charSprite) {
        RadioButton character1 = new RadioButton(charName);
        ImageView oratio = new ImageView(new Image(getClass().getResource(charSprite).toExternalForm()));
        oratio.setViewport(new Rectangle2D(0,140, 70, 70));
        animationsMap.put(character1, spriteAnimation().imageView(oratio).startingRow(2).columns(3).build());
        character1.setGraphic(oratio);
        character1.setToggleGroup(menuToggleGroup);
        GridPane.setValignment(character1, VPos.CENTER);
        GridPane.setHalignment(character1, HPos.CENTER);
        return character1;
    }

    private void selectedCharacterAnimation(HashMap<Toggle, SpriteAnimation> animationsMap, Toggle oldValue, Toggle newValue) {
        if(oldValue != null && animationsMap.get(oldValue) != null) {
            animationsMap.get(oldValue).stop();
        }
        animationsMap.get(newValue).play();
        if(characterHasDefaultName()) {
            characterNameInputField.setText(((RadioButton) newValue).getText());
        }
    }

    private boolean characterHasDefaultName() {
        return characterNameInputField.getText().equals(DEFAULT_CHAR_NAME)
                || characterNameInputField.getText().equals(OUZO)
                || characterNameInputField.getText().equals(PRIME)
                || characterNameInputField.getText().equals(ORATIO);
    }

    private void addCharacterNameAndStartButton(GridPane characterScreen) {
        characterNameInputField = createCharacterNameInputField();
        Button startGameButton = createStartGameButton();
        GridPane.setValignment(characterNameInputField, VPos.CENTER);
        GridPane.setHalignment(characterNameInputField, HPos.CENTER);
        GridPane.setValignment(startGameButton, VPos.CENTER);
        GridPane.setHalignment(startGameButton, HPos.CENTER);
        characterScreen.add(characterNameInputField, 0,1,3,1);
        characterScreen.add(startGameButton, 1,2,1,1);
    }

    private TextField createCharacterNameInputField() {
        characterNameTextField = new TextField(DEFAULT_CHAR_NAME);
        characterNameTextField.setPrefWidth(DIMENSIONS.getWidth() - 20);
        characterNameTextField.setAlignment(Pos.CENTER);
        return characterNameTextField;
    }

    private Button createStartGameButton() {
        Button startGameButton = new Button("Start game");
        startGameButton.setOnKeyPressed(
                event -> {
                    if (KeyCode.ENTER.equals(event.getCode())) {
                        startNewGame();
                    }
                }
        );
        startGameButton.setOnMouseClicked(
                event -> {
                    startNewGame();
                }
        );
        return startGameButton;
    }

    private void startNewGame() {
        getRestrictedState().setCharacterName(characterNameTextField.getText());
        registerTransitionSlot(TransitionSlot.TRANSITION_SLOT_ONE);
        getRestrictedState().setCharacterType(getCharacterType());
    }

    private CharacterType getCharacterType() {
        if(menuToggleGroup.getSelectedToggle().equals(character1)){
            return MERCENARY;
        }
        if(menuToggleGroup.getSelectedToggle().equals(character2)){
            return BARD;
        }
        if(menuToggleGroup.getSelectedToggle().equals(character3)){
            return SAGE;
        }
        return null;
    }

    private Background createStartScreenBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/com/swsb/rp9/characterselection/background/glacial_mountains_preview_lightened.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(0, 0, false, false, false, true));
        return new Background(backgroundImage);
    }
}
