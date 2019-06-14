package com.swsb.rp9.fight;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.Dimension.square;
import static com.swsb.rp9.core.ImageBuilder.image;
import static com.swsb.rp9.core.Position.position;

public class FightView extends GameView {

    private static final Dimension DIMENSIONS = rectangle(640, 480);

    public FightView() {
        super(DIMENSIONS, null);
    }

    @Override
    public void redraw() {

    }

    @Override
    public String getTitle() {
        return "<mortalCombatVoice>Fight!</mortalCombatVoice>";
    }

    @Override
    protected Parent createGuiRootNode() {
        ImageView hero = createHeroView();
        ImageView enemy = createEnemyView();
        return new Group(hero, enemy);
    }

    private ImageView createHeroView() {
        return image()
                    .url(this.getClass().getResource("/com/swsb/rp9/fight/sprites/hero/mercinary_fight_tmp.png").toExternalForm())
                    .startingPosition(position(DIMENSIONS.getWidth() / 4 * 3, DIMENSIONS.getHeight() / 3))
                    .buildView();
    }

    private ImageView createEnemyView() {
        return image()
                    .url(this.getClass().getResource("/com/swsb/rp9/fight/sprites/enemy/wisp.png").toExternalForm())
                    .startingPosition(position(DIMENSIONS.getWidth() / 5, DIMENSIONS.getHeight() / 4))
                    .dimension(square(120))
                    .buildView();
    }

    @Override
    public String getStyleSheetLocation() {
        return null;
    }
}
