package component.item.equipment;

import component.inventory.Inventory;
import component.inventory.Slot;
import component.item.Item;
import component.item.RefineIron;
import component.player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.EquipmentUtils;

public class Equipment extends Item implements Refinable {
    protected int durability;
    protected EquipmentUtils.TIER tier = EquipmentUtils.TIER.Bronze;
    public Equipment(String name, Image image) {
        super(name, image);
    }

    @Override
    public void use() {
        if (this instanceof Amulet) {
            if (Player.getInstance().getAmulet() != null) {
                Amulet amulet = Player.getInstance().getAmulet();
                amulet.setDestroyed(false);

                Inventory.getInstance().getSlots().add(new Slot(amulet));
                Player.getInstance().setAmulet((Amulet) this);
            } else {
                Player.getInstance().setAmulet((Amulet) this);
            }
            setDestroyed(true);
        } else if (this instanceof Armor) {
            if (Player.getInstance().getArmor() != null) {
                Armor armor = Player.getInstance().getArmor();
                armor.setDestroyed(false);

                Inventory.getInstance().getSlots().add(new Slot(armor));
                Player.getInstance().setArmor((Armor) this);
            } else {
                Player.getInstance().setArmor((Armor) this);
            }
            setDestroyed(true);
        } else if (this instanceof Weapon) {
            if (Player.getInstance().getWeapon() != null) {
                Weapon weapon = Player.getInstance().getWeapon();
                weapon.setDestroyed(false);

                Inventory.getInstance().getSlots().add(new Slot(weapon));
                Player.getInstance().setWeapon((Weapon) this);
            } else {
                Player.getInstance().setWeapon((Weapon) this);
            }
            setDestroyed(true);
        }

        Inventory.getInstance().updateInventory();
    }

    public int getDurability() {
        return durability;
    }

    public void decreaseDurability() {
        durability--;
    }

    @Override
    public void upgrade() {
        if (tier == EquipmentUtils.TIER.Silver) return ;

        boolean found = false;
        for (Slot slot: Inventory.getInstance().getSlots()) {
            if (slot.getItem() instanceof RefineIron) {
                Inventory.getInstance().getSlots().remove(slot);
                found = true;
                break;
            }
        }
        if (!found) return ;

        name = name + "+";
        tier = EquipmentUtils.TIER.Silver;
        image = EquipmentUtils.getImage(this);
        imageView = new ImageView(EquipmentUtils.getImage(this));
        Inventory.getInstance().updateInventory();
    }

    public EquipmentUtils.TIER getTier() {
        return tier;
    }
}
