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
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public void upgrade(boolean useIron) {
        if (tier==EquipmentUtils.TIER.Gold) return ;

        super.upgrade(useIron);
        defense += 5;
        if (tier==EquipmentUtils.TIER.Bronze) durability = 3;
        else if (tier==EquipmentUtils.TIER.Silver) durability = 5;
        else if (tier==EquipmentUtils.TIER.Gold) durability = 10;
    }
}
