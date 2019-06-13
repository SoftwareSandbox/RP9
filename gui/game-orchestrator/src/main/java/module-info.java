module game.orchestrator {

    exports com.swsb.rp9.game.orchestrator.api;

    requires start.menu;
    requires overworld;
    requires credits;
    requires credits.view.api;
    requires character.selection;

    requires javafx.controls;
    requires javafx.fxml;

    opens com.swsb.rp9.game.orchestrator to javafx.graphics;

    uses com.swsb.rp9.credits.view.api.CreditsView;

}