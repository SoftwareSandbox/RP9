package com.swsb.rp9.start.menu;

import com.swsb.rp9.core.Dimension;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.domain.api.PlaceholderState;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import static com.swsb.rp9.core.Dimension.rectangle;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_ONE;
import static com.swsb.rp9.core.TransitionSlot.TRANSITION_SLOT_TWO;
import static javafx.scene.paint.Color.BLACK;

public class StartMenuDefaultView extends GameView<PlaceholderState> {

    private static final Dimension DIMENSIONS = rectangle(640, 480);

    public StartMenuDefaultView() {
        super(DIMENSIONS, new PlaceholderState());
    }

    @Override
    public String getTitle() {
        return "StartMenu (The default one)";
    }

    @Override
    public void redraw() {
        // for now, nothing to redraw
    }

    @Override
    protected Parent createGuiRootNode() {
        return createStartScreenPane();
    }

    @Override
    public String getStyleSheetLocation() {
        return getClass().getResource("/com/swsb/rp9/start/menu/styles/start-screen.css").toExternalForm();
    }

    private Parent createStartScreenPane() {
        VBox verticalMenu = createToggleMenu();
        TilePane startScreen = new TilePane(verticalMenu);
        startScreen.setBackground(createStartScreenBackground());
        startScreen.setAlignment(Pos.CENTER);
        return startScreen;
    }

    private Background createStartScreenBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/com/swsb/rp9/start/menu/background/glacial_mountains_preview_lightened.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(0, 0, false, false, false, true));
        return new Background(backgroundImage);
    }

    private VBox createToggleMenu() {
        ToggleGroup menuToggleGroup = new ToggleGroup();

        RadioButton newGameButton = new RadioButton("New game");
        newGameButton.setTextFill(BLACK);
        newGameButton.setSelected(true);
        newGameButton.setToggleGroup(menuToggleGroup);
        newGameButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                registerTransitionSlot(TRANSITION_SLOT_ONE);
            }
        });

        RadioButton creditsButton = new RadioButton("Credits");
        creditsButton.setTextFill(BLACK);
        creditsButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                registerTransitionSlot(TRANSITION_SLOT_TWO);
            }
        });

        creditsButton.setToggleGroup(menuToggleGroup);

        return new VBox(newGameButton, creditsButton);
    }

}
