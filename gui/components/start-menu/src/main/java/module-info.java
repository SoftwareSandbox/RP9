module start.menu {

    exports com.swsb.rp9.start.menu.api;

    requires transitive core;

    requires javafx.controls;
    requires javafx.fxml;

    opens com.swsb.rp9.start.menu.sound;
}
