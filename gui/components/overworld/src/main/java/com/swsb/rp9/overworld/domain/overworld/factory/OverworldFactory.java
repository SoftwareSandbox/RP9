package com.swsb.rp9.overworld.domain.overworld.factory;

import com.swsb.rp9.overworld.domain.overworld.Overworld;

public interface OverworldFactory {

    Overworld createOverworld(int width, int height);
}
