package com.swsb.rp9.domain;

import com.swsb.rp9.domain.api.Coordinate;
import com.swsb.rp9.domain.api.Direction;

import java.util.Random;

public class Character {
    private static final int DEFAULT_HP = 100;
    private static final int DEFAULT_BASEDAMAGE = 15;

    private String name;
    private int hitPoints;
    private int baseDamage;
    private Coordinate coordinate;

    public Character() {
        hitPoints = DEFAULT_HP;
        baseDamage = DEFAULT_BASEDAMAGE;
        this.coordinate = new Coordinate(1,1);
    }

    public void move(Direction direction) {
        this.coordinate = coordinate.neighbourInDirection(direction);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
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

    int getAttackDamage() {
        return baseDamage + new Random().nextInt(10);
    }

    void takeDamage(int damage) {
        hitPoints -= damage;
    }

    boolean isDefeated() {
        return hitPoints <= 0;
    }
}
