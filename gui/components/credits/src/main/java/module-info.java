import com.swsb.rp9.CreditsDefaultView;

module credits {

    requires core;
    requires domain;

    requires javafx.controls;
    requires javafx.fxml;

    exports com.swsb.rp9.credits.api;

    provides com.swsb.rp9.credits.api.CreditsView
            with CreditsDefaultView;
}