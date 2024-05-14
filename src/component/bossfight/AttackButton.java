package component.bossfight;

import component.player.Player;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class AttackButton extends HBox {
    private Boss attackedBoss;
    private BossFight bossFight;
    public  UnitUtils.Result myResult;
    private MediaPlayer explosionSound;
    public AttackButton(Boss boss, BossFight bossFight){
        attackedBoss = boss;
        this.bossFight = bossFight;
        this.setAlignment(Pos.BOTTOM_CENTER);
        Image r = new Image(ClassLoader.getSystemResource("rock.jpg").toString());
        Image p = new Image(ClassLoader.getSystemResource("paper.jpg").toString());
        Image s = new Image(ClassLoader.getSystemResource("scissors.jpg").toString());
        Media sound = new Media(ClassLoader.getSystemResource("Explosion.wav").toString());
        explosionSound = new MediaPlayer(sound);
        explosionSound.setCycleCount(1);
        Button scissors = Btn(s, UnitUtils.Outcome.SCISSORS);
        Button rock = Btn(r, UnitUtils.Outcome.ROCK);
        Button paper = Btn(p, UnitUtils.Outcome.PAPER);

       this.getChildren().addAll(rock,paper,scissors);
    }
    public Button Btn(Image image, UnitUtils.Outcome myChoice){
        Button btn = new Button();
        btn.setTextFill(Color.WHITE);
        btn.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(200,100,true,true,true,true))));
        btn.setFont(new Font(30));
        btn.setPrefSize(200,100);
        btn.setSkin(new MyButtonSkin(btn));
        btn.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,null,null)));
        btn.arm();
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BossFight.myChoice = myChoice;
                // boss starts shooting
                if (Player.getInstance().getAmulet()!=null && Player.getInstance().getAmulet().getProbability() > Math.random()) {
                    if (myChoice == UnitUtils.Outcome.ROCK) {
                        attackedBoss.setChoice(UnitUtils.Outcome.SCISSORS);
                    } else if (myChoice == UnitUtils.Outcome.PAPER) {
                        attackedBoss.setChoice(UnitUtils.Outcome.ROCK);
                    } else {
                        attackedBoss.setChoice(UnitUtils.Outcome.PAPER);
                    }
                } else {
                    attackedBoss.shoot();
                }

                // get the result;
                myResult = Player.getResult(attackedBoss, myChoice);
                Status.drawLastResultMsg(BossFight.lastResultMsg);
                Status.drawResultImg(BossFight.resultImg);
                Status.showMsg(BossFight.resultImg);
                // reduce boss hp if player wins
                if (myResult.equals(UnitUtils.Result.WIN)){
                    attackedBoss.setHp(attackedBoss.getHp()-Player.getInstance().getAtk());
                    Player.getInstance().useWeapon();
                } else if(myResult.equals(UnitUtils.Result.LOSS)){
                    Player.getInstance().takeDamage(10);
                }
                Player.getInstance().useAmulet();

                BossPane.bossHpText.setText("Your HP: "+ Player.getHp() +"/100"+" VS  Boss HP: " + attackedBoss.getHp() +"/"+attackedBoss.max_hp);
                BossFight.statusBox.getGraphicsContext2D().clearRect(0,0,300,400);
                Status.drawStatusBox(BossFight.statusBox);
                Status.drawStatusMsg(BossFight.statusBox);
                explosionSound.play();

                if(attackedBoss.getHp()==0){
                    Player.level += 1;
                    Status.drawBossConquered(bossFight);
                    Player.getInstance().setCoin(Player.getInstance().getCoin() + Math.min(Player.level*5,100));
                }
                else if(Player.getHp() == 0){
                    Status.drawGameOver(bossFight);
                }
            }
        });
        return btn;
    }
}
