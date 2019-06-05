package com.swsb.rp9.game.orchestrator;

import com.swsb.rp9.game.orchestrator.api.GameSceneTopology;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

class GameOrchestrator {

    private static final int SIXTY_FPS_DELAY = 10000 / 60;

    private final Stage stage;
    private final Timer timer;

    private final GameSceneTopology gameSceneTopology;

    GameOrchestrator(Stage stage) {
        this.stage = stage;
        this.timer = new Timer();
        gameSceneTopology = new GameSceneTopology();
        gameSceneTopology.createDefaultTopology();
    }

    void startGameLoop() {
        timer.schedule(new GameLoop(), 0, SIXTY_FPS_DELAY);
    }

    void stopGameLoop() {
        timer.cancel();
    }

    private class GameLoop extends TimerTask {

        private GameLoop() {
            setInitialScene();
            stage.show();
        }

        private void setInitialScene() {
            stage.setScene(gameSceneTopology.getCurrentGameScene().getScene());
            stage.setTitle(gameSceneTopology.getCurrentGameScene().getTitle());
        }

        /**
         * Actual Game Loop
         * Is continuously executed by the timer
         * (with a provided waiting period in-between executions)
         * <p>
         * Flow (TODO):
         * 1. Perform game logic (business rules)
         * 2. Redraw all GUI elements (move character,...), scene,...
         * <p>
         * Platform.runLater is required since we're changing JavaFX classes
         * on a Thread of Timer, not on the Thread of FX Application.
         */
        @Override
        public void run() {

            Platform.runLater(() -> {

                var currentGameScene = gameSceneTopology.transition();
                stage.setScene(currentGameScene.getScene());
                stage.setTitle(currentGameScene.getTitle());

            });

        }
    }
}
