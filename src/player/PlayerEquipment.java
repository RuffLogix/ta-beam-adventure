package player;

import equipment.Amulet;
import equipment.Armor;
import equipment.Weapon;

public class PlayerEquipment {
    private Weapon weapon;
    private Armor armor;
    private Amulet amulet;

    public PlayerEquipment() {
        this.weapon = null;
        this.armor = null;
        this.amulet = null;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Amulet getAmulet() {
        return amulet;
    }

    public void setAmulet(Amulet amulet) {
        this.amulet = amulet;
    }
}
