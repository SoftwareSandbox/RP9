package com.swsb.rp9.overworld.view;

import com.swsb.rp9.domain.api.OverworldState;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.Position.position;
import static com.swsb.rp9.overworld.domain.RectangleBuilder.rectangle;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.rgb;

public class MenuView {
    private final Label characterNameLabel;
    private final Label hitPointsLabel;
    private boolean visible;
    private Group view;
    private OverworldState restrictedState;

    public MenuView(OverworldState restrictedState) {
        this.restrictedState = restrictedState;
        this.characterNameLabel = createCharacterNameLabel();
        this.hitPointsLabel = createHitPointsLabel();
        this.view = new Group(rectangle()
                .dimension(rectangle(580, 420))
                .color(rgb(192, 192, 192, 0.5))
                .strokeColor(BLACK)
                .position(position(30, 30))
                .build(),
                characterNameLabel,
                hitPointsLabel,
                createMenuLabel()

        );
        this.visible = false;
    }

    private Label createMenuLabel() {
        Label label = new Label("~~~~MENU~~~~~");
        label.setTextFill(Color.WHITE);
        label.setTranslateX(260);
        label.setTranslateY(410);
        return label;
    }

    private Label createCharacterNameLabel() {
        Label label = new Label(restrictedState.getCharacterName());
        label.setTextFill(Color.WHITE);
        label.setTranslateX(30);
        label.setTranslateY(30);
        return label;
    }

    private Label createHitPointsLabel() {
        Label label = new Label("HP: " + restrictedState.getHitPoints());
        label.setTextFill(Color.WHITE);
        label.setLayoutY(40);
        label.setTranslateX(30);
        label.setTranslateY(30);
        return label;
    }

    public Group getView() {
        return view;
    }

    public void redraw() {
        view.setVisible(visible);
        characterNameLabel.setText(restrictedState.getCharacterName());
        hitPointsLabel.setText("HP: " + restrictedState.getHitPoints());
    }

    public void onKeyPressed() {
        visible = !visible;
    }

    public boolean isVisible() {
        return visible;
    }
}
