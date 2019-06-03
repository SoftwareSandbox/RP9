package com.swsb.rp9.start.menu;

import javafx.scene.Node;
import javafx.scene.control.Button;

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

    private List<Node> generateNodes() {
        return Collections.singletonList(generateButton());
    }

    private Node generateButton() {
        return new Button("Play game");
    }

}
