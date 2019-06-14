package com.swsb.rp9.overworld.view;

import com.swsb.rp9.core.Position;
import com.swsb.rp9.overworld.domain.hero.Hero;
import javafx.scene.image.ImageView;

import static com.swsb.rp9.overworld.OverworldDefaultView.RECTANGLE_SIZE;
import static com.swsb.rp9.overworld.OverworldDefaultView.toPosition;
import static com.swsb.rp9.core.Direction.*;
import static com.swsb.rp9.core.ImageBuilder.image;

public class HeroView {
    private static final int HERO_OFFSET = -15;
    public static final int NUMBER_OF_FRAMES_NEEDED_FOR_MOVE = 20; //NEEDS TO BE A DIVIDER OF 40 OR THINGS WILL BREAK, I THINK, MAYBE NOT NOW I THINK ABOUT IT
    private Position position;
    private ImageView imageView;
    private Hero hero;
    private boolean isGrowing;

    private final Animations animations;

    public HeroView(Hero hero) {
        this.hero = hero;
        this.position = toPosition(hero.getCoordinate());
        this.imageView = image()
                .url(getClass().getResource("/com/swsb/rp9/overworld/sprites/hero/Ouzo-the-Wolf-Bard.png").toExternalForm())
                .buildView();
        this.animations = new Animations(imageView);
        animations.startAnimation(RIGHT);
    }

    public ImageView getView() {
        return imageView;
    }

    public void redraw() {
//        growHero();
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
            Position delta = position.difference(positionToMoveTo).limit(RECTANGLE_SIZE / NUMBER_OF_FRAMES_NEEDED_FOR_MOVE);
            position = position.move(delta);
            animations.startAnimation(delta.getDirection());
        } else {
            animations.stopAnimation();
        }
        imageView.setX(position.getX() + HERO_OFFSET);
        imageView.setY(position.getY() + HERO_OFFSET);
    }
}
