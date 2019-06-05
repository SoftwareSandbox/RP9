package com.swsb.rp9.game.orchestrator.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.credits.api.CreditsScene;
import com.swsb.rp9.overworld.api.OverworldScene;
import com.swsb.rp9.start.menu.api.StartMenuScene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSceneTopology {

    private GameScene currentGameScene;
    private Map<GameScene, TopologyNode> gameTopology;

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
                                startMenuScene,
                                TopologyNode.create(startMenuScene)
                                        .addChildGameScene(overworldScene)
                                        .addChildGameScene(creditsScene),
                                overworldScene,
                                TopologyNode.create(overworldScene)
                                        .addChildGameScene(startMenuScene),
                                creditsScene,
                                TopologyNode.create(creditsScene)
                                        .addChildGameScene(startMenuScene)
                        ));


        currentGameScene = startMenuScene;
    }

    public GameScene transition() {
        if (currentGameScene.isReadyForTransition()) {
            var previousGameScene = currentGameScene;
            currentGameScene = gameTopology.get(currentGameScene)
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

        private TopologyNode addChildGameScene(GameScene succeedingGameScene) {
            childGameScenes.add(succeedingGameScene);
            return this;
        }

        private GameScene getChildGameSceneToTransitionTo() {
            return childGameScenes
                    .get(gameScene.getIndexOfChildToTransitionTo());
        }
    }

}
