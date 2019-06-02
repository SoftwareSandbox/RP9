package com.swsb.rp9.secondary.module.frondend;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.*;

public class Hero {
    private ImageView view;

    public Hero(ImageView view) {
        this.view = view;
    }

    public EventHandler<KeyEvent> onKeyPressed(){
        return event -> {
            if (event.getCode().equals(DOWN)) {
                view.setY(view.getY() + 40);
            }
            if (event.getCode().equals(RIGHT)) {
                view.setX(view.getX() + 40);
            }
            if (event.getCode().equals(UP)) {
                view.setY(view.getY() - 40);
            }
            if (event.getCode().equals(LEFT)) {
                view.setX(view.getX() - 40);
            }
        };
    }

    public ImageView getView() {
        return view;
    }
}
