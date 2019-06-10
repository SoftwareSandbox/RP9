package com.swsb.rp9.game.orchestrator.gamemap;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.TransitionSlot;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents to what other GameScene objects a single GameScene can transition to.
 */
public class IndividualGameSceneMap {

    private final GameScene gameScene;
    private final List<GameScene> gameScenesToTransitionTo;

    private IndividualGameSceneMap(GameScene gameScene) {
        this.gameScene = gameScene;
        gameScenesToTransitionTo = new ArrayList<>();
    }

    public static IndividualGameSceneMap gameScene(GameScene gameScene) {
        return new IndividualGameSceneMap(gameScene);
    }

    public IndividualGameSceneMap canTransitionTo(GameScene succeedingGameScene, TransitionSlot transitionSlot) {
        gameScenesToTransitionTo.add(transitionSlot.asIndex(), succeedingGameScene);
        return this;
    }

    GameScene getGameSceneToTransitionTo() {
        GameScene gameSceneToTransitionTo = gameScenesToTransitionTo
                .get(gameScene.getActiveTransitionSlot().asIndex());
        gameScene.resetSceneTransitionState();
        return gameSceneToTransitionTo;
    }
}
