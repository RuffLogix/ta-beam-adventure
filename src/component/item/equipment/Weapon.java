package component.item.equipment;

import component.inventory.Inventory;
import component.inventory.Slot;
import component.item.RefineIron;
import component.player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public void upgrade(boolean useIron) {
        if (tier==EquipmentUtils.TIER.Gold) return ;

        if (useIron) {
            boolean found = false;
            for (Slot slot : Inventory.getInstance().getSlots()) {
                if (slot.getItem() instanceof RefineIron) {
                    Inventory.getInstance().getSlots().remove(slot);
                    found = true;
                    break;
                }
            }
            if (!found) return;
        }

        name = name + "+";
        tier = tier == EquipmentUtils.TIER.Bronze ? EquipmentUtils.TIER.Silver : tier == EquipmentUtils.TIER.Silver ? tier = EquipmentUtils.TIER.Gold : tier;
        image = EquipmentUtils.getImage(this);
        imageView = new ImageView(EquipmentUtils.getImage(this));

        Inventory.getInstance().updateInventory();

        damage += 5;
        if (tier==EquipmentUtils.TIER.Silver) durability = 7;
        else if (tier==EquipmentUtils.TIER.Gold) durability = 15;
    }

    public void use() {
        if (Player.getInstance().getWeapon() != null) {
            Weapon weapon = Player.getInstance().getWeapon();
            weapon.setDestroyed(false);

            Inventory.getInstance().getSlots().add(new Slot(weapon));
            Player.getInstance().setWeapon((Weapon) this);
        } else {
            Player.getInstance().setWeapon((Weapon) this);
        }
        setDestroyed(true);
        Inventory.getInstance().updateInventory();
    }
    public int getDurability() {
        return durability;
    }
    public void decreaseDurability() {
        durability--;
    }
    public EquipmentUtils.TIER getTier() {
        return tier;
    }
}
