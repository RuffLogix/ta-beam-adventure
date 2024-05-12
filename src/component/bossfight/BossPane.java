package component.bossfight;

import component.player.Player;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BossPane extends VBox {
    public static Text bossHpText;
    public static Boss boss;
    public static AttackButton atk;
    public BossPane(Boss boss, BossFight bossFight){
        this.boss = boss;
        setAlignment(Pos.CENTER);
        Image img =  new Image(ClassLoader.getSystemResource("bg.png").toString());
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(800,600,true,true,true,true));
        Background bGround = new Background(bImg);
        setBackground(bGround);
        ImageView bossImg = boss.getImageView();
        Text hpText = new Text("Your HP: "+ Player.getHp()+"/100"+" VS  Boss HP: " + boss.getHp() +"/"+boss.max_hp);
        hpText.setFill(Color.WHITE);
        hpText.setFont(Font.font("Verdana", FontWeight.BOLD,30));
        hpText.setStroke(Color.BLACK);
        atk = new AttackButton(boss,bossFight);
        this.bossHpText = hpText;
        this.getChildren().add(hpText);
        this.getChildren().add(bossImg);
        this.getChildren().add(atk);

    }

}
