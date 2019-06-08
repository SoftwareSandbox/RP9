package com.swsb.rp9.game.orchestrator;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.TransitionSlot;
import com.swsb.rp9.credits.api.CreditsScene;
import com.swsb.rp9.overworld.api.OverworldScene;
import com.swsb.rp9.start.menu.api.StartMenuScene;

import java.util.*;

import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_TWO;
import static com.swsb.rp9.game.orchestrator.GameSceneMapping.GameSceneLinking.gameScene;

/**
 * Represents the entire structure (mapping) of all the scenes part of the gameMap.
 * It contains which scenes can transition into (and are thus linked to) other scenes.
 */
class GameSceneMapping {

    private Map<UUID, GameSceneLinking> gameMap;

    GameSceneMapping() {
        this.gameMap = new HashMap<>();
    }

    /**
     * A method to create a default mapping of game scenes (you can see this as the default 'level' design).
     * Creates and connects the different scenes with each other.
     *
     * @return the GameScene that should be shown on startup of the gameMap.
     */
    GameScene createDefaultMapping() {
        var startMenuScene = new StartMenuScene();
        var overworldScene = new OverworldScene();
        var creditsScene = new CreditsScene();

        gameMap
                .putAll(
                        Map.of(
                                startMenuScene.getUUID(),
                                gameScene(startMenuScene)
                                        .isLinkedTo(overworldScene, TRANSITION_SLOT_ONE)
                                        .isLinkedTo(creditsScene, TRANSITION_SLOT_TWO),
                                overworldScene.getUUID(),
                                gameScene(overworldScene)
                                        .isLinkedTo(startMenuScene, TRANSITION_SLOT_ONE),
                                creditsScene.getUUID(),
                                gameScene(creditsScene)
                                        .isLinkedTo(startMenuScene, TRANSITION_SLOT_ONE)
                        ));

        return startMenuScene;
    }

    GameScene performSceneTransition(GameScene currentGameScene) {

        currentGameScene.evaluateSceneTransition();

        if (currentGameScene.shouldTransitionToAnotherGameScene()) {
            return gameMap.get(currentGameScene.getUUID())
                    .getLinkedGameSceneToTransitionTo();
        }
        return currentGameScene;
    }

    /**
     * Represents to what other GameScene objects a single GameScene is connected (linked),
     * and thus will be able to transition to (a GameScene object can only transition to linked GameScene objects).
     */
    static class GameSceneLinking {

        private final GameScene gameScene;
        private final List<GameScene> linkedGameScenes;

        private GameSceneLinking(GameScene gameScene) {
            this.gameScene = gameScene;
            linkedGameScenes = new ArrayList<>();
        }

        static GameSceneLinking gameScene(GameScene gameScene) {
            return new GameSceneLinking(gameScene);
        }

        private GameSceneLinking isLinkedTo(GameScene succeedingGameScene, TransitionSlot transitionSlot) {
            linkedGameScenes.add(transitionSlot.asIndex(), succeedingGameScene);
            return this;
        }

        private GameScene getLinkedGameSceneToTransitionTo() {
            GameScene gameSceneToTransitionTo = linkedGameScenes
                    .get(gameScene.getActiveTransitionSlot().asIndex());
            gameScene.resetSceneTransitionState();
            return gameSceneToTransitionTo;
        }
    }

}
