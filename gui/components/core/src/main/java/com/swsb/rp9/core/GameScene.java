package com.swsb.rp9.core;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import static java.util.Arrays.asList;

public abstract class GameScene {

    private final Scene scene;

    private GameScene nextGameScene;

    private boolean isComplete;

    public GameScene(Dimension dimension, Color backgroundColor, Node... nodes) {
        scene = new Scene(new Group(asList(nodes)), dimension.getWidth(), dimension.getHeight(), backgroundColor);
        setOnSceneCompleteEventHandler();
    }

    public Scene getScene() {
        return scene;
    }

    protected abstract void setOnSceneCompleteEventHandler();

    protected void markAsComplete() {
        isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setNextGameScene(GameScene nextGameScene) {
        this.nextGameScene = nextGameScene;
    }

    public GameScene getNextGameScene() {
        isComplete = false; // reset on scene change (TODO: temp fix, should be removed)
        return nextGameScene;
    }

    public abstract String getTitle();
}
