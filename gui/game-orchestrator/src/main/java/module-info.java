import com.swsb.rp9.credits.api.CreditsView;

module game.orchestrator {

    exports com.swsb.rp9.game.orchestrator.api;

    requires start.menu;
    requires character.selection;
    requires fight;
    requires overworld;
    requires credits;

    requires javafx.controls;
    requires javafx.fxml;

    opens com.swsb.rp9.game.orchestrator to javafx.graphics;

    uses CreditsView;

}
