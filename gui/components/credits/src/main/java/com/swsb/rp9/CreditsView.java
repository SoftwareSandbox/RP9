package com.swsb.rp9;

import com.swsb.rp9.core.Dimension;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;

public class CreditsView {

    private final List<Node> nodes;

    public CreditsView() {
        nodes = generateNodes();
    }

    public Node[] getNodes() {
        return nodes.toArray(new Node[0]);
    }

    public Dimension getDimension() {
        return Dimension.square(750);
    }

    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    private List<Node> generateNodes() {
        return List.of(
                generateLabel(),
                generateCredits());
    }

    private Node generateLabel() {
        Label label = new Label("Press B to go back!");
        label.setTextFill(Color.WHITE);
        return label;
    }

    private Node generateCredits() {
        Label label = new Label("CREDITS: Some Cegeka people! 6 in total");
        label.setTextFill(Color.YELLOW);
        return label;
    }

}
