package com.swsb.rp9.core;

import javafx.scene.Scene;

import java.util.Objects;
import java.util.UUID;

import static com.swsb.rp9.core.TransitionSlot.NO_TRANSITION_SLOT;

/**
 * Represents an entire game scene or 'screen'.
 * It owns a GameView and a (JavaFX) Scene.
 * It can be seen as the C(ontroller) in a MVC application.
 */
public abstract class GameScene {

    private final UUID uid;
    private final Scene scene;
    private GameView gameView;
    private GameSceneTransitionState gameSceneTransitionState;

    public GameScene(GameView gameView) {
        this.uid = UUID.randomUUID();
        this.gameView = assignDefaultViewIfNull(gameView);
        this.scene = addStylesheetToScene(
                createScene(this.gameView));
        this.gameSceneTransitionState = new GameSceneTransitionState();
        registerTransitionSlotsForSceneEvents();
    }

    protected abstract GameView createDefaultGameView();

    public void evaluateSceneTransition() {
        if (gameView.hasRegisteredSceneTransitionSlots()) {
            this.gameSceneTransitionState = new GameSceneTransitionState()
                    .usingTransitionSlot(gameView.getFirstRegisteredSceneTransitionSlot())
                    .markAsReadyForTransition();
        }
    }

    public boolean shouldTransitionToAnotherGameScene() {
        return gameSceneTransitionState.isReadyForTransition();
    }

    public TransitionSlot getActiveTransitionSlot() {
        return gameSceneTransitionState.getActiveTransitionSlot();
    }

    public void resetSceneTransitionState() {
        gameSceneTransitionState.reset();
    }

    public Scene getScene() {
        return scene;
    }

    public GameView getGameView() {
        return gameView;
    }

    public UUID getUUID() {
        return uid;
    }

    public void redraw() {
        gameView = gameView.redraw();
    }

    public String getTitleOfView() {
        return gameView.getTitle();
    }

    protected void registerTransitionSlotsForSceneEvents() {
    }

    private Scene createScene(GameView gameView) {
        return new Scene(gameView.getGuiRootNode(),
                gameView.getDimension().getWidth(),
                gameView.getDimension().getHeight());
    }

    private Scene addStylesheetToScene(Scene scene) {
        if (gameView.getStyleSheetLocation() != null && !gameView.getStyleSheetLocation().isBlank()) {
            scene.getStylesheets().add(gameView.getStyleSheetLocation());
        }
        return scene;
    }

    private GameView assignDefaultViewIfNull(GameView gameView) {
        return gameView == null ? createDefaultGameView() : gameView;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameScene gameScene = (GameScene) o;
        return Objects.equals(uid, gameScene.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    /**
     * Represents the transition state of a GamingScene instance.
     * Meaning, GameSceneTransitionState encapsulates the state required to decide whether or not the current GameScene
     * needs to be replaced by another (child) GameScene (due to an event made by the played).
     */
    private class GameSceneTransitionState {

        private boolean isReadyForTransition;
        private TransitionSlot activeTransitionSlot;

        private GameSceneTransitionState() {
            reset();
        }

        private boolean isReadyForTransition() {
            return isReadyForTransition;
        }

        private TransitionSlot getActiveTransitionSlot() {
            return activeTransitionSlot;
        }

        private void reset() {
            this.isReadyForTransition = false;
            this.activeTransitionSlot = NO_TRANSITION_SLOT;
        }

        private GameSceneTransitionState markAsReadyForTransition() {
            isReadyForTransition = true;
            return this;
        }

        private GameSceneTransitionState usingTransitionSlot(TransitionSlot activeTransitionSlot) {
            this.activeTransitionSlot = activeTransitionSlot;
            return this;
        }


    }
}
