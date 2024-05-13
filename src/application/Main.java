package application;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import view.ViewManager;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            AudioClip  bgSound = new AudioClip(ClassLoader.getSystemResource("sound/background.mp3").toString());
            ViewManager viewManager = ViewManager.getInstance();

            primaryStage = viewManager.getMainStage();

            primaryStage.show();
            bgSound.setCycleCount(AudioClip.INDEFINITE);
            bgSound.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
