package component.item.equipment;

import component.inventory.Inventory;
import component.inventory.Slot;
import component.item.RefineIron;
import component.player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        defense += 10;
        if (tier==EquipmentUtils.TIER.Bronze) durability = 3;
        else if (tier==EquipmentUtils.TIER.Silver) durability = 5;
        else if (tier==EquipmentUtils.TIER.Gold) durability = 10;
    }

    public void use() {
        if (Player.getInstance().getArmor() != null) {
            Armor armor = Player.getInstance().getArmor();
            armor.setDestroyed(false);

            Inventory.getInstance().getSlots().add(new Slot(armor));
            Player.getInstance().setArmor((Armor) this);
        } else {
            Player.getInstance().setArmor((Armor) this);
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
