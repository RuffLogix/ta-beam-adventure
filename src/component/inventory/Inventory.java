package component.inventory;

import component.item.HpPotion;
import component.item.RefineIron;
import component.item.equipment.Amulet;
import component.item.equipment.Armor;
import component.item.equipment.Weapon;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Inventory extends GridPane {
    private static Inventory instance;
    private static ArrayList<Slot> slots;
    private static final int INVENTORY_SIZE = 39;
    private static AnimationTimer timer;

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }

        return instance;
    }

    private Inventory() {
        initialInventory();
        updateInventory();
    }

    private void initialInventory() {
        slots = new ArrayList<>();

        slots.add(new Slot(new HpPotion(10)));
        slots.add(new Slot(new Armor()));
        slots.add(new Slot(new Amulet()));
        slots.add(new Slot(new Weapon()));
        slots.add(new Slot(new HpPotion(20)));
        slots.add(new Slot(new Amulet()));
        slots.add(new Slot(new RefineIron()));
        slots.add(new Slot(new RefineIron()));
        slots.add(new Slot(new RefineIron()));
        slots.add(new Slot(new RefineIron()));
        slots.add(new Slot(new RefineIron()));


        setHgap(2);
        setVgap(2);

        AnchorPane.setTopAnchor(this, 5.0);
        AnchorPane.setLeftAnchor(this, 100.0);
    }

    public void updateInventory() {
        getChildren().clear();

        slots.removeIf(slot -> slot.getItem().isDestroyed());

        for (int i = 0; i < INVENTORY_SIZE; i++) {
            if (i < slots.size()) {
                Slot slot = slots.get(i);
                add(slot, i % 13, i / 13);
            } else {
                Rectangle rectangle = new Rectangle(30, 30);
                rectangle.setOpacity(0.5);
                rectangle.setOnMouseEntered(e -> rectangle.setOpacity(1));
                rectangle.setOnMouseExited(e -> rectangle.setOpacity(0.5));
                add(rectangle, i % 13, i / 13);
            }
        }
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }
}
