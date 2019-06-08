package com.swsb.rp9.core;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.UUID;

import static com.swsb.rp9.core.SceneTransitionPosition.NO_POSITION;

public abstract class GameScene {

    private final UUID uid;
    private final Scene scene;
    private final GameView gameView;
    private SceneTransitionState sceneTransitionState;

    public GameScene(GameView gameView) {
        this.uid = UUID.randomUUID();
        this.gameView = assignDefaultViewIfNull(gameView);
        this.scene = createScene(this.gameView);
        this.sceneTransitionState = new GameScene.SceneTransitionState();
    }

    public abstract String getTitle();
    protected abstract GameView createDefaultGameView();

    public void performSceneTransition() {
        if(!gameView.getRegisteredSceneTransitions().isEmpty()) {
            this.sceneTransitionState =  new GameScene.SceneTransitionState()
                    .transitionToChild(gameView.getRegisteredSceneTransitions().poll())
                    .markAsReadyForTransition();
        }
    }

    public boolean isReadyForTransition() {
        return sceneTransitionState.isReadyForTransition();
    }

    public SceneTransitionPosition getIndexOfChildToTransitionTo() {
        return sceneTransitionState.getChildIndex();
    }

    public void resetSceneTransitionState() {
        sceneTransitionState.markAsNotReadyForTransition();
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

    private Scene createScene(GameView gameView) {
        return new Scene(new Group(gameView.getGuiElements()),
                gameView.getDimension().getWidth(),
                gameView.getDimension().getHeight(),
                gameView.getBackgroundColor());
    }

    private GameView assignDefaultViewIfNull(GameView gameView) {
        return gameView == null ? createDefaultGameView() : gameView;
    }

    /**
     * Represents the transition state of a GamingScene instance.
     * Meaning, SceneTransitionState encapsulates the state required to decide whether or not the current GameScene
     * needs to be replaced by another (child) GameScene (due to an event made by the played).
     */
    private static class SceneTransitionState {

        private boolean isReadyForTransition;
        private SceneTransitionPosition childIndex;

        private SceneTransitionState() {
            markAsNotReadyForTransition();
        }

        private boolean isReadyForTransition() {
            return isReadyForTransition;
        }


        private SceneTransitionPosition getChildIndex() {
            return childIndex;
        }

        private void markAsNotReadyForTransition() {
            this.isReadyForTransition = false;
            this.childIndex = NO_POSITION;
        }

        private SceneTransitionState markAsReadyForTransition() {
            isReadyForTransition = true;
            return this;
        }

        private SceneTransitionState transitionToChild(SceneTransitionPosition childIndex) {
            this.childIndex = childIndex;
            return this;
        }


    }
}
