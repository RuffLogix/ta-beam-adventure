import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            Group root = new Group();
            Scene scene = new Scene(root, 800, 600);

            scene.setFill(Color.BLACK);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}