package com.swsb.rp9.overworld.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.overworld.OverworldView;

public class OverworldScene extends GameScene {

    // TODO: Temp fix (should not be static)
    private static final OverworldView view = new OverworldView();

    public OverworldScene() {
        super(view.getDimension(), view.getBackgroundColor(), view.getNodes());
    }

    @Override
    protected void setOnSceneCompleteEventHandler() {
        getScene()
                .setOnMouseClicked(event ->
                    getSceneTransitionState()
                            .markAsReadyForTransition()
                            .transitionToChild(0));
    }

    @Override
    public String getTitle() {
        return "Overworld";
    }


}
