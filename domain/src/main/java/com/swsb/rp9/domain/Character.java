package com.swsb.rp9.domain;

import com.swsb.rp9.domain.api.CharacterType;
import com.swsb.rp9.shared.Coordinate;
import com.swsb.rp9.shared.Direction;

import java.util.Random;

import static com.swsb.rp9.domain.api.CharacterType.MERCENARY;

public class Character {
    private static final int DEFAULT_HP = 100;
    private int experiencePoints;
    private static final int DEFAULT_BASEDAMAGE = 15;

    private String name;
    private int hitPoints;
    private int maxHitPoints;
    private int baseDamage;
    private Coordinate coordinate;
    private CharacterType characterType;
    private boolean typeChanged;

    public Character() {
        hitPoints = DEFAULT_HP;
        maxHitPoints = hitPoints;
        baseDamage = DEFAULT_BASEDAMAGE;
        this.coordinate = new Coordinate(1,1);
        this.experiencePoints = 0;
        this.characterType = MERCENARY;
        this.typeChanged = true;
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

    public int getMaxHitPoints() {
        return maxHitPoints;
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

    public void addCandlePoint() {
        experiencePoints++;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setType(CharacterType characterType) {
        this.characterType = characterType;
        typeChanged = true;
    }

    public CharacterType getCharacterType() {
        typeChanged = false;
        return characterType;
    }

    public boolean isTypeChanged() {
        return typeChanged;
    }
}
