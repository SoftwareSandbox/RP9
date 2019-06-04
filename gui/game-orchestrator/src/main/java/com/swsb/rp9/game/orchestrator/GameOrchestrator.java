package com.swsb.rp9.game.orchestrator;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.overworld.api.OverworldScene;
import com.swsb.rp9.start.menu.api.StartMenuScene;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

class GameOrchestrator {

    private static final int SIXTY_FPS_DELAY = 10000 / 60;

    private final Stage stage;
    private final Timer timer;

    private GameScene currentGameScene;

    GameOrchestrator(Stage stage) {
        this.stage = stage;
        this.timer = new Timer();
        generateGameScenes();
    }

    /**
     * Creates and connects the different
     * scenes with each other.
     */
    private void generateGameScenes() {
        var startMenuScene = new StartMenuScene();
        var overworldScene = new OverworldScene();

        startMenuScene.setNextGameScene(overworldScene);
        overworldScene.setNextGameScene(startMenuScene);

        currentGameScene = startMenuScene;
    }

    void startGameLoop() {
        timer.schedule(new GameLoop(), 0, SIXTY_FPS_DELAY);
    }

    void stopGameLoop() {
        timer.cancel();
    }

    private class GameLoop extends TimerTask {

        private GameLoop() {
            initializePrimaryScene();
            stage.show();
        }

        private void initializePrimaryScene() {
            stage.setScene(currentGameScene.getScene());
            stage.setTitle(currentGameScene.getTitle());
        }

        /**
         * Actual Game Loop
         * Is continuously executed by the timer
         *  (with a provided waiting period in-between executions)
         *
         * Flow (TODO):
         *  1. Perform game logic (business rules)
         *  2. Redraw all GUI elements (move character,...), scene,...
         *
         *  Platform.runLater is required since we're changing JavaFX classes
         *  on a Thread of Timer, not on the Thread of FX Application.
         */
        @Override
        public void run() {

            Platform.runLater(() -> {

                if(currentGameScene.isComplete()) {
                    currentGameScene = currentGameScene.getNextGameScene();
                    stage.setScene(currentGameScene.getScene());
                    stage.setTitle(currentGameScene.getTitle());
                }

            });

        }
    }
}
