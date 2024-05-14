package application;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import view.ViewManager;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            ViewManager viewManager = ViewManager.getInstance();
            Media media = new Media(ClassLoader.getSystemResource("sound/background.mp3").toString());
            MediaPlayer  bgSound = new MediaPlayer(media);
            primaryStage = viewManager.getMainStage();

            primaryStage.show();
            bgSound.setCycleCount(MediaPlayer.INDEFINITE);
            bgSound.setOnEndOfMedia(bgSound::play);
            bgSound.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
