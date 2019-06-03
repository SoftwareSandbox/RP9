package com.swsb.rp9.start.menu.api;

import com.swsb.rp9.generic.scene.Dimension;
import com.swsb.rp9.generic.scene.SceneBuilder;
import com.swsb.rp9.start.menu.StartMenuView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class StartMenuScene {

    private final SceneBuilder sceneBuilder;
    private final StartMenuView view;

    private StartMenuScene() {
        sceneBuilder = new SceneBuilder();
        view = new StartMenuView();
    }

    public static StartMenuScene startMenuScene(Dimension dimension, EventHandler<? super KeyEvent> onKeyPressed) {
        var startMenuScene = new StartMenuScene();
        startMenuScene.sceneBuilder
                .dimension(dimension)
                .onKeyPressed(onKeyPressed);
        return startMenuScene;
    }

    public Scene build() {
        return sceneBuilder
                .color(Color.BEIGE)
                .nodes(view.getNodes())
                .build();
    }



}
