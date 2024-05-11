package component.item;

import component.inventory.Inventory;
import component.inventory.Slot;
import component.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import subscene.MarketSubScene;

public class MarketItem {
    private Item item;
    private Text itemName;
    private Text itemDescription;
    private Text itemPrice;
    private Button buyButton;
    private HBox itemLayout;

    public MarketItem(Item item, String itemDescription, int itemPrice) {
        this.item = item;
        createUI(item, itemDescription, itemPrice);
        setupEvents(itemPrice);
    }

    private void createUI(Item item, String itemDescription, int itemPrice) {
        itemName = new Text(item.getName());
        this.itemDescription = new Text(itemDescription);
        this.itemPrice = new Text("Price: " + itemPrice);

        buyButton = new Button("Buy");
        buyButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        buyButton.setPadding(new Insets(5, 10, 5, 10));

        itemLayout = new HBox();
        itemLayout.setAlignment(Pos.BASELINE_CENTER);
        itemLayout.setPadding(new Insets(10));
        itemLayout.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240, 0.8), new CornerRadii(5), Insets.EMPTY)));
        itemLayout.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));

        itemLayout.setSpacing(10);

        itemLayout.getChildren().addAll(itemName, this.itemDescription, this.itemPrice, buyButton);
    }

    private void setupEvents(int itemPrice) {
        buyButton.setOnAction(e -> buyItem(itemPrice));
    }

    private void buyItem(int itemPrice) {
        if (Player.getInstance().getCoin() >= itemPrice) {
            Player.getInstance().setCoin(Player.getInstance().getCoin() - itemPrice);
            Inventory.getInstance().getSlots().add(new Slot(item));
            Inventory.getInstance().updateInventory();
            itemLayout.setVisible(false);
        } else {
            MarketSubScene.notEnoughCoin.setVisible(true);
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    MarketSubScene.notEnoughCoin.setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public HBox getItemLayout() {
        return itemLayout;
    }

    public Text getItemName() {
        return itemName;
    }

    public Text getItemDescription() {
        return itemDescription;
    }

    public Text getItemPrice() {
        return itemPrice;
    }
}
