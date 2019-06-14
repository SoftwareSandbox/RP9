package com.swsb.rp9.overworld.view;

import com.swsb.rp9.core.Position;
import com.swsb.rp9.domain.api.OverworldState;
import javafx.scene.image.ImageView;

import static com.swsb.rp9.core.ImageBuilder.image;
import static com.swsb.rp9.overworld.OverworldDefaultView.RECTANGLE_SIZE;
import static com.swsb.rp9.overworld.OverworldDefaultView.toPosition;
import static com.swsb.rp9.shared.Direction.RIGHT;

public class CharacterView {
    private static final int HERO_OFFSET = -20;
    public static final int NUMBER_OF_FRAMES_NEEDED_FOR_MOVE = 20; //NEEDS TO BE A DIVIDER OF 40 OR THINGS WILL BREAK, I THINK, MAYBE NOT NOW I THINK ABOUT IT
    private final OverworldState overworldState;
    private Position position;
    private ImageView imageView;
    private boolean isGrowing;

    private final Animations animations;

    public CharacterView(OverworldState overworldState) {
        this.overworldState = overworldState;
        this.position = toPosition(overworldState.getHeroCoordinate());
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
        Position positionToMoveTo = toPosition(overworldState.getHeroCoordinate());
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
