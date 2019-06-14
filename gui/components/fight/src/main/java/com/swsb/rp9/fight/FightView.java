package com.swsb.rp9.fight;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.domain.api.FightState;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    private ProgressBar heroProgressBar;
    private ProgressBar enemyProgressBar;

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
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(characterGroup);
        borderPane.setBottom(actionMenu);
        heroHitPointsLabel = createHeroHitPointsLabel();
        heroProgressBar = createHeroProgressBar();
        enemyHitPointsLabel = createEnemyHitPointsLabel();
        enemyProgressBar = createEnemyProgressBar();
        BorderPane hitpointsPane = new BorderPane();
        VBox enemyVBox = new VBox(enemyHitPointsLabel, enemyProgressBar);
        enemyVBox.setSpacing(10);
        enemyVBox.setAlignment(Pos.BASELINE_RIGHT);
        hitpointsPane.setLeft(enemyVBox);
        VBox heroVBox = new VBox(heroHitPointsLabel, heroProgressBar);
        heroVBox.setSpacing(10);
        heroVBox.setAlignment(Pos.BASELINE_LEFT);
        hitpointsPane.setRight(heroVBox);
        borderPane.setTop(hitpointsPane);
        return borderPane;
    }

    private ProgressBar createEnemyProgressBar() {
        return new ProgressBar(getEnemyHealthPercentage());
    }

    private ProgressBar createHeroProgressBar() {
        ProgressBar progressBar = new ProgressBar(getHeroHealthPercentage());
        return progressBar;
    }

    public void redraw() {
        enemyHitPointsLabel.setText("HP: " + getRestrictedState().getEnemyHitpoints());
        heroHitPointsLabel.setText("HP: " + getRestrictedState().getHeroHitpoints());
        heroProgressBar.setProgress(getHeroHealthPercentage());
        enemyProgressBar.setProgress(getEnemyHealthPercentage());
    }

    private double getEnemyHealthPercentage() {
        return (double) getRestrictedState().getEnemyHitpoints() / getRestrictedState().getEnemyMaxHitpoints();
    }

    private double getHeroHealthPercentage() {
        return (double) getRestrictedState().getHeroHitpoints() / getRestrictedState().getHeroMaxHitpoints();
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
        return label;
    }

    private Label createHeroHitPointsLabel() {
        Label label = new Label("HP: " + getRestrictedState().getHeroHitpoints());
        label.setTextFill(BLACK);
        label.setScaleX(2);
        label.setScaleY(2);
        return label;
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
                if (getRestrictedState().isEnemyDefeated()) {
                    resetEnemy();
                    registerTransitionSlot(TRANSITION_SLOT_ONE);
                } else {
                    getRestrictedState().enemyDamagesHero();
                    if (getRestrictedState().isHeroDefeated()) {
                        getRestrictedState().resetGame();
                        // TODO: move this to a better location, use onTransitionToThisScene hook on scene?
                        getRestrictedState().resetEnemy();
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
