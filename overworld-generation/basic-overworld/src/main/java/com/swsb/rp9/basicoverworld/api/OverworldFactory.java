package com.swsb.rp9.basicoverworld.api;

import com.swsb.rp9.shared.OverworldFactoryResult;

public interface OverworldFactory {

    OverworldFactoryResult createOverworld(int width, int height);

    int getLoadOrder();
}
