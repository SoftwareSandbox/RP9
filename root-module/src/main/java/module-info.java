module root.module {
    requires game.orchestrator;

    requires spring.boot.autoconfigure;
    requires spring.boot;

    requires transitive java.sql;

    opens com.swsb.rp9.root.module;
}
