package com.swsb.rp9.secondary.module.frondend;

import com.swsb.rp9.secondary.module.api.Direction;
import javafx.scene.image.ImageView;

import static com.swsb.rp9.secondary.module.api.Direction.*;

public class Hero {
    private ImageView view;

    public Hero(ImageView view) {
        this.view = view;
    }

    public ImageView getView() {
        return view;
    }

    public void move(Direction direction) {
        if (direction.equals(DOWN)) {
            view.setY(view.getY() + 40);
        }
        if (direction.equals(RIGHT)) {
            view.setX(view.getX() + 40);
        }
        if (direction.equals(UP)) {
            view.setY(view.getY() - 40);
        }
        if (direction.equals(LEFT)) {
            view.setX(view.getX() - 40);
        }
    }
}
