package component.bossFight;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BossFight extends StackPane {
  private BossPane bosspane;
  public static Canvas statusBox = new Canvas(250,400);
  public static Label resultMsg = new Label();
  public static Label lastResultMsg = new Label();
  public static Canvas resultImg = new Canvas(220,150);
  public static UnitUtils.Outcome myChoice;
  public BossFight(){
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

      this.getChildren().add(bosspane);
      this.getChildren().add(statusBox);
      this.getChildren().add(resultMsg);
      this.getChildren().add(lastResultMsg);
      this.getChildren().add(resultImg);
  }
}
