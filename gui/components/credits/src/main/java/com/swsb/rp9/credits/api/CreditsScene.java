package com.swsb.rp9.credits.api;

import com.swsb.rp9.CreditsView;
import com.swsb.rp9.core.GameScene;
import javafx.scene.input.KeyCode;

public class CreditsScene extends GameScene {

    // TODO: Temp fix (should not be static)
    private static final CreditsView view = new CreditsView();

    public CreditsScene() {
        super(view.getDimension(), view.getBackgroundColor(), view.getNodes());
    }

    @Override
    protected void setOnSceneCompleteEventHandler() {
        getScene().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.B)) {
                getSceneTransitionState()
                        .markAsReadyForTransition()
                        .transitionToChild(0);
            }
        });
    }

    @Override
    public String getTitle() {
        return "Credits";
    }
}
