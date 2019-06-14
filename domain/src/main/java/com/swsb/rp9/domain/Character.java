package com.swsb.rp9.domain;

import com.swsb.rp9.domain.api.Coordinate;
import com.swsb.rp9.domain.api.Direction;

public class Character {
    private static final int DEFAULT_HP = 100;
    private int experiencePoints;

    private String name;
    private int hitPoints;
    private Coordinate coordinate;

    public Character() {
        hitPoints = DEFAULT_HP;
        this.coordinate = new Coordinate(1,1);
        this.experiencePoints = 0;
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

    void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void addCandlePoint() {
        experiencePoints++;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }
}
