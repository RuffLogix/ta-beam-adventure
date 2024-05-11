package subscene;


import component.item.HpPotion;
import component.item.MarketItem;
import component.item.equipment.Amulet;
import component.item.equipment.Armor;
import component.item.equipment.Weapon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MarketSubScene extends SubScene {

    private ArrayList<MarketItem> marketItems;
    private AnchorPane root;
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

    private void createMarketItems() {
        marketItems = new ArrayList<>();

        Armor item1 = new Armor();
        Amulet item2 = new Amulet();
        HpPotion item3 = new HpPotion(10);
        Weapon item4 = new Weapon();

        MarketItem marketItem1 = new MarketItem(item1, "This is an armor", 0);
        MarketItem marketItem2 = new MarketItem(item2, "This is an amulet", 0);
        MarketItem marketItem3 = new MarketItem(item3, "This is a potion", 10);
        MarketItem marketItem4 = new MarketItem(item4, "This is a weapon", 0);

        marketItems.add(marketItem1);
        marketItems.add(marketItem2);
        marketItems.add(marketItem3);
        marketItems.add(marketItem4);

        Label marketTitle = new Label("Market");
        marketTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        marketTitle.setPadding(new Insets(10));
        VBox container = new VBox(20); // Spacing between MarketItems
        container.setAlignment(Pos.CENTER); // Center alignment
        container.setPrefWidth(400); // Width same as MarketSubScene
        container.setPadding(new Insets(20));
        container.getChildren().add(marketTitle);

        for (MarketItem item : marketItems) {
            container.getChildren().add(item.getItemLayout());
        }

        notEnoughCoin = new Label("Not enough coin");
        notEnoughCoin.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
        notEnoughCoin.setPadding(new Insets(10));
        notEnoughCoin.setVisible(false);
        container.getChildren().add(notEnoughCoin);

        root.getChildren().add(container);
    }

    public void toggle() {
        setVisible(!isVisible());
    }
}
