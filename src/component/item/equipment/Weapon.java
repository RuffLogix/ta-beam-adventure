package component.item.equipment;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Weapon extends Equipment {
    private static final String IMAGE_PATH = ClassLoader.getSystemResource("sprite/item.png").toString();
    private static final Image IMAGE = new Image(IMAGE_PATH);
    private static WritableImage writableImage = new WritableImage(IMAGE.getPixelReader(), 312, 250, 63, 63);
    private int damage;
    public Weapon() {
        super("Weapon", writableImage);
        durability = 100;
        damage = 5;
    }

    public int getDamage() {
        return damage;
    }
}
