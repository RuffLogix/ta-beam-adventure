package component.bossFight;

import component.player.Player;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class AttackButton extends HBox {
    private Boss attackedBoss;
    private BossFight boosfight;
    public  UnitUtils.Result myResult;
    private AudioClip explosionSound;
    public AttackButton(Boss boss, BossFight bossFight){
        attackedBoss = boss;
        this.boosfight = bossFight;
        this.setAlignment(Pos.BOTTOM_CENTER);
        Image r = new Image(ClassLoader.getSystemResource("rock.jpg").toString());
        Image p = new Image(ClassLoader.getSystemResource("paper.jpg").toString());
        Image s = new Image(ClassLoader.getSystemResource("scissors.jpg").toString());
        explosionSound = new AudioClip(ClassLoader.getSystemResource("Explosion.wav").toString());
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
                attackedBoss.shoot();
                // get the result;
                myResult = Player.getResult(attackedBoss, myChoice);
                Status.drawLastResultMsg(BossFight.lastResultMsg);
                Status.drawResultImg(BossFight.resultImg);
                Status.showMsg(BossFight.resultImg);
                // reduce boss hp if player wins
                if(myResult.equals(UnitUtils.Result.WIN)){attackedBoss.setHp(attackedBoss.getHp()-20);}
                else if(myResult.equals(UnitUtils.Result.LOSS)){Player.setHp(Player.getHp() -20);}

                BossPane.bossHpText.setText("Your HP: "+ Player.getHp()+" VS  Boss HP: " + attackedBoss.getHp());
                BossFight.statusBox.getGraphicsContext2D().clearRect(0,0,300,400);
                Status.drawStatusBox(BossFight.statusBox);
                Status.drawStatusMsg(BossFight.statusBox);
                explosionSound.play();

                if(attackedBoss.getHp()==0){
                    Status.drawBossConquered(boosfight);
                    Player.level += 1;
                    Player.getInstance().setCoin(Player.getInstance().getCoin() + Math.min(Player.level*5,100));
                }
                else if(Player.getHp() == 0){
                    Status.drawGameOver(boosfight);
                }
            }
        });
        return btn;
    }
}
