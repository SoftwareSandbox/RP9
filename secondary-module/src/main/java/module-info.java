module secondary.module {
    exports com.swsb.rp9.secondary.module;

    requires javafx.controls;
    requires javafx.fxml;

    // Open sprites and textures to other modules (javafx graphics needs it)
    // For some reason opens ... to javafx.graphics does not seem to work
    // See also https://github.com/javafxports/openjdk-jfx/issues/441
    opens com.swsb.rp9.secondary.module.sprite;
    opens com.swsb.rp9.secondary.module.texture;
    opens com.swsb.rp9.secondary.module.fxml;
}
