package component.bossfight;

import javafx.application.Application;
import javafx.stage.Stage;

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
