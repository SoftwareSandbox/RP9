package com.swsb.rp9.overworld;

import com.swsb.rp9.core.Dimension;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.List;

public class OverworldView {
    private final List<Node> nodes;

    public OverworldView() {
        nodes = generateNodes();
    }

    public Node[] getNodes() {
        return nodes.toArray(new Node[0]);
    }

    public Dimension getDimension() {
        return Dimension.square(500);
    }

    public Color getBackgroundColor() {
        return Color.YELLOW;
    }

    private Node generateButton() {
        return new Button("Click somewhere (not on the button) to go back.");
    }

    private List<Node> generateNodes() {
        return Collections.singletonList(generateButton());
    }
}
