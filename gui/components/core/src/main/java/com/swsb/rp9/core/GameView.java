package com.swsb.rp9.core;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static java.util.Collections.unmodifiableList;

public abstract class GameView {

    private final List<Node> guiElements;
    private final Queue<TransitionSlot> registeredTransitionSlots;
    private final Dimension dimensions;
    private final Color backgroundColor;

    public GameView(Dimension dimensions, Color backgroundColor) {
        this.dimensions = dimensions;
        this.backgroundColor = backgroundColor;
        guiElements = createGuiElements();
        registeredTransitionSlots = new ArrayBlockingQueue<>(1);
    }

    public void registerTransitionSlot(TransitionSlot transitionSlot) {
        registeredTransitionSlots.add(transitionSlot);
    }

    public abstract GameView redraw();

    protected abstract List<Node> createGuiElements();

    boolean hasRegisteredSceneTransitionSlots() {
        return !registeredTransitionSlots.isEmpty();
    }

    TransitionSlot getFirstRegisteredSceneTransitionSlot() {
        if(!hasRegisteredSceneTransitionSlots()) {
            throw new IllegalStateException("There are no TransitionSlot objects registered.");
        }
        var registeredTransitionSlot = registeredTransitionSlots.poll();
        removeOtherRegisteredTransitionSlots();
        return registeredTransitionSlot;
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
