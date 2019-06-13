package com.swsb.rp9.start.menu.api;

import com.swsb.rp9.core.GameScene;
import com.swsb.rp9.core.GameView;
import com.swsb.rp9.start.menu.StartMenuDefaultView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class StartMenuScene extends GameScene {

    public StartMenuScene() {
        this(null);
    }

    private final MediaPlayer mediaPlayer;

    private StartMenuScene(GameView gameView) {
        super(gameView);
        mediaPlayer = createMediaPlayer();
    }

    @Override
    protected GameView createDefaultGameView() {
        return new StartMenuDefaultView();
    }

    private MediaPlayer createMediaPlayer() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource("/com/swsb/rp9/start/menu/sound/Peter_Batemon-Fantastic_Space.mp3").toExternalForm()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        return mediaPlayer;
    }

    @Override
    protected void onTransitionToOtherScene() {
        super.onTransitionToOtherScene();
        mediaPlayer.stop();
    }
}
