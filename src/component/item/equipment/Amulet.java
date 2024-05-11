package component.item.equipment;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Amulet extends Equipment {
    private static final String IMAGE_PATH = ClassLoader.getSystemResource("sprite/item.png").toString();
    private static final Image IMAGE = new Image(IMAGE_PATH);
    private static WritableImage writableImage = new WritableImage(IMAGE.getPixelReader(), 199, 195, 40, 47);
    public Amulet() {
        super("Amulet", writableImage);
        durability = 250;
    }
}
