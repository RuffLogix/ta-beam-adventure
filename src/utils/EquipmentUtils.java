package utils;

import component.item.equipment.Amulet;
import component.item.equipment.Armor;
import component.item.equipment.Equipment;
import component.item.equipment.Weapon;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class EquipmentUtils {
    private static final String IMAGE_PATH = ClassLoader.getSystemResource("sprite/item.png").toString();
    private static final Image IMAGE = new Image(IMAGE_PATH);
    public enum TIER { Bronze, Iron, Steel, Silver, Gold, Diamond, Mythril, Adamantium, Orichalcum, Excalibur }

    public static Image getImage(Equipment equipment) {
        if (equipment instanceof Weapon) {
            switch (equipment.getTier()) {
                case Bronze -> {
                    return new WritableImage(IMAGE.getPixelReader(), 312, 250, 63, 63);
                }
                case Silver -> {
                    return new WritableImage(IMAGE.getPixelReader(), 437, 250, 63, 63);
                }
            }
        } else if (equipment instanceof Armor) {
            switch (equipment.getTier()) {
                case Bronze -> {
                    return new WritableImage(IMAGE.getPixelReader(), 316, 7, 56, 52);
                }
                case Silver -> {
                    return new WritableImage(IMAGE.getPixelReader(), 441, 7, 56, 52);
                }
            }
        } else if (equipment instanceof Amulet) {
            switch (equipment.getTier()) {
                case Bronze -> {
                    return new WritableImage(IMAGE.getPixelReader(), 11, 449, 36, 40);
                }
                case Silver -> {
                    return new WritableImage(IMAGE.getPixelReader(), 74, 449, 36, 40);
                }
            }
        }

        return null;
    }

    public static Image getRefineIronImage() {
        return new WritableImage(IMAGE.getPixelReader(), 140, 195, 32, 48);
    }
}
