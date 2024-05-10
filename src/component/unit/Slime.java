package component.unit;

import component.player.Player;
import javafx.animation.ScaleTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;
import view.GameViewManager;

public class Slime extends BasedUnit {
    public Slime() {
        initialSlime();
        render();
    }

    private void initialSlime() {
        speed = 0.5;
        coin = (int) (Math.random() * 10) + 1;
        imagePath = ClassLoader.getSystemResource("sprite/slime.png").toString();
        position = new Point2D(0, 0);
    }

    private void render() {
        image = new Image(imagePath);

        int i=0, j=0;
        while ((i==0 && j==0) || (j==0 && i==1) || (j==0 && i==3)) {
            i = (int) (Math.random() * 6);
            j = (int) (Math.random() * 4);
        }

        imageView = new ImageView(new WritableImage(image.getPixelReader(), 30 + i*118, 63 + j*180, 84, 73));

        imageView.setFitWidth(GameViewManager.TILE_SIZE / 3.0);
        imageView.setFitHeight(GameViewManager.TILE_SIZE / 3.0 * 0.8);
        imageView.setLayoutX(position.getX());
        imageView.setLayoutY(position.getY());
        imageView.setStyle("-fx-effect: dropshadow(three-pass-box, white, 10, 0, 0, 0);");

        imageView.setOnMouseClicked(e -> {
            Player player = Player.getInstance();
            if (position.distance(Player.getInstance().getPosition().add(Player.getInstance().getPlayerImageView().getFitWidth()/2, Player.getInstance().getPlayerImageView().getFitHeight()/2)) < 100) {
                player.setCoin(player.getCoin() + coin);
                imageView.setVisible(false);
            }
        });

        ScaleTransition transition = new ScaleTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setByX(0.15);
        transition.setByY(0.15);
        transition.setAutoReverse(true);
        transition.setNode(imageView);
        transition.setCycleCount(ScaleTransition.INDEFINITE);
        transition.play();
    }

    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;

        imageView.setLayoutX(position.getX());
        imageView.setLayoutY(position.getY());

        randomDestination();
    }

    public void walk() {
        if (position.distance(Player.getInstance().getPosition().add(Player.getInstance().getPlayerImageView().getFitWidth()/2, Player.getInstance().getPlayerImageView().getFitHeight()/2)) < 100) {
            imageView.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
        } else {
            imageView.setStyle("-fx-effect: dropshadow(three-pass-box, white, 10, 0, 0, 0);");
        }
        if (position.distance(destination) < 5) randomDestination();

        double dx = destination.getX() - position.getX();
        double dy = destination.getY() - position.getY();

        int directionX = dx > 0 ? 1 : -1;
        int directionY = dy > 0 ? 1 : -1;

        position = position.add(directionX*speed, directionY*speed);
        imageView.setLayoutX(position.getX());
        imageView.setLayoutY(position.getY());
    }
}
