package view;

import component.inventory.Inventory;
import component.player.Player;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        Image img =  new Image(ClassLoader.getSystemResource("cover.jpg").toString());
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(800,600,true,true,true,true));
        Background bGround = new Background(bImg);
        mainPane.setBackground(bGround);
        Text name = new Text("AJ.TOE'S ADVENTURE");
        name.setFont(Font.font("Verdana", FontWeight.BOLD,40));
        name.setFill(Color.WHITE);
        name.setStroke(Color.BLACK);
        name.setLayoutX(170);
        name.setLayoutY(150);
        mainPane.getChildren().add(name);
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
        startButton.setLayoutX(300);
        startButton.setLayoutY(500);
        startButton.setFont(new Font(20));
        startButton.setPrefSize(100,50);
        mainPane.getChildren().add(startButton);

        startButton.setOnAction(e -> {
            Inventory.getInstance().reset();
            Player.getInstance().reset();
            GameViewManager.getInstance().reset();
            GameViewManager.getInstance().createNewGame(mainStage);
        });
    }

    private void createExitButton() {
        exitButton = new Button("Exit");
        exitButton.setLayoutX(450);
        exitButton.setLayoutY(500);
        exitButton.setFont(new Font(20));
        exitButton.setPrefSize(100,50);
        mainPane.getChildren().add(exitButton);

        exitButton.setOnAction(e -> {
            System.exit(0);
        });
    }
}
