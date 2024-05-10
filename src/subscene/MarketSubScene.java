package subscene;

import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class MarketSubScene extends SubScene {
    public MarketSubScene() {
        super(new AnchorPane(), 400, 400);
        prefWidth(400);
        prefHeight(400);

        AnchorPane root = (AnchorPane) getRoot();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        setVisible(false);

        Button button = new Button("Market");
        button.setLayoutX(200);
        button.setLayoutY(200);
        root.getChildren().add(button);
    }
}
