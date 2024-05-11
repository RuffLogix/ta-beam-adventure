package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class ViewManager {
    public final static int WINDOW_WIDTH = 800;
    public final static int WINDOW_HEIGHT = 600;
    private AnchorPane mainPane;
    private Stage mainStage;
    private Scene mainScene;
    private Button startButton;
    private Button exitButton;
    private static ViewManager instance;

    public static ViewManager getInstance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    private ViewManager() {
        createMainStage();
        createButtons();
    }

    private void createMainStage() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        mainStage = new Stage();
        mainStage.setResizable(false);
        mainStage.setScene(mainScene);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createButtons() {
        createStartButton();
        createExitButton();
    }

    private void createStartButton() {
        startButton = new Button("Start");
        startButton.setLayoutX(400);
        startButton.setLayoutY(300);
        mainPane.getChildren().add(startButton);

        startButton.setOnAction(e -> {
            GameViewManager.getInstance().createNewGame(mainStage);
        });
    }

    private void createExitButton() {
        exitButton = new Button("Exit");
        exitButton.setLayoutX(400);
        exitButton.setLayoutY(350);
        mainPane.getChildren().add(exitButton);

        exitButton.setOnAction(e -> {
            System.exit(0);
        });
    }
}
