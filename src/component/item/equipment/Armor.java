package component.item.equipment;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Armor extends Equipment {
    private static final String IMAGE_PATH = ClassLoader.getSystemResource("sprite/item.png").toString();
    private static final Image IMAGE = new Image(IMAGE_PATH);
    private static WritableImage writableImage = new WritableImage(IMAGE.getPixelReader(), 316, 7, 56, 52);
    private int defense;

    public Armor() {
        super("Armor", writableImage);
        durability = 100;
        defense = 5;
    }

    public int getDefense() {
        return defense;
    }
}