package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            ViewManager viewManager = ViewManager.getInstance();

            primaryStage = viewManager.getMainStage();

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}