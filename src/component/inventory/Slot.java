package component.inventory;

import component.item.IUsable;
import component.item.Item;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Slot extends Pane {
    private Item item;
    private ImageView imageView;
    Label text;
    public Slot(Item item) {
        imageView = item.getImageView();
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        this.item = item;

        setPrefWidth(30);
        setPrefHeight(30);

        getChildren().add(imageView);

        addEventListeners();
        addText();
    }

    private void addEventListeners() {
        setOnMouseClicked(e -> {
            item.use();
            imageView.setImage(item.getImage());
            Inventory.getInstance().updateInventory();
        });

        setOnMouseEntered(e -> {
            setOpacity(0.5);
        });

        setOnMouseExited(e -> {
            setOpacity(1);
        });
    }

    private void addText() {
        text = new Label("x1");
        text.setLayoutX(15);
        text.setLayoutY(15);
        text.setTextFill(Color.WHITE);
        getChildren().add(text);
    }

    public Item getItem() {
        return item;
    }

    public void setText(String s) {
        text.setText(s);
    }
}
