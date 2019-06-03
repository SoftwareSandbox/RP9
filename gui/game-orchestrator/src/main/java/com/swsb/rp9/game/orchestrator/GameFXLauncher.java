package com.swsb.rp9.game.orchestrator;

import com.swsb.rp9.generic.scene.Dimension;
import com.swsb.rp9.start.menu.api.StartMenuScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameFXLauncher extends Application {

    private static final double SCENE_WIDTH = 960;

    /**
     * Use this method to launch the game.
     */
    public void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage
                .setScene(StartMenuScene
                .startMenuScene(Dimension.square(SCENE_WIDTH), event ->
                        System.out.println(event.getCode().getName()))
                .build());
        primaryStage.setTitle("RP9");
        primaryStage.show();
    }
}
