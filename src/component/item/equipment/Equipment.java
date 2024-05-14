package component.item.equipment;

import component.inventory.Inventory;
import component.inventory.Slot;
import component.item.Item;
import component.item.RefineIron;
import component.player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.EquipmentUtils;

public abstract class Equipment extends Item implements Refinable {
    protected int durability;
    protected EquipmentUtils.TIER tier = EquipmentUtils.TIER.Bronze;
    public Equipment(String name, Image image) {
        super(name, image);
    }

    public abstract int getDurability();

    public abstract void decreaseDurability();
    public abstract EquipmentUtils.TIER getTier();
}
