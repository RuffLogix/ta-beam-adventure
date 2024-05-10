package component.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Item implements IUsable{
    protected String name;
    protected Image image;
    protected ImageView imageView;

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
}
