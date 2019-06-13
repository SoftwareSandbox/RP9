package com.swsb.rp9.overworld.view;

import com.swsb.rp9.overworld.domain.Position;
import com.swsb.rp9.overworld.domain.hero.Hero;
import javafx.scene.image.ImageView;

import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.overworld.OverworldDefaultView.RECTANGLE_SIZE;
import static com.swsb.rp9.overworld.OverworldDefaultView.toPosition;
import static com.swsb.rp9.overworld.domain.ImageBuilder.image;

public class HeroView {
    private static final int HERO_OFFSET = 7;
    public static final int NUMBER_OF_FRAMES_NEEDED_FOR_MOVE = 20; //NEEDS TO BE A DIVIDER OF 40 OR THINGS WILL BREAK, I THINK, MAYBE NOT KNOW I THINK ABOUT IT
    private Position position;
    private ImageView imageView;
    private Hero hero;
    private boolean isGrowing;

    public HeroView(Hero hero) {
        this.hero = hero;
        this.position = toPosition(hero.getCoordinate());
        this.imageView = image()
                .url(getClass().getResource("/com/swsb/rp9/overworld/sprites/hero/hero.png").toExternalForm())
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
        if (isGrowing) {
            if (imageView.getScaleY() <= 1.05) {
                imageView.setScaleY(imageView.getScaleY() + 0.01);
            } else {
                isGrowing = !isGrowing;
            }
        } else {
            if (imageView.getScaleY() >= 0.95) {
                imageView.setScaleY(imageView.getScaleY() - 0.01);
            } else {
                isGrowing = !isGrowing;
            }
        }
    }

    private void positionHero() {
        Position positionToMoveTo = toPosition(hero.getCoordinate());
        if (!positionToMoveTo.equals(position)) {
            position = position.move(position.difference(positionToMoveTo).limit(RECTANGLE_SIZE / NUMBER_OF_FRAMES_NEEDED_FOR_MOVE));
        }
        imageView.setX(position.getX() + HERO_OFFSET);
        imageView.setY(position.getY());
    }
}
