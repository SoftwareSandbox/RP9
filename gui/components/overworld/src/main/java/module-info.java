module overworld {

    requires core;

    requires javafx.controls;
    requires javafx.fxml;

    exports com.swsb.rp9.overworld.api;

    opens com.swsb.rp9.overworld.sound;

}
