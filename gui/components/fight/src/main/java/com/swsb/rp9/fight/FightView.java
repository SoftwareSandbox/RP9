package com.swsb.rp9.fight;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.core.ImageBuilder.image;
import static com.swsb.rp9.core.Position.position;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_TWO;
import static javafx.scene.paint.Color.BLACK;

public class FightView extends GameView {

    private static final Dimension DIMENSIONS = rectangle(640, 480);

    public FightView() {
        super(DIMENSIONS, null);
    }

    @Override
    public void redraw() {

    }

    @Override
    public String getTitle() {
        return "<mortalCombatVoice>Fight!</mortalCombatVoice>";
    }

    @Override
    protected Parent createGuiRootNode() {
        ImageView hero = createHeroView();
        ImageView enemy = createEnemyView();
        Group characterGroup = new Group(hero, enemy);


        VBox actionMenu = createActionMenu();
        GridPane gridPane = new GridPane();
        gridPane.add(characterGroup, 0, 0);
        gridPane.add(actionMenu, 0, 1);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(characterGroup);
        borderPane.setBottom(actionMenu);
        return borderPane;
    }

    private ImageView createHeroView() {
        return image()
                    .url(this.getClass().getResource("/com/swsb/rp9/fight/sprites/hero/mercinary_fight_tmp.png").toExternalForm())
                    .startingPosition(position(DIMENSIONS.getWidth() / 4 * 3, DIMENSIONS.getHeight() / 3))
                    .buildView();
    }

    private ImageView createEnemyView() {
        return image()
                    .url(this.getClass().getResource("/com/swsb/rp9/fight/sprites/enemy/wisp.png").toExternalForm())
                    .startingPosition(position(DIMENSIONS.getWidth() / 5, DIMENSIONS.getHeight() / 4))
                    .dimension(square(120))
                    .buildView();
    }

    private VBox createActionMenu() {
        ToggleGroup menuToggleGroup = new ToggleGroup();

        RadioButton newGameButton = new RadioButton("Attack");
        newGameButton.setTextFill(BLACK);
        newGameButton.setSelected(true);
        newGameButton.setToggleGroup(menuToggleGroup);
        newGameButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
//                registerTransitionSlot(TRANSITION_SLOT_ONE);
            }
        });

        RadioButton creditsButton = new RadioButton("Flee");
        creditsButton.setTextFill(BLACK);
        creditsButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                registerTransitionSlot(TRANSITION_SLOT_ONE);
            }
        });

        creditsButton.setToggleGroup(menuToggleGroup);

        VBox vBox = new VBox(newGameButton, creditsButton);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    @Override
    public String getStyleSheetLocation() {
        return getClass().getResource("/com/swsb/rp9/fight/styles/action-menu.css").toExternalForm();
    }
}
