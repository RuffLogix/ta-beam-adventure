package component.player;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import view.GameViewManager;
import view.ViewManager;

public class Player {
    private Point2D position;
    private Image playerImage;
    private ImageView playerImageView;
    private String playerResourcePath;
    private int horizontalDirection;
    private int verticalDirection;
    private WritableImage[] walkUp;
    private WritableImage[] walkDown;
    private WritableImage[] walkLeft;
    private WritableImage[] walkRight;
    private int direction;
    private int playerSpeed;
    private int coin;
    private static Player instance;
    public static final int MAX_HP = 100;

    private int hp;

    public static Player getInstance() {
        if (instance == null) instance = new Player();
        return instance;
    }

    private Player() {
        initialPlayer();
        loadResource();
        render();
    }

    private void loadResource() {
        horizontalDirection = 0;
        verticalDirection = 0;
        playerResourcePath = ClassLoader.getSystemResource("sprite/player.png").toString();
    }

    private void initialPlayer() {
        hp = MAX_HP;
        playerSpeed = 5;
        position = new Point2D(ViewManager.WINDOW_WIDTH / 2.0, ViewManager.WINDOW_HEIGHT / 2.0);
    }

    private void render() {
        playerImage = new Image(playerResourcePath);

        walkUp = new WritableImage[3];
        walkDown = new WritableImage[3];
        walkLeft = new WritableImage[3];
        walkRight = new WritableImage[3];

        for (int i=0; i<3; i++) walkDown[i] = new WritableImage(playerImage.getPixelReader(), i*322, 0, 322, 322);
        for (int i=0; i<3; i++) walkLeft[i] = new WritableImage(playerImage.getPixelReader(), i*322, 322, 322, 322);
        for (int i=0; i<3; i++) walkRight[i] = new WritableImage(playerImage.getPixelReader(), i*322, 2*322, 322, 322);
        for (int i=0; i<3; i++) walkUp[i] = new WritableImage(playerImage.getPixelReader(), i*322, 3*322, 322, 322);

        playerImageView = new ImageView(walkDown[0]);
        playerImageView.setFitWidth(GameViewManager.TILE_SIZE);
        playerImageView.setFitHeight(GameViewManager.TILE_SIZE);
        playerImageView.setLayoutX(position.getX());
        playerImageView.setLayoutY(position.getY());
    }

    public void update() {
        if (GameViewManager.isOutsideGame(position.add(horizontalDirection * playerSpeed, verticalDirection * playerSpeed))) return;

        position = position.add(horizontalDirection*playerSpeed, verticalDirection*playerSpeed);
        playerImageView.setLayoutX(position.getX());
        playerImageView.setLayoutY(position.getY());

        if (horizontalDirection > 0) direction = 1;
        else if (horizontalDirection < 0) direction = 2;
        else if (verticalDirection > 0) direction = 3;
        else if (verticalDirection < 0) direction = 0;
    }

    public void playerAnimation(int spriteIndex) {
        if (horizontalDirection == 0 && verticalDirection == 0) return;

        spriteIndex = spriteIndex % 3;
        switch (direction) {
            case 0 -> playerImageView.setImage(walkUp[spriteIndex]);
            case 1 -> playerImageView.setImage(walkRight[spriteIndex]);
            case 2 -> playerImageView.setImage(walkLeft[spriteIndex]);
            case 3 -> playerImageView.setImage(walkDown[spriteIndex]);
        }
    }

    public ImageView getPlayerImageView() {
        return playerImageView;
    }

    public void setHorizontalDirection(int horizontalDirection) {
        this.horizontalDirection = horizontalDirection;
    }

    public void setVerticalDirection(int verticalDirection) {
        this.verticalDirection = verticalDirection;
    }

    public void setCoin(int coin) {
        coin = Math.max(0, coin);
        this.coin = coin;
    }

    public int getCoin() {
        return coin;
    }

    public Point2D getPosition() {
        return position;
    }

    public int getHp() {
        return hp;
    }

    public void heal(int amount) {
        hp = Math.min(hp + amount, MAX_HP);
    }
}
