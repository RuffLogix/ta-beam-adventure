package subscene;


import component.item.HpPotion;
import component.item.Item;
import component.item.MarketItem;
import component.item.RefineIron;
import component.item.equipment.Amulet;
import component.item.equipment.Armor;
import component.item.equipment.Equipment;
import component.item.equipment.Weapon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MarketSubScene extends SubScene {

    private static ArrayList<MarketItem> marketItems;
    private static AnchorPane root;
    public static Label notEnoughCoin;

    public MarketSubScene() {
        super(new AnchorPane(), 400, 400);
        prefWidth(400);
        prefHeight(400);

        root = (AnchorPane) getRoot();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.5), null, null)));
        setVisible(false);

        createMarketItems();
    }

    private static Item createItem() {
        double random = Math.random();
        if (random < 0.2) {
            return new Armor();
        } else if (random < 0.4) {
            return new Amulet();
        } else if (random < 0.6) {
            return new Weapon();
        } else if (random < 0.8) {
            return new HpPotion(10);
        } else {
            return new RefineIron();
        }
    }

    public static void createMarketItems() {
        root.getChildren().clear();
        marketItems = new ArrayList<>();

        for (int i=0; i<4; i++) {
            Item item = createItem();
            if (item instanceof Equipment) {
                if (Math.random() < 0.2) ((Equipment) item).upgrade(false);
                if (Math.random() < 0.1) ((Equipment) item).upgrade(false);
            }
            marketItems.add(new MarketItem(item, "This is an item", (int) (1+ Math.random() * 15)));
        }
        Label marketTitle = new Label("Market");
        marketTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        marketTitle.setPadding(new Insets(10));
        VBox container = new VBox(20); // Spacing between MarketItems
        container.setAlignment(Pos.CENTER); // Center alignment
        container.setPrefWidth(400); // Width same as MarketSubScene
        container.setPadding(new Insets(20));
        container.getChildren().add(marketTitle);

        notEnoughCoin = new Label("Not enough coin");
        notEnoughCoin.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
        notEnoughCoin.setVisible(false);
        container.getChildren().add(notEnoughCoin);

        for (MarketItem item : marketItems) {
            container.getChildren().add(item.getItemLayout());
        }

        root.getChildren().add(container);
    }

    public void toggle() {
        setVisible(!isVisible());
    }
}
