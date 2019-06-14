package com.swsb.rp9.domain;

class Character {

    private static final int DEFAULT_HP = 100;

    private String name;
    private int hitPoints;

    Character() {
        hitPoints = DEFAULT_HP;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getHitPoints() {
        return hitPoints;
    }

    void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
