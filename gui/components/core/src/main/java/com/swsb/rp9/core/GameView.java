package com.swsb.rp9.core;

import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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

    public abstract void redraw();
    public abstract String getTitle();
    protected abstract Parent createGuiRootNode();
    public abstract String getStyleSheetLocation();

    /**
     * Allows to react to KeyPressedEvents that occurred on the (JavaFX) Scene itself (not on individual elements
     * such as buttons)
     * Can be overridden by the subclasses.
     */
    protected void setOnKeyPressedForScene(KeyEvent event) {}

    /**
     * Allows to react to MouseClickedEvents that occurred on the (JavaFX) Scene itself (not on individual elements
     * such as buttons)
     * Can be overridden by the subclasses.
     */
    protected void setOnMouseClickedForScene(MouseEvent event) {}

    protected void registerTransitionSlot(TransitionSlot transitionSlot) {
        registeredTransitionSlots.add(transitionSlot);
    }

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
