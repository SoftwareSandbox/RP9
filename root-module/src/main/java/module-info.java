module root.module {
    requires secondary.module;

    requires spring.boot.autoconfigure;
    requires spring.boot;

    opens com.cegeka.rp9.root.module;
}