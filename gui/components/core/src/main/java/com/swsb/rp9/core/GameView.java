package com.swsb.rp9.core;

import javafx.scene.Parent;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Represents the visual part of a 'screen', the view.
 * It can be seen as the V(iew) in a MVC application.
 */
public abstract class GameView {

    private final Parent guiRootNode;
    private final Queue<TransitionSlot> registeredTransitionSlots;
    private final Dimension dimensions;

    public GameView(Dimension dimensions) {
        this.dimensions = dimensions;
        guiRootNode = createGuiRootNode();
        registeredTransitionSlots = new ArrayBlockingQueue<>(TransitionSlot.values().length);
    }

    public void registerTransitionSlot(TransitionSlot transitionSlot) {
        registeredTransitionSlots.add(transitionSlot);
    }

    public abstract GameView redraw();

    public abstract String getTitle();
    public abstract String getStyleSheetLocation();

    protected abstract Parent createGuiRootNode();

    boolean hasRegisteredSceneTransitionSlots() {
        return !registeredTransitionSlots.isEmpty();
    }

    TransitionSlot getFirstRegisteredSceneTransitionSlot() throws IllegalStateException {
        if(!hasRegisteredSceneTransitionSlots()) {
            throw new IllegalStateException("There are no TransitionSlot objects registered.");
        }
        var registeredTransitionSlot = registeredTransitionSlots.poll();
        removeOtherRegisteredTransitionSlots();
        return registeredTransitionSlot;
    }

    Parent getGuiRootNode() {
        return guiRootNode;
    }

    Dimension getDimension() {
        return dimensions;
    }

    /**
     * PSA: Should only be called by method {@code getFirstRegisteredSceneTransitionSlot}.
     * --------------------------------------------------------------------------------
     * We use a Queue to register transition slots. Since registration happens by (mainly) click or key events,
     * it's possible multiple transition slots get registered right after each other. We are only interested in the
     * first registered transition slot! Furthermore, should we don't want our first registered transition slot be
     * overridden by other registered transition slots. For those two reasons, we keep a FIFO Queue which we clear
     * after having handled its first entry.
     */
    private void removeOtherRegisteredTransitionSlots() {
        if(hasRegisteredSceneTransitionSlots()) {
            registeredTransitionSlots.clear();
        }
    }

}
