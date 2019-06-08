package com.swsb.rp9.game.orchestrator;

import com.swsb.rp9.core.GameScene;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Orchestrates the entire game.
 * It contains the game loop running at an fps of {@value #FRAMES_PER_SECOND}.
 * It loads in the different scenes (such as the start menu and the overworld)
 */
class GameOrchestrator {

    private static final int FRAMES_PER_SECOND = 10000 / 60;

    private final Stage stage;
    private final Timer timer;
    private final GameScene gameSceneToShowOnStartup;

    private final GameSceneMapping gameSceneMapping;

    GameOrchestrator(Stage stage) {
        this.stage = stage;
        this.timer = new Timer();
        gameSceneMapping = new GameSceneMapping();
        gameSceneToShowOnStartup = gameSceneMapping.createDefaultMapping();
    }

    void startGameLoop() {
        timer.schedule(new GameLoop(gameSceneToShowOnStartup), 0, FRAMES_PER_SECOND);
    }

    void stopGameLoop() {
        timer.cancel();
    }

    /**
     * Represents the game loop.
     * Method {@code run} is (almost) continuously executed.
     */
    private class GameLoop extends TimerTask {

        private GameScene currentGameScene;

        private GameLoop(GameScene gameSceneToShowOnStartup) {
            this.currentGameScene = gameSceneToShowOnStartup;
            setInitialScene(gameSceneToShowOnStartup);
            stage.show();
        }

        private void setInitialScene(GameScene gameSceneToShowOnStartup) {
            stage.setScene(gameSceneToShowOnStartup.getScene());
            stage.setTitle(gameSceneToShowOnStartup.getTitle());
        }

        /**
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
                performSceneTransitionIfRequired();
            });

        }

        private void performSceneTransitionIfRequired() {
            var currentOrNewGameScene = gameSceneMapping.performSceneTransition(currentGameScene);
            if(!currentGameScene.equals(currentOrNewGameScene)) {
                currentGameScene = currentOrNewGameScene;
                stage.setScene(currentOrNewGameScene.getScene());
                stage.setTitle(currentOrNewGameScene.getTitle());
            }
        }
    }
}
