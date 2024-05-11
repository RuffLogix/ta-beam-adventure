package component.item.equipment;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import utils.EquipmentUtils;

public class Weapon extends Equipment {
    private WritableImage writableImage = (WritableImage) EquipmentUtils.getImage(this);
    private int damage;
    public Weapon() {
        super("Weapon", null);
        super.setImage(writableImage);
        durability = 5;
        damage = 5;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void upgrade() {
        if (tier==EquipmentUtils.TIER.Gold) return ;

        super.upgrade();
        damage += 5;
        durability = 7;
    }
}
