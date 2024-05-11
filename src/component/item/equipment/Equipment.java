package component.item.equipment;

import component.inventory.Inventory;
import component.inventory.Slot;
import component.item.Item;
import component.player.Player;
import javafx.scene.image.Image;

public class Equipment extends Item {
    public Equipment(String name, Image image) {
        super(name, image);
    }
    protected int durability;

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

    public String getName() {
        return name;
    }
}
