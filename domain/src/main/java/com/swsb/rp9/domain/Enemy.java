package com.swsb.rp9.domain;

import java.util.Random;

public class Enemy {

    private int hitPoints;
    private int baseDamage;

    public Enemy(int hitPoints, int baseDamage) {
        this.hitPoints = hitPoints;
        this.baseDamage = baseDamage;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackDamage() {
        return baseDamage + new Random().nextInt(6);
    }

    public void doDamage(int damage) {
        hitPoints -= damage;
    }

    public boolean isDefeated() {
        return hitPoints <= 0;
    }
}
