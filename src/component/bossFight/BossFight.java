package component.bossFight;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.ViewManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BossFight extends StackPane {
  private BossPane bosspane;
  private static BossFight instance;
  private static Stage mainStage;
  public static Pane mainPane;
  public static Canvas statusBox = new Canvas(250,400);
  public static Label resultMsg = new Label();
  public static Label lastResultMsg = new Label();
  public static Canvas resultImg = new Canvas(220,150);
  public static UnitUtils.Outcome myChoice;

  public BossFight(){
      createMainStage();
  }
    public static BossFight getInstance() {
        if (instance == null) {
            instance = new BossFight();
        }
        return instance;
    }
    private void createMainStage() {
        mainPane = new StackPane();
        List<Boss> list = Arrays.asList(new ChickenBoss(),new PigBoss(), new SheepBoss(), new RabbitBoss());
        Random random = new Random();
        Boss randomBoss = list.get(random.nextInt(list.size()));
        bosspane = new BossPane(randomBoss,this);

        Status.drawStatusBox(statusBox);
        Status.drawStatusMsg(statusBox);

        DoubleBinding sx = statusBox.widthProperty().add(30);
        DoubleBinding sy= statusBox.heightProperty().subtract(450);
        statusBox.translateXProperty().bind(sx);
        statusBox.translateYProperty().bind(sy);
        lastResultMsg.translateXProperty().bind(sx.subtract(40));
        lastResultMsg.translateYProperty().bind(sy.add(150));
        mainPane.getChildren().add(bosspane);
        mainPane.getChildren().add(statusBox);
        mainPane.getChildren().add(resultMsg);
        mainPane.getChildren().add(lastResultMsg);
        mainPane.getChildren().add(resultImg);

        Scene scene = new Scene(mainPane, 800, 600);
        mainStage = new Stage();
        mainStage.setTitle("TA.Beam's Adventure");
        mainStage.setScene(scene);
    }
    public static Stage getMainStage() {
        return mainStage;
    }
}
