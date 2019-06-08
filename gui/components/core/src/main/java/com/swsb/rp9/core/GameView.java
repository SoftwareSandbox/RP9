package com.swsb.rp9.core;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static java.util.Collections.unmodifiableList;

public abstract class GameView {

    private final List<Node> guiElements;
    private final Queue<SceneTransitionPosition> sceneTransitions;
    private final Dimension dimensions;
    private final Color backgroundColor;

    public GameView(Dimension dimensions, Color backgroundColor) {
        this.dimensions = dimensions;
        this.backgroundColor = backgroundColor;
        guiElements = createGuiElements();
        sceneTransitions = new ArrayBlockingQueue<>(SceneTransitionPosition.values().length);
    }

    public void registerSceneTransition(SceneTransitionPosition sceneTransitionPosition) {
        sceneTransitions.add(sceneTransitionPosition);
    }

    protected abstract List<Node> createGuiElements();

    Queue<SceneTransitionPosition> getRegisteredSceneTransitions() {
        return sceneTransitions;
    }

    List<Node> getGuiElements() {
        return unmodifiableList(guiElements);
    }

    Dimension getDimension() {
        return dimensions;
    }

    Color getBackgroundColor() {
        return backgroundColor;
    }

}
