import com.swsb.rp9.CreditsDefaultView;
import com.swsb.rp9.credits.api.CreditsView;

module credits {

    requires core;

    requires javafx.controls;
    requires javafx.fxml;

    exports com.swsb.rp9.credits.api;

    provides CreditsView
            with CreditsDefaultView;
}