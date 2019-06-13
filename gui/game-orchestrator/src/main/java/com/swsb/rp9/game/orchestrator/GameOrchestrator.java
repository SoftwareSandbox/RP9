package com.swsb.rp9.game.orchestrator;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.game.orchestrator.gamemap.FullGameSceneMap;
import com.swsb.rp9.game.orchestrator.gamemap.mappings.DefaultGameSceneMapping;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Orchestrates the entire game.
 * It contains the game loop running at an fps of {@code #FPS}.
 * It loads in the different scenes (such as the start menu and the overworld)
 */
class GameOrchestrator {

    private static final int FPS = get60FPS();

    private final Stage stage;
    private final Timer timer;
    private final GameScene gameSceneToShowOnStartup;

    private final FullGameSceneMap fullGameSceneMap;

    GameOrchestrator(Stage stage) {
        this.stage = stage;
        this.timer = new Timer();
        fullGameSceneMap = new FullGameSceneMap(new DefaultGameSceneMapping());
        gameSceneToShowOnStartup = fullGameSceneMap.getInitialGameScene();
    }

    void startGameLoop() {
        timer.schedule(new GameLoop(gameSceneToShowOnStartup), 0, FPS);
    }

    void stopGameLoop() {
        timer.cancel();
    }

    private static int get60FPS() { return 1000 / 60; }

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
            currentGameScene.onTransitionToThisScene();
        }

        private void setInitialScene(GameScene gameSceneToShowOnStartup) {
            stage.setScene(gameSceneToShowOnStartup.getScene());
            stage.setTitle(gameSceneToShowOnStartup.getTitleOfView());
        }

        /**
         * This method is the actual (infinite) game loop.
         *
         * Platform.runLater is required since we're changing JavaFX classes
         * on a Thread of Timer, not on the Thread of FX Application.
         */
        @Override
        public void run() {

            Platform.runLater(() -> {
                loadInNewSceneIfRequired();
                currentGameScene.redraw();
            });

        }

        private void loadInNewSceneIfRequired() {
            currentGameScene.evaluateSceneTransition();

            if (currentGameScene.shouldTransitionToAnotherGameScene()) {
                currentGameScene.onTransitionToOtherScene();
                var newGameScene = fullGameSceneMap
                        .getGameSceneToTransitionTo(currentGameScene.getUUID());
                stage.setScene(newGameScene.getScene());
                stage.setTitle(newGameScene.getTitleOfView());
                currentGameScene = newGameScene;
                currentGameScene.onTransitionToThisScene();
            }
        }
    }
}
