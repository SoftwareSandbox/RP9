module fight {
    exports com.swsb.rp9.fight.api;

    requires javafx.controls;
    requires javafx.graphics;
    requires core;
    requires domain;

    opens com.swsb.rp9.fight.sound;
}
