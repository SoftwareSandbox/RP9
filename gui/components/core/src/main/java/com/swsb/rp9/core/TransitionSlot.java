package com.swsb.rp9.core;

/**
 * TransitionSlot objects are the glue that allows to load in new scenes when a specific event is triggered
 * (e.g. clicking a specific button). In essence, they represent an index, nothing more.
 */
public enum TransitionSlot {

    NO_TRANSITION_SLOT(-1),
    TRANSITION_SLOT_ONE(0),
    TRANSITION_SLOT_TWO(1),
    TRANSITION_SLOT_THREE(2),
    TRANSITION_SLOT_FOUR(3),
    TRANSITION_SLOT_FIVE(4);

    private final int indexOfTransitionSlot;

    TransitionSlot(int indexOfTransitionSlot) {
        this.indexOfTransitionSlot = indexOfTransitionSlot;
    }

    public int asIndex() {
        return indexOfTransitionSlot;
    }
}

