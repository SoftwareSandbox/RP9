package com.swsb.rp9.domain.overworld.factory;

import com.swsb.rp9.domain.Character;
import com.swsb.rp9.domain.overworld.Overworld;

public interface OverworldFactory {

    Overworld createOverworld(int width, int height, Character character);
}
