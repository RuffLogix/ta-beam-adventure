package subscene;

import component.inventory.Inventory;
import component.player.Player;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class InventorySubScene extends SubScene {
    private Label coinLabel;
    private Label hpLabel;
    private Label atkLabel;
    private Label defLabel;
    private Label armorLabel;
    private Label weaponLabel;
    private Label amuletLabel;
    private final static int WIDTH = 650;
    private final static int HEIGHT = 250;
    private static InventorySubScene instance;

    public static InventorySubScene getInstance() {
        if (instance == null) {
            instance = new InventorySubScene();
        }

        return instance;
    }

    public InventorySubScene() {
        super(new AnchorPane(), WIDTH, HEIGHT);
        prefWidth(WIDTH);
        prefHeight(HEIGHT);

        AnchorPane root = (AnchorPane) getRoot();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.25), null, null)));

        setupStats(root);
        setupEquipment(root);
        setupItemSlots(root);
    }

    private void setupStats(AnchorPane root) {
        VBox statsBox = new VBox(5);

        coinLabel = createStyledLabel("Coins: " + Player.getInstance().getCoin());
        hpLabel = createStyledLabel("HP: " + Player.getInstance().getHp() + "/" + Player.MAX_HP);
        atkLabel = createStyledLabel("Atk: 15");
        defLabel = createStyledLabel("Def: 10");

        statsBox.getChildren().addAll(coinLabel, hpLabel, atkLabel, defLabel);

        AnchorPane.setTopAnchor(statsBox, 5.0);
        AnchorPane.setLeftAnchor(statsBox, 5.0);

        root.getChildren().add(statsBox);
    }

    public void updateStats() {
        coinLabel.setText("Coins: " + Player.getInstance().getCoin());
        hpLabel.setText("HP: " + Player.getInstance().getHp() + "/" + Player.MAX_HP);
    }

    private void setupItemSlots(AnchorPane root) {
        Inventory itemSlots = Inventory.getInstance();

        root.getChildren().add(itemSlots);
    }

    private void setupEquipment(AnchorPane root) {
        VBox equipmentBox = new VBox(5);

        armorLabel = createStyledLabel("Armor: Leather");
        weaponLabel = createStyledLabel("Weapon: Sword");
        amuletLabel = createStyledLabel("Amulet: Courage");

        equipmentBox.getChildren().addAll(armorLabel, weaponLabel, amuletLabel);

        AnchorPane.setTopAnchor(equipmentBox, 5.0);
        AnchorPane.setRightAnchor(equipmentBox, 5.0);

        root.getChildren().add(equipmentBox);
    }

    private Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font("Arial", 16));
        label.setTextFill(Color.web("#FFFFFF"));
        return label;
    }
}
