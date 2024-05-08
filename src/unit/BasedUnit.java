package unit;

import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;

public abstract class BasedUnit {
    private int hp;
    private Point2D position;
    private ImageView imageView;
    public BasedUnit(int hp,Point2D position, ImageView imageView){
        setHp(hp);
        setPosition(position);
        setImageView(imageView);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0,hp);
    }
    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
