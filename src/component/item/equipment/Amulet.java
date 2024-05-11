package component.item.equipment;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import utils.EquipmentUtils;

public class Amulet extends Equipment {
    private WritableImage writableImage = (WritableImage) EquipmentUtils.getImage(this);
    public Amulet() {
        super("Amulet", null);
        super.setImage(writableImage);
        durability = 250;
    }
}
