module superawesome.credits.expansion {

    requires core;

    requires javafx.controls;
    requires javafx.fxml;

    requires credits.view.api;

    provides com.swsb.rp9.credits.view.api.CreditsView
            with com.swsb.rp9.credits.AwesomeCreditsView;
}
