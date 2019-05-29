package com.cegeka.rp9.root.module;

import com.cegeka.rp9.secondary.module.World;

public class Application {

    public static void main(String[] args) {
        var worldMessage = new World().getWorldMessage();
        System.out.println("Hello " + worldMessage);
    }

}
