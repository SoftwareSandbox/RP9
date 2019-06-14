package com.swsb.rp9.overworld.view;

import com.swsb.rp9.shared.Direction;
import javafx.scene.image.ImageView;

import static com.swsb.rp9.overworld.view.SpriteAnimation.Builder.spriteAnimation;

public class Animations {

    private final SpriteAnimation moveUp;
    private final SpriteAnimation moveDown;
    private final SpriteAnimation moveRight;
    private final SpriteAnimation moveLeft;
    private SpriteAnimation currentAnimation;

    public Animations(ImageView imageView) {
        moveLeft = spriteAnimation()
                .imageView(imageView)
                .startingRow(1)
                .build();
        moveRight = spriteAnimation()
                .imageView(imageView)
                .startingRow(2)
                .build();
        moveDown = spriteAnimation()
                .imageView(imageView)
                .startingRow(0)
                .build();
        moveUp = spriteAnimation()
                .imageView(imageView)
                .startingRow(3)
                .build();
    }

    public void startAnimation(Direction direction){
        currentAnimation = getAnimationFor(direction);
        currentAnimation.play();
    }

    public void stopAnimation(){
        if(currentAnimation != null) {
            currentAnimation.stop();
        }
    }

    private SpriteAnimation getAnimationFor(Direction direction) {
        switch (direction) {
            case UP:
                return moveUp;
            case DOWN:
                return moveDown;
            case LEFT:
                return moveLeft;
            case RIGHT:
                return moveRight;
            default:
                throw new RuntimeException("No animation found for direction " + direction);
        }
    }
}
