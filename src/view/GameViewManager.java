package view;

import component.player.Player;
import component.unit.Slime;
import javafx.animation.FadeTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import subscene.InventorySubScene;
import subscene.MarketSubScene;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DayNightLight;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GameViewManager {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private AnimationTimer gameTimer;
    private Stage mainStage;
    private MarketSubScene marketSubScene;
    private InventorySubScene inventorySubScene = InventorySubScene.getInstance();
    private String stoneResourcePath;
    private String grass1ResourcePath;
    private String grass2ResourcePath;
    public final static int TILE_SIZE = 100;
    private Player player = Player.getInstance();
    private ArrayList<Slime> slimes;
    DayNightLight dayNightLight;
    private static GameViewManager instance;

    public static GameViewManager getInstance() {
        if (instance == null) {
            instance = new GameViewManager();
        }
        return instance;
    }

    private GameViewManager() {
        initialGameStage();
    }

    private void initialGameStage() {
        slimes = new ArrayList<>();

        createGameStage();
        createGameLoop();
        createListeners();
        loadResources();
        renderTile();
        createDayNightLight();
        renderSlime(Math.max((int) (Math.random() * 50), 20));

        createSubScenes();
        gamePane.getChildren().add(player.getPlayerImageView());
    }

    private void createDayNightLight() {
        dayNightLight = new DayNightLight();
        gamePane.getChildren().add(dayNightLight);
    }

    private void loadResources() {
        stoneResourcePath = ClassLoader.getSystemResource("material/stone.jpg").toString();
        grass1ResourcePath = ClassLoader.getSystemResource("material/grass1.png").toString();
        grass2ResourcePath = ClassLoader.getSystemResource("material/grass2.jpg").toString();
    }

    private void renderSlime(int n) {
        for (int i=0; i<n; i++) {
            Slime slime = new Slime();
            slime.setPosition(new Point2D(Math.random() * 1500, Math.random() * 1200));
            slimes.add(slime);
            gamePane.getChildren().add(slime.getImageView());
        }
    }

    private void renderTile() {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("map/map.txt");
            if (inputStream == null) return ;
            Scanner fileReader = new Scanner(inputStream);

            int i=0;
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                int j=0;
                for (char c : data.toCharArray()) {
                    if (c==' ') continue;

                    Image image = switch (c) {
                        case '0' -> new Image(stoneResourcePath);
                        case '1' -> new Image(grass1ResourcePath);
                        case '2' -> new Image(grass2ResourcePath);
                        default -> new Image(stoneResourcePath);
                    };

                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(TILE_SIZE/2);
                    imageView.setFitHeight(TILE_SIZE/2);
                    imageView.setLayoutX(j*TILE_SIZE/2);
                    imageView.setLayoutY(i*TILE_SIZE/2);
                    gamePane.getChildren().add(imageView);
                    j++;
                }
                i++;
            }

            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createListeners() {
        gameScene.setOnKeyTyped(e -> {
            switch (e.getCharacter()) {
                case "w" -> player.setVerticalDirection(-1);
                case "s" -> player.setVerticalDirection(1);
                case "a" -> player.setHorizontalDirection(-1);
                case "d" -> player.setHorizontalDirection(1);
               
            }
        });

        gameScene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W, S -> player.setVerticalDirection(0);
                case D, A -> player.setHorizontalDirection(0);
            }
        });
    }

    private void createGameStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, ViewManager.WINDOW_WIDTH, ViewManager.WINDOW_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    private void createSubScenes() {
        marketSubScene = new MarketSubScene();
        inventorySubScene = new InventorySubScene();
        gamePane.getChildren().addAll(marketSubScene, inventorySubScene);
    }

    public void createNewGame(Stage mainStage) {
        this.mainStage = mainStage;
        mainStage.hide();
        gameStage.show();
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                player.playerAnimation((int) (now/100000000));
                player.update();
                updateCamera();
                inventorySubScene.updateStats();
                updateSlime();
                marketSubScene.toFront();
                inventorySubScene.toFront();
            }
        };
        gameTimer.start();
    }

    private void updateSlime() {
        for (Slime slime : slimes) {
            slime.walk();
        }

        gamePane.getChildren().removeIf(e -> {
            return !(e.isVisible()) && (e!=marketSubScene);
        });

        slimes.removeIf(e -> {
            return !e.getImageView().isVisible();
        });
    }

    private void updateCamera() {
        if (player.getPlayerImageView().getLayoutX() < 350) {
            gamePane.setLayoutX(0);
            inventorySubScene.setLayoutX(75);
        } else if (player.getPlayerImageView().getLayoutX() > 1150) {
            gamePane.setLayoutX(-800);
            inventorySubScene.setLayoutX(800 + 75);
        } else {
            gamePane.setLayoutX(-player.getPlayerImageView().getLayoutX() + ViewManager.WINDOW_WIDTH/2.0 - TILE_SIZE/2.0);
            inventorySubScene.setLayoutX(player.getPlayerImageView().getLayoutX() - 275);
            marketSubScene.setLayoutX(player.getPlayerImageView().getLayoutX() - 175);
        }

        if (player.getPlayerImageView().getLayoutY() < 250) {
            gamePane.setLayoutY(0);
        } else if (player.getPlayerImageView().getLayoutY() > 950) {
            gamePane.setLayoutY(-700);
        } else {
            gamePane.setLayoutY(-player.getPlayerImageView().getLayoutY() + ViewManager.WINDOW_HEIGHT/2.0 - TILE_SIZE/2.0);
            inventorySubScene.setLayoutY(player.getPlayerImageView().getLayoutY() + 250);
            marketSubScene.setLayoutY(player.getPlayerImageView().getLayoutY() - 250);
        }
    }

    public static boolean isOutsideGame(Point2D position) {
        if (position.getX() < 0 || position.getX() > GameViewManager.TILE_SIZE * 15) return true;
        return position.getY() < 0 || position.getY() > GameViewManager.TILE_SIZE * 12;
    }

    public Stage getGameStage() {
        return gameStage;
    }
}
