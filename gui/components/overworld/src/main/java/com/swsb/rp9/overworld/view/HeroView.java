package com.swsb.rp9.overworld.view;

import com.swsb.rp9.overworld.domain.Position;
import com.swsb.rp9.overworld.domain.hero.Hero;
import javafx.scene.image.ImageView;

import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.overworld.OverworldDefaultView.RECTANGLE_SIZE;
import static com.swsb.rp9.overworld.OverworldDefaultView.toPosition;
import static com.swsb.rp9.overworld.domain.ImageBuilder.image;

public class HeroView {
    private ImageView imageView;
    private Hero hero;
    private boolean isGrowing;

    public HeroView(Hero hero) {
        this.hero = hero;
        this.imageView = image()
                .url("com/swsb/rp9/overworld/sprites/hero/hero.png")
                .dimension(square(RECTANGLE_SIZE))
                .buildView();
    }


    public ImageView getView() {
        return imageView;
    }

    public void redraw() {
        growHero();
        positionHero();
    }

    private void growHero() {
        if(isGrowing) {
            if(imageView.getScaleY() <= 1.05) {
                imageView.setScaleY(imageView.getScaleY() + 0.01);
            } else {
                isGrowing = !isGrowing;
            }
        } else {
            if(imageView.getScaleY() >= 0.95) {
                imageView.setScaleY(imageView.getScaleY() - 0.01);
            } else {
                isGrowing = !isGrowing;
            }
        }
    }

    private void positionHero() {
        Position position = toPosition(hero.getCoordinate());
        imageView.setX(position.getX());
        imageView.setY(position.getY());
    }
}
