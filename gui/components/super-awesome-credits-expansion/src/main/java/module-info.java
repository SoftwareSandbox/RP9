module superawesome.credits.expansion {

    requires core;

    requires javafx.controls;
    requires javafx.fxml;

    requires credits;

    provides com.swsb.rp9.credits.api.CreditsView
            with com.swsb.rp9.credits.AwesomeCreditsView;
}
