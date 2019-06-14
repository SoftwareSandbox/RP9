import com.swsb.rp9.proceduralworld.RandomOverworldFactory;

module procedural.overworld {

    requires shared;
    requires basic.overworld;

    provides com.swsb.rp9.basicoverworld.api.OverworldFactory
            with RandomOverworldFactory;

}