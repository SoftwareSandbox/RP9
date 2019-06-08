package com.swsb.rp9.game.orchestrator.gamemap;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.game.orchestrator.gamemap.mappings.GameSceneMapping;

import java.util.UUID;

/**
 * Represents the entire structure (mapping) of all the scenes part of the full game.
 * It holds the state to know which scenes can transition into (and are thus linked to) other scenes.
 */
public class FullGameSceneMap {

    private final GameSceneMapping gameSceneMapping;

    public FullGameSceneMap(GameSceneMapping gameSceneMapping) {
        this.gameSceneMapping = gameSceneMapping;
    }

    /**
     * @param uuidOfGameSceneToTransitionFrom The UUID of the GameScene FROM which we are going to transition
     * @return the GameScene TO which we are going to transition
     */
    public GameScene getLinkedGameSceneToTransitionTo(UUID uuidOfGameSceneToTransitionFrom) {
        return gameSceneMapping.getMapping()
                .get(uuidOfGameSceneToTransitionFrom)
                .getLinkedGameSceneToTransitionTo();
    }

    public GameScene getInitialGameScene() {
        return gameSceneMapping.getInitialGameScene();
    }
}
