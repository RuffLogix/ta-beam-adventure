package component.item.equipment;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import utils.EquipmentUtils;

public class Armor extends Equipment {
    private WritableImage writableImage = (WritableImage) EquipmentUtils.getImage(this);
    private int defense;

    public Armor() {
        super("Armor", null);
        super.setImage(writableImage);
        durability = 3;
        defense = 5;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public void upgrade() {
        if (tier==EquipmentUtils.TIER.Silver) return ;

        super.upgrade();
        defense += 5;
        durability = 5;
    }
}
