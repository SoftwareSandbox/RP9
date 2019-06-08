package com.swsb.rp9.game.orchestrator;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.SceneTransitionPosition;
import com.swsb.rp9.credits.api.CreditsScene;
import com.swsb.rp9.overworld.api.OverworldScene;
import com.swsb.rp9.start.menu.api.StartMenuScene;

import java.util.*;

import static com.swsb.rp9.core.SceneTransitionPosition.POSITION_ONE;
import static com.swsb.rp9.core.SceneTransitionPosition.POSITION_TWO;

public class GameSceneTopology {

    private GameScene currentGameScene;
    private Map<UUID, TopologyNode> gameTopology;

    public GameSceneTopology() {
        this.gameTopology = new HashMap<>();
    }

    public GameScene getCurrentGameScene() {
        return currentGameScene;
    }

    /**
     * A method to create a default scene topology.
     * Creates and connects the different scenes with each other.
     */
    public void createDefaultTopology() {
        var startMenuScene = new StartMenuScene();
        var overworldScene = new OverworldScene();
        var creditsScene = new CreditsScene();

        gameTopology
                .putAll(
                        Map.of(
                                startMenuScene.getUUID(),
                                TopologyNode.create(startMenuScene)
                                        .addChildGameScene(POSITION_ONE, overworldScene)
                                        .addChildGameScene(POSITION_TWO, creditsScene),
                                overworldScene.getUUID(),
                                TopologyNode.create(overworldScene)
                                        .addChildGameScene(POSITION_ONE, startMenuScene),
                                creditsScene.getUUID(),
                                TopologyNode.create(creditsScene)
                                        .addChildGameScene(POSITION_ONE, startMenuScene)
                        ));


        currentGameScene = startMenuScene;
    }

    public GameScene transition() {
        currentGameScene.performSceneTransition();
        if (currentGameScene.isReadyForTransition()) {
            var previousGameScene = currentGameScene;
            currentGameScene = gameTopology.get(currentGameScene.getUUID())
                    .getChildGameSceneToTransitionTo();
            previousGameScene.resetSceneTransitionState();
        }
        return currentGameScene;
    }

    /**
     * Represents a single node in the topology
     */
    private static class TopologyNode {

        private GameScene gameScene;
        private List<GameScene> childGameScenes;

        private TopologyNode(GameScene gameScene) {
            this.gameScene = gameScene;
            childGameScenes = new ArrayList<>();
        }

        private static TopologyNode create(GameScene gameScene) {
            return new TopologyNode(gameScene);
        }

        private TopologyNode addChildGameScene(SceneTransitionPosition sceneTransitionPosition, GameScene succeedingGameScene) {
            childGameScenes.add(sceneTransitionPosition.getIndexOfTransitionPosition(), succeedingGameScene);
            return this;
        }

        private GameScene getChildGameSceneToTransitionTo() {
            return childGameScenes
                    .get(gameScene.getIndexOfChildToTransitionTo().getIndexOfTransitionPosition());
        }
    }

}
