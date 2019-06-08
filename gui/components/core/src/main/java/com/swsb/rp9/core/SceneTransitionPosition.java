package com.swsb.rp9.core;

public enum SceneTransitionPosition {

    NO_POSITION(-1),
    POSITION_ONE(0),
    POSITION_TWO(1),
    POSITION_THREE(2),
    POSITION_FOUR(3),
    POSITION_FIVE(4);

    private final int indexOfTransitionPosition;

    SceneTransitionPosition(int indexOfTransitionPosition) {
        this.indexOfTransitionPosition = indexOfTransitionPosition;
    }

    public int getIndexOfTransitionPosition() {
        return indexOfTransitionPosition;
    }
}
