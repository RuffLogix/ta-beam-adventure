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
    private static final String IMAGE_PATH2 = ClassLoader.getSystemResource("sprite/item2.png").toString();
    private static final Image IMAGE2 = new Image(IMAGE_PATH2);
    public enum TIER { Bronze, Iron, Steel, Silver, Gold, Diamond, Mythril, Adamantium, Orichalcum, Excalibur }

    public static Image getImage(Equipment equipment) {
        if (equipment instanceof Weapon) {
            if (equipment.getTier().equals(TIER.Bronze)) {
                return new WritableImage(IMAGE.getPixelReader(), 312, 250, 63, 63);
            } else if (equipment.getTier().equals(TIER.Silver)) {
                return new WritableImage(IMAGE.getPixelReader(), 437, 250, 63, 63);
            } else if (equipment.getTier().equals(TIER.Gold)) {
                return new WritableImage(IMAGE2.getPixelReader(), 437, 250, 63, 63);
            }
        } else if (equipment instanceof Armor) {
            if (equipment.getTier().equals(TIER.Bronze)) {
                return new WritableImage(IMAGE.getPixelReader(), 316, 7, 56, 52);
            } else if (equipment.getTier().equals(TIER.Silver)) {
                return new WritableImage(IMAGE.getPixelReader(), 441, 7, 56, 52);
            } else if (equipment.getTier().equals(TIER.Gold)) {
                return new WritableImage(IMAGE2.getPixelReader(), 441, 7, 56, 52);
            }
        } else if (equipment instanceof Amulet) {
            if (equipment.getTier().equals(TIER.Bronze)) {
                return new WritableImage(IMAGE.getPixelReader(), 11, 449, 36, 40);
            } else if (equipment.getTier().equals(TIER.Silver)) {
                return new WritableImage(IMAGE.getPixelReader(), 74, 449, 36, 40);
            } else if (equipment.getTier().equals(TIER.Gold)) {
                return new WritableImage(IMAGE2.getPixelReader(), 74, 449, 36, 40);
            }
        }

        return null;
    }

    public static Image getRefineIronImage() {
        return new WritableImage(IMAGE.getPixelReader(), 140, 195, 32, 48);
    }
}
