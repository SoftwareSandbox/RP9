package com.swsb.rp9.fight;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import static com.swsb.rp9.core.Dimension.rectangle;

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
        TilePane characterScreen = new TilePane();
        characterScreen.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        characterScreen.setAlignment(Pos.CENTER);
        return characterScreen;
    }

    @Override
    public String getStyleSheetLocation() {
        return null;
    }
}
