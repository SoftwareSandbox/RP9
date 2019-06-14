package com.swsb.rp9.fight;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.domain.api.FightState;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
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

public class FightView extends GameView<FightState> {

    private static final Dimension DIMENSIONS = rectangle(800, 640);
    private Label heroHitPointsLabel;
    private Label enemyHitPointsLabel;

    public FightView() {
        super(DIMENSIONS, new FightState());
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
        heroHitPointsLabel = createHeroHitPointsLabel();
        enemyHitPointsLabel = createEnemyHitPointsLabel();
        borderPane.setTop(new HBox(enemyHitPointsLabel, heroHitPointsLabel));
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

    private Label createEnemyHitPointsLabel() {
        Label label = new Label("HP: " + getRestrictedState().getEnemyHitpoints());
        label.setTextFill(BLACK);
        label.setScaleX(2);
        label.setScaleY(2);
        label.setLayoutY(40);
        label.setTranslateX(30);
        label.setTranslateY(30);
        return label;
    }

    private Label createHeroHitPointsLabel() {
        Label label = new Label("HP: " + getRestrictedState().getHeroHitpoints());
        label.setTextFill(BLACK);
        label.setScaleX(2);
        label.setScaleY(2);
        label.setLayoutY(40);
        label.setTranslateX(DIMENSIONS.getWidth() - 130);
        label.setTranslateY(30);
        return label;
    }

    public void redraw() {
        enemyHitPointsLabel.setText("HP: " + getRestrictedState().getEnemyHitpoints());
        heroHitPointsLabel.setText("HP: " + getRestrictedState().getHeroHitpoints());
    }

    private VBox createActionMenu() {
        ToggleGroup menuToggleGroup = new ToggleGroup();

        RadioButton attackButton = new RadioButton("Attack");
        attackButton.setTextFill(BLACK);
        attackButton.setSelected(true);
        attackButton.setToggleGroup(menuToggleGroup);
        attackButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                getRestrictedState().heroDamagesEnemy();
                if(getRestrictedState().isEnemyDefeated()) {
                    resetEnemy();
                    registerTransitionSlot(TRANSITION_SLOT_ONE);
                } else {
                    getRestrictedState().enemyDamagesHero();
                    if (getRestrictedState().isHeroDefeated()) {
                        getRestrictedState().resetGame();
                        registerTransitionSlot(TRANSITION_SLOT_TWO);
                    }
                }
            }
        });

        RadioButton fleeButton = new RadioButton("Flee");
        fleeButton.setTextFill(BLACK);
        fleeButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                registerTransitionSlot(TRANSITION_SLOT_ONE);
            }
        });

        fleeButton.setToggleGroup(menuToggleGroup);

        VBox vBox = new VBox(attackButton, fleeButton);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private void resetEnemy() {
        getRestrictedState().resetEnemy();
    }

    @Override
    public String getStyleSheetLocation() {
        return getClass().getResource("/com/swsb/rp9/fight/styles/action-menu.css").toExternalForm();
    }
}
