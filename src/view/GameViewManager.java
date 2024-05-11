package view;

import component.player.Player;
import component.unit.Slime;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import subscene.InventorySubScene;
import subscene.MarketSubScene;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
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

    public GameViewManager() {
        initialGameStage();
    }

    private void initialGameStage() {
        slimes = new ArrayList<>();

        createGameStage();
        createGameLoop();
        createListeners();
        loadResources();
        renderTile();
        renderSlime(30);

        createSubScenes();
        gamePane.getChildren().add(player.getPlayerImageView());
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
            File gameMap = new File("src/map/map.txt");
            Scanner fileReader = new Scanner(gameMap);

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
                    imageView.setFitWidth(TILE_SIZE);
                    imageView.setFitHeight(TILE_SIZE);
                    imageView.setLayoutX(j*TILE_SIZE);
                    imageView.setLayoutY(i*TILE_SIZE);
                    gamePane.getChildren().add(imageView);
                    j++;
                }
                i++;
            }
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
        inventorySubScene = new InventorySubScene();
        gamePane.getChildren().add(inventorySubScene);
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
                inventorySubScene.updateStats();
                updateCamera();
                updateSlime();
            }
        };
        gameTimer.start();
    }

    private void updateSlime() {
        for (Slime slime : slimes) {
            slime.walk();
        }

        gamePane.getChildren().removeIf(e -> {
            return !(e.isVisible());
        });

        slimes.removeIf(e -> {
            return !e.getImageView().isVisible();
        });
    }

    private void updateCamera() {
        inventorySubScene.setLayoutX(player.getPlayerImageView().getLayoutX() - 275);
        inventorySubScene.setLayoutY(player.getPlayerImageView().getLayoutY() + 250);
        gamePane.setLayoutX(-player.getPlayerImageView().getLayoutX() + ViewManager.WINDOW_WIDTH/2.0 - TILE_SIZE/2.0);
        gamePane.setLayoutY(-player.getPlayerImageView().getLayoutY() + ViewManager.WINDOW_HEIGHT/2.0 - TILE_SIZE/2.0);
    }

    public static boolean isOutsideGame(Point2D position) {
        if (position.getX() < 0 || position.getX() > GameViewManager.TILE_SIZE * 15) return true;
        return position.getY() < 0 || position.getY() > GameViewManager.TILE_SIZE * 12;
    }
}