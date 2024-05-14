package component.item.equipment;

import component.inventory.Inventory;
import component.inventory.Slot;
import component.item.RefineIron;
import component.player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        probability += 0.05;
        if (tier==EquipmentUtils.TIER.Silver) durability = 15;
        else if (tier==EquipmentUtils.TIER.Gold) durability = 25;
    }

    public void use() {
        if (Player.getInstance().getAmulet() != null) {
            Amulet amulet = Player.getInstance().getAmulet();
            amulet.setDestroyed(false);

            Inventory.getInstance().getSlots().add(new Slot(amulet));
            Player.getInstance().setAmulet((Amulet) this);
        } else {
            Player.getInstance().setAmulet((Amulet) this);
        }
        setDestroyed(true);
        Inventory.getInstance().updateInventory();
    }

    public double getProbability() {
        return probability;
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
