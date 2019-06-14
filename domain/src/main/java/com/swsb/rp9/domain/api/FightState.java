package com.swsb.rp9.domain.api;

import com.swsb.rp9.domain.Enemy;

import java.util.Random;

public class FightState extends RestrictedState {

    private Enemy enemy;

    public FightState() {
        enemy = spawnEnemy();
    }

    private Enemy spawnEnemy() {
        return new Enemy(100 + new Random().nextInt(100), 10);
    }

    public int getHeroHitpoints() {
        return getGameState().getHitPoints();
    }

    public int getHeroMaxHitpoints() {
        return getGameState().getMaxHitPoints();
    }

    public int getEnemyHitpoints() {
        return enemy.getHitPoints();
    }

    public int getEnemyMaxHitpoints() {
        return enemy.getMaxHitPoints();
    }

    public boolean isEnemyDefeated() {
        return enemy.isDefeated();
    }

    public boolean isHeroDefeated() {
        return getGameState().isHeroDefeated();
    }

    public void heroDamagesEnemy() {
        enemy.doDamage(getGameState().getHeroDamage());
    }

    public void enemyDamagesHero() {
        getGameState().heroTakesDamage(enemy.getAttackDamage());
    }

    public void resetEnemy() {
        enemy = spawnEnemy();
    }

    public CharacterType getCharacterType() {
        return getGameState().getCharacterType();
    }

    public boolean hasCharacterTypeChanged() {
        return getGameState().isCharacterTypeChanged();
    }
}
