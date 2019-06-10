package com.swsb.rp9.overworld.domain.hero;

import com.swsb.rp9.overworld.domain.Direction;
import javafx.scene.image.ImageView;

import static com.swsb.rp9.overworld.domain.Direction.*;

public class Hero {
    private ImageView view;

    public Hero(ImageView view) {
        this.view = view;
    }

    public ImageView getView() {
        return view;
    }

    private boolean isGrowing;
    public void heroStance() {
        if(isGrowing) {
            if(view.getScaleY() <= 1.05) {
                view.setScaleY(view.getScaleY() + 0.01);
            } else {
                isGrowing = !isGrowing;
            }
        } else {
            if(view.getScaleY() >= 0.95) {
                view.setScaleY(view.getScaleY() - 0.01);
            } else {
                isGrowing = !isGrowing;
            }
        }
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
