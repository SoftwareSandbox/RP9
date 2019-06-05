package com.swsb.rp9.core;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.Objects;

import static java.util.Arrays.asList;

public abstract class GameScene {

    private final Scene scene;

    private final SceneTransitionState sceneTransitionState;

    public GameScene(Dimension dimension, Color backgroundColor, Node... nodes) {
        scene = new Scene(new Group(asList(nodes)), dimension.getWidth(), dimension.getHeight(), backgroundColor);
        sceneTransitionState = new GameScene.SceneTransitionState();
        setOnSceneCompleteEventHandler();
    }

    public abstract String getTitle();

    protected abstract void setOnSceneCompleteEventHandler();

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
