package com.swsb.rp9.secondary.module.frondend;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.List;

import static java.util.Arrays.asList;

public class SceneBuilder {

    private Color color;
    private EventHandler<? super KeyEvent> onKeyPressed;
    private List<Node> nodes;
    private Dimension dimension;

    public static SceneBuilder scene(){
        return new SceneBuilder();
    }

    public Scene build(){
        Scene scene = new Scene(new Group(nodes), dimension.getWidth(), dimension.getHeight(), color);
        scene.setOnKeyPressed(onKeyPressed);
        return scene;
    }

    public SceneBuilder nodes(Node... nodes) {
        this.nodes = asList(nodes);
        return this;
    }

    public SceneBuilder color(Color color) {
        this.color = color;
        return this;
    }

    public SceneBuilder onKeyPressed(EventHandler<? super KeyEvent> onKeyPressed){
        this.onKeyPressed = onKeyPressed;
        return this;
    }

    public SceneBuilder dimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }
}
