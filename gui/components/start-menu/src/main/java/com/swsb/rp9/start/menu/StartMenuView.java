package com.swsb.rp9.start.menu;

import com.swsb.rp9.core.Dimension;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.List;

public class StartMenuView {

    private final List<Node> nodes;

    public StartMenuView() {
        nodes = generateNodes();
    }

    public Node[] getNodes() {
        return nodes.toArray(new Node[0]);
    }

    public Dimension getDimension() {
        return Dimension.square(750);
    }

    public Color getBackgroundColor() {
        return Color.DARKBLUE;
    }

    private List<Node> generateNodes() {
        return Collections.singletonList(generateLabel());
    }

    private Node generateLabel() {
        Label label = new Label("Press N to advance");
        label.setTextFill(Color.WHITE);
        return label;
    }

}
