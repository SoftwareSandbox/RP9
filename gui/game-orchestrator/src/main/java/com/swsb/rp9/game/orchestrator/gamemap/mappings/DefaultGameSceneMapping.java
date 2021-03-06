package com.swsb.rp9.game.orchestrator.gamemap.mappings;

import com.swsb.rp9.characterselection.api.CharacterSelectionScene;
import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.credits.api.CreditsScene;
import com.swsb.rp9.credits.api.CreditsView;
import com.swsb.rp9.fight.api.FightScene;
import com.swsb.rp9.game.orchestrator.gamemap.IndividualGameSceneMap;
import com.swsb.rp9.overworld.api.OverworldScene;
import com.swsb.rp9.start.menu.api.StartMenuScene;

import java.util.*;

import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_TWO;
import static com.swsb.rp9.game.orchestrator.gamemap.IndividualGameSceneMap.gameScene;

/**
 * The Default Game Scene Mapping ('level design')
 */
public class DefaultGameSceneMapping implements GameSceneMapping {

    private GameScene initialGameScene;
    private Map<UUID, IndividualGameSceneMap> gameMap;

    public DefaultGameSceneMapping() {
        gameMap = createMapping();
    }

    @Override
    public Map<UUID, IndividualGameSceneMap> getMapping() {
        return Collections.unmodifiableMap(gameMap);
    }

    @Override
    public GameScene getInitialGameScene() {
        return initialGameScene;
    }

    private Map<UUID, IndividualGameSceneMap> createMapping() {
        gameMap = new HashMap<>();

        var startMenuScene = new StartMenuScene();
        var characterSelectionScene = new CharacterSelectionScene();
        var overworldScene = new OverworldScene();
        var fightScene = new FightScene();
        var creditsView = determineCreditsView();
        var creditsScene = new CreditsScene(creditsView);

        gameMap
                .putAll(
                        Map.of(
                                startMenuScene.getUUID(),
                                gameScene(startMenuScene)
                                        .canTransitionTo(characterSelectionScene, TRANSITION_SLOT_ONE)
                                        .canTransitionTo(creditsScene, TRANSITION_SLOT_TWO),
                                overworldScene.getUUID(),
                                gameScene(overworldScene)
                                        .canTransitionTo(startMenuScene, TRANSITION_SLOT_ONE)
                                        .canTransitionTo(fightScene, TRANSITION_SLOT_TWO),
                                creditsScene.getUUID(),
                                gameScene(creditsScene)
                                        .canTransitionTo(startMenuScene, TRANSITION_SLOT_ONE),
                                characterSelectionScene.getUUID(),
                                gameScene(characterSelectionScene)
                                        .canTransitionTo(overworldScene, TRANSITION_SLOT_ONE),
                                fightScene.getUUID(),
                                gameScene(fightScene)
                                        .canTransitionTo(overworldScene, TRANSITION_SLOT_ONE)
                                        .canTransitionTo(startMenuScene, TRANSITION_SLOT_TWO)
                        ));

        initialGameScene = startMenuScene;
        return gameMap;

    }

    private GameView determineCreditsView() {
        return (GameView) ServiceLoader.load(CreditsView.class)
                                       .stream()
                                       .map(ServiceLoader.Provider::get)
                                       .max(Comparator.comparingInt(CreditsView::awesomeness))
                                       .orElseThrow(() -> new RuntimeException("blab"));
    }
}
