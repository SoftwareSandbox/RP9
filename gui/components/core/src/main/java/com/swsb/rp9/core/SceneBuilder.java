package com.swsb.rp9.core;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import static java.util.Arrays.asList;

public class SceneBuilder {

    private Color color;
    private EventHandler<? super KeyEvent> onKeyPressed;
    private Dimension dimension;
    private Parent root;

    public static SceneBuilder scene(){
        return new SceneBuilder();
    }

    public Scene build(){
        Scene scene = new Scene(root, dimension.getWidth(), dimension.getHeight(), color);
        scene.setOnKeyPressed(onKeyPressed);
        return scene;
    }

    public SceneBuilder nodes(Node... nodes) {
        this.root = new Group(asList(nodes));
        return this;
    }

    public SceneBuilder root(Parent root){
        this.root = root;
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
