package com.swsb.rp9.root.module;

import com.swsb.rp9.secondary.module.World;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        new World().run();
        // or... (new way)
//        GameLauncher.launch();
    }
}
