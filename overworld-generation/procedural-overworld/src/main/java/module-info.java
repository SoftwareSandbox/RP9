import com.swsb.rp9.proceduralworld.ProceduralOverworldFactory;

module procedural.overworld {

    requires shared;
    requires basic.overworld;

    provides com.swsb.rp9.basicoverworld.api.OverworldFactory
            with ProceduralOverworldFactory;

}