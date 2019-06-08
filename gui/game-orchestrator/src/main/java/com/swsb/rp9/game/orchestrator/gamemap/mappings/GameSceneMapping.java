package com.swsb.rp9.game.orchestrator.gamemap.mappings;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.game.orchestrator.gamemap.IndividualGameSceneMap;

import java.util.Map;
import java.util.UUID;

public interface GameSceneMapping {

    Map<UUID, IndividualGameSceneMap> getMapping();
    GameScene getInitialGameScene();

}
