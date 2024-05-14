package view;

import component.bossfight.BossFight;
import component.player.Player;
import component.unit.Slime;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import subscene.InventorySubScene;
import subscene.MarketSubScene;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DayNightLight;
import utils.TileRenderer;

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
    public final static int TILE_SIZE = 100;
    private Player player = Player.getInstance();
    private ArrayList<Slime> slimes;
    DayNightLight dayNightLight;
    private static GameViewManager instance;
    private Text playerLevel;

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
        renderTile();
        createDayNightLight();

        createSubScenes();
        createTeleport();
        renderSlime(Math.max((int) (Math.random() * 50), 20));
        playerLevel = new Text();
        playerLevel.setText("Level: " + Player.level);
        playerLevel.setFill(Color.WHITE);

        gamePane.getChildren().addAll(player.getPlayerImageView(), playerLevel);
    }

    private void createDayNightLight() {
        dayNightLight = new DayNightLight();
        gamePane.getChildren().add(dayNightLight);
    }

    public void renderSlime(int n) {
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
                    Image image = TileRenderer.getTile(c);
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
            if (e.getCharacter().equals("w")) {
                if (!marketSubScene.isVisible()) player.setVerticalDirection(-1);
            } else if (e.getCharacter().equals("s")) {
                if (!marketSubScene.isVisible()) player.setVerticalDirection(1);
            } else if (e.getCharacter().equals("a")) {
                if (!marketSubScene.isVisible()) player.setHorizontalDirection(-1);
            } else if (e.getCharacter().equals("d")) {
                if (!marketSubScene.isVisible()) player.setHorizontalDirection(1);
            } else if (e.getCharacter().equals("1")) {
                if (Player.getInstance().getArmor() != null) Player.getInstance().getArmor().upgrade(true);
            } else if (e.getCharacter().equals("2")) {
                if (Player.getInstance().getWeapon() != null) Player.getInstance().getWeapon().upgrade(true);
            } else if (e.getCharacter().equals("3")) {
                if (Player.getInstance().getAmulet() != null) Player.getInstance().getAmulet().upgrade(true);
            } else if (e.getCharacter().equals("m")) {
                marketSubScene.toggle();
            }
        });

        gameScene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.S) player.setVerticalDirection(0);
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.D) player.setHorizontalDirection(0);
        });
    }

    private void createGameStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, ViewManager.WINDOW_WIDTH, ViewManager.WINDOW_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setTitle("TA.Beam's Adventure");
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

    private void createTeleport() {
        Image image = new Image(ClassLoader.getSystemResource("sprite/teleport.png").toString());
        ImageView imageView = new ImageView(image);
        imageView.setOpacity(0.5);

        imageView.setLayoutX(1150);
        imageView.setLayoutY(-125);

        imageView.setOnMouseEntered(e -> {
            imageView.setStyle("-fx-effect: dropshadow(three-pass-box, white, 10, 0, 0, 0);");
        });

        imageView.setOnMouseExited(e -> {
            imageView.setStyle("");
        });

        Media sound = new Media(ClassLoader.getSystemResource("sound/teleport.mp3").toString());
        MediaPlayer teleportSound = new MediaPlayer(sound);
        teleportSound.setCycleCount(-1);
        imageView.setOnMouseClicked(e -> {
            try {
                teleportSound.play();
                gameStage.hide();
                BossFight.getInstance();
                BossFight.getMainStage().show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        gamePane.getChildren().add(imageView);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if ((int) (now/100000000) % 1000 == 0) dayNightLight.toDay();
                if ((int) (now/100000000) % 1000 == 500) dayNightLight.toNight();
                player.playerAnimation((int) (now/100000000));
                player.update();
                updateCamera();
                inventorySubScene.updateStats();
                updateSlime();
                marketSubScene.toFront();
                inventorySubScene.toFront();
                playerLevel.setText("Level: " + Player.level);
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
            marketSubScene.setLayoutY(player.getPlayerImageView().getLayoutY() - 200);
        }

        playerLevel.setLayoutX(player.getPlayerImageView().getLayoutX() + 35);
        playerLevel.setLayoutY(player.getPlayerImageView().getLayoutY() - 10);
    }

    public static boolean isOutsideGame(Point2D position) {
        if (position.getX() < 0 || position.getX() > GameViewManager.TILE_SIZE * 15) return true;
        return position.getY() < 0 || position.getY() > GameViewManager.TILE_SIZE * 12;
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public void reset() {
        gameTimer.stop();
        instance = null;
    }
}
