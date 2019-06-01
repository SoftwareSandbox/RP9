module root.module {
    requires secondary.module;

    requires spring.boot.autoconfigure;
    requires spring.boot;

    opens com.swsb.rp9.root.module;
}
