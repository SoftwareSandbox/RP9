package com.swsb.rp9.game.orchestrator;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameFXLauncher extends Application {

    private GameOrchestrator orchestrator;

    /**
     * Use this method to launch the game.
     */
    public void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) {
        orchestrator = new GameOrchestrator(primaryStage);
        orchestrator.startGameLoop();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        orchestrator.stopGameLoop();
    }
}
