package com.swsb.rp9.core;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.Objects;

public abstract class GameScene {

    private final Scene scene;
    private final GameView gameView;
    private final SceneTransitionState sceneTransitionState;

    public GameScene(GameView gameView) {
        this.gameView = assignDefaultViewIfNull(gameView);
        this.scene = createScene(this.gameView);
        this.sceneTransitionState = new GameScene.SceneTransitionState();
        setOnSceneCompleteEventHandler();
    }

    public abstract String getTitle();
    protected abstract void setOnSceneCompleteEventHandler();
    protected abstract GameView createDefaultGameView();

    protected SceneTransitionState getSceneTransitionState() {
        return sceneTransitionState;
    }
    public boolean isReadyForTransition() {
        return sceneTransitionState.isReadyForTransition();
    }

    public int getIndexOfChildToTransitionTo() {
        return sceneTransitionState.getChildIndex();
    }

    public void resetSceneTransitionState() {
        sceneTransitionState.markAsNotReadyForTransition();
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameScene gameScene = (GameScene) o;
        return Objects.equals(scene, gameScene.scene) &&
                Objects.equals(sceneTransitionState, gameScene.sceneTransitionState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scene, sceneTransitionState);
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

    protected static class SceneTransitionState {

        private boolean isReadyForTransition;
        private int childIndex;

        private SceneTransitionState() {
            markAsNotReadyForTransition();
        }

        private boolean isReadyForTransition() {
            return isReadyForTransition;
        }


        private int getChildIndex() {
            return childIndex;
        }

        private void markAsNotReadyForTransition() {
            this.isReadyForTransition = false;
            this.childIndex = -1;
        }

        public SceneTransitionState markAsReadyForTransition() {
            isReadyForTransition = true;
            return this;
        }

        public SceneTransitionState transitionToChild(int childIndex) {
            this.childIndex = childIndex;
            return this;
        }


    }
}
