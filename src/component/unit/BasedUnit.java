package component.unit;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.GameViewManager;

public abstract class BasedUnit {
    protected Point2D position;
    protected Image image;
    protected ImageView imageView;
    protected Point2D destination;
    protected String imagePath;
    protected double speed;
    protected int coin;

    public BasedUnit() { }

    public BasedUnit(String imagePath) {
        initialUnit(imagePath);
    }

    private void initialUnit(String imagePath) {
        image = new Image(imagePath);
        imageView = new ImageView(image);
        position = new Point2D(0, 0);
    }

    public abstract void setPosition(Point2D position);
    public void randomDestination() {
        Point2D newDestination = position.add(Math.random() * 100 - 50, Math.random() * 100 - 50);
        while (GameViewManager.isOutsideGame(newDestination)) newDestination = position.add(Math.random() * 100 - 50, Math.random() * 100 - 50);
        destination = newDestination;
    }
}
