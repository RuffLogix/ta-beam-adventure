package component.bossFight;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ViewManager;

public class TestBossFight extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            BossFight bossFight = BossFight.getInstance();
            primaryStage = bossFight.getMainStage();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
