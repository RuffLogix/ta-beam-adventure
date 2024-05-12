package component.bossfight;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BossFight extends StackPane {
  private BossPane bossPane;
  private static BossFight instance;
  private static Stage mainStage;
  public static Pane mainPane;
  public static Canvas statusBox = new Canvas(250,420);
  public static Label lastResultMsg;
  public static Canvas resultImg = new Canvas(220,150);
  public static UnitUtils.Outcome myChoice;

  private BossFight(){
      createMainStage();
  }
    public static BossFight getInstance() {
      instance = new BossFight();
      return instance;
    }
    private void createMainStage() {
        mainPane = new StackPane();
        List<Boss> list = Arrays.asList(new ChickenBoss(),new PigBoss(), new SheepBoss(), new RabbitBoss());
        Random random = new Random();
        Boss randomBoss = list.get(random.nextInt(list.size()));
        bossPane = new BossPane(randomBoss,this);

        Status.drawStatusBox(statusBox);
        Status.drawStatusMsg(statusBox);
        lastResultMsg = new Label();

        DoubleBinding sx = statusBox.widthProperty().add(30);
        DoubleBinding sy= statusBox.heightProperty().subtract(450);
        statusBox.translateXProperty().bind(sx);
        statusBox.translateYProperty().bind(sy);
        lastResultMsg.translateXProperty().bind(sx.subtract(520));
        lastResultMsg.translateYProperty().bind(sy.add(180));
        mainPane.getChildren().add(bossPane);
        mainPane.getChildren().add(statusBox);
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
