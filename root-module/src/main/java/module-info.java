module root.module {
    requires secondary.module;
    requires game.orchestrator;

    requires spring.boot.autoconfigure;
    requires spring.boot;

    opens com.swsb.rp9.root.module;
}
