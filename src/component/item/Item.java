package component.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Item implements IUsable{
    protected String name;
    protected Image image;
    protected ImageView imageView;
    private boolean isDestroyed = false;

    public Item(String name, Image image) {
        this.name = name;
        this.image = image;
        this.imageView = new ImageView(image);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        this.imageView = new ImageView(image);
    }

    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public String getName() {
        return name;
    }
}
