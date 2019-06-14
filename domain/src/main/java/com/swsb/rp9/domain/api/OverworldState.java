package com.swsb.rp9.domain.api;

public class OverworldState extends RestrictedState {

    public String getCharacterName() {
        return getGameState().getCharacterName();
    }

    public int getHitPoints() {
        return getGameState().getHitpoints();
    }


}
