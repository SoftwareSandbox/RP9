package com.swsb.rp9.game.orchestrator.gamemap;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.TransitionSlot;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents to what other GameScene objects a single GameScene is connected (linked),
 * and thus will be able to transition to (a GameScene object can only transition to linked GameScene objects).
 */
public class IndividualGameSceneMap {

    private final GameScene gameScene;
    private final List<GameScene> linkedGameScenes;

    private IndividualGameSceneMap(GameScene gameScene) {
        this.gameScene = gameScene;
        linkedGameScenes = new ArrayList<>();
    }

    public static IndividualGameSceneMap gameScene(GameScene gameScene) {
        return new IndividualGameSceneMap(gameScene);
    }

    public IndividualGameSceneMap isLinkedTo(GameScene succeedingGameScene, TransitionSlot transitionSlot) {
        linkedGameScenes.add(transitionSlot.asIndex(), succeedingGameScene);
        return this;
    }

    GameScene getLinkedGameSceneToTransitionTo() {
        GameScene gameSceneToTransitionTo = linkedGameScenes
                .get(gameScene.getActiveTransitionSlot().asIndex());
        gameScene.resetSceneTransitionState();
        return gameSceneToTransitionTo;
    }
}
