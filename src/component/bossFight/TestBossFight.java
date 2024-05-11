package component.bossFight;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestBossFight extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BossFight root = new BossFight();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Boss");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
