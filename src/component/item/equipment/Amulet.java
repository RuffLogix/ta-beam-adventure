package component.item.equipment;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import utils.EquipmentUtils;

public class Amulet extends Equipment {
    private WritableImage writableImage = (WritableImage) EquipmentUtils.getImage(this);
    private double probability;

    public Amulet() {
        super("Amulet", null);
        super.setImage(writableImage);
        durability = 10;
        probability = 0.1;
    }

    @Override
    public void upgrade(boolean useIron) {
        if (tier==EquipmentUtils.TIER.Gold) return ;

        super.upgrade(useIron);
        probability += 0.1;
        if (tier==EquipmentUtils.TIER.Silver) durability = 15;
        else if (tier==EquipmentUtils.TIER.Gold) durability = 25;
    }

    public double getProbability() {
        return probability;
    }
}
