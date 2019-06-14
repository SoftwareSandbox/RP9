module basic.overworld {

    exports com.swsb.rp9.basicoverworld.api;

    requires shared;

    provides com.swsb.rp9.basicoverworld.api.OverworldFactory
            with com.swsb.rp9.basicoverworld.WalledOverworldFactory;

}