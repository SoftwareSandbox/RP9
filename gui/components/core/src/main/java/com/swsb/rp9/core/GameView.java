package com.swsb.rp9.core;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public abstract class GameView {

    private final List<Node> guiElements;
    private final Dimension dimensions;
    private final Color backgroundColor;

    public GameView(Dimension dimensions, Color backgroundColor) {
        this.dimensions = dimensions;
        this.backgroundColor = backgroundColor;
        guiElements = createGuiElements();
    }

    protected abstract List<Node> createGuiElements();


    public List<Node> getGuiElements() {
        return unmodifiableList(guiElements);
    }

    public Dimension getDimension() {
        return dimensions;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

}
