package component.bossfight;

import component.inventory.Inventory;
import component.inventory.Slot;
import component.item.Item;
import component.item.RefineIron;
import component.item.equipment.Amulet;
import component.item.equipment.Armor;
import component.item.equipment.Weapon;
import component.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import subscene.MarketSubScene;
import view.GameViewManager;
import view.ViewManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Status {
    public static void drawResultImg(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw the result image of the player and the boss
        gc.clearRect(0,0,220,170);
        gc.setGlobalAlpha(0.8);
        gc.setLineWidth(10.0);
        gc.setFill(Color.rgb(188,158,130));
        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(10, 10, 200, 130,20,20);
        gc.fillRoundRect(10, 10, 200, 130,20,20);
        gc.drawImage(UnitUtils.convertOutcomeToImage(BossFight.myChoice),10,15);
        gc.drawImage(UnitUtils.convertOutcomeToImage(BossPane.boss.getChoice()),140,15);
        gc.setFont(new Font(30));
        gc.setFill(Color.rgb(88,57,39));
        gc.fillText("VS", 95,65);

        gc.setFont(new Font(20));
        gc.setFill(Color.WHITE);
        gc.fillText(String.valueOf(BossPane.atk.myResult),90,110);
    }
    public static void showMsg(Canvas t){
        t.setVisible(true);
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask(){

            @Override
            public void run() {
               t.setVisible(false);
            }
        }, 2000);

    }
    public static void drawStatusBox(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setGlobalAlpha(0.8);
        gc.setLineWidth(10.0);
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(10, 10, 200, 400,20,20);
        gc.fillRoundRect(10, 10, 200, 400,20,20);
    }
    public static void drawStatusMsg(Canvas canvas){
        // Draw the status message of each equipment
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(20));
        gc.fillText("Boss level: "+Player.level,20,40);
        gc.fillText("Your Equipment:",20,70);

        gc.setFont(new Font(17));
        String armorName = Player.getInstance().getArmor()!=null ? Player.getInstance().getArmor().getName() : "None";
        String weaponName = Player.getInstance().getWeapon()!=null ? Player.getInstance().getWeapon().getName() : "None";
        String amuletName = Player.getInstance().getAmulet()!=null ? Player.getInstance().getAmulet().getName() : "None";
        gc.fillText("Armor : "+armorName ,20,100);
        gc.fillText("Weapon : "+weaponName ,20,200);
        gc.fillText("Amulet : "+amuletName ,20,300);

        int armorDu = Player.getInstance().getArmor()!=null ? Player.getInstance().getArmor().getDurability() : 0;
        int weaponDu = Player.getInstance().getWeapon()!=null ? Player.getInstance().getWeapon().getDurability() : 0;
        int amuletDu = Player.getInstance().getAmulet()!=null ? Player.getInstance().getAmulet().getDurability() : 0;
        gc.setFont(new Font(15));
        if(Player.getInstance().getArmor()!=null){
            gc.fillText("Durability: " +armorDu,20,130);
            gc.drawImage(Player.getInstance().getArmor().getImage(),20,140,30,30);
        }
        if(Player.getInstance().getWeapon()!=null){
            gc.fillText("Durability: " +weaponDu,20,230);
            gc.drawImage(Player.getInstance().getWeapon().getImage(),20,240,30,30);
        }
        if(Player.getInstance().getAmulet()!=null){
            gc.fillText("Durability: " +amuletDu,20,330);
            gc.drawImage(Player.getInstance().getAmulet().getImage(),20,340,30,30);
        }


    }
    public static void drawBossConquered(BossFight bossFight){
        Canvas canvas = new Canvas(800,600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw the congratulation message
        gc.setGlobalAlpha(0.8);
        gc.setLineWidth(10.0);
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(10, 10, 780, 580,20,20);
        gc.fillRoundRect(10, 10, 780, 580,20,20);
        gc.setFont(new Font(40));
        gc.setFill(Color.YELLOW);
        gc.fillText("Congratulations!!!",250,200);
        gc.setFont(new Font(20));
        gc.fillText("You've conquered the boss.",300,250);
        gc.fillText("Your rewards: ",340,300);
        gc.fillText("Level: +1  Coins: +"+Math.min(Player.level*5,100), 340,340);

        // Play the win sound
        AudioClip winSound = new AudioClip(ClassLoader.getSystemResource("sound/win.mp3").toString());
        winSound.play();

        // Create a item drop list
        AnchorPane a = new AnchorPane(canvas);
        HBox ItemList = new HBox();
        ItemList.setPrefSize(600,75);
        ItemList.setLayoutX(100);
        ItemList.setLayoutY(400);
        ItemList.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,255, 0.5),null,null)));
        ItemList.setSpacing(10);
        ItemList.setAlignment(Pos.CENTER);

        ArrayList<Item> items = UnitUtils.generateReward();

        for (Item item: items) {
            Slot s = new Slot(item);
            HBox.setMargin(s, new Insets(12.5));
            s.setPrefWidth(50);
            s.setPrefHeight(50);
            s.setText("");
            s.getItem().getImageView().setFitWidth(50);
            s.getItem().getImageView().setFitHeight(50);
            s.setOnMouseClicked(null);
            ItemList.getChildren().add(s);
        }

        for (Item item: items) {
            if (item instanceof RefineIron) {
                Inventory.getInstance().getSlots().add(new Slot(new RefineIron()));
            } else if (item instanceof Armor) {
                Inventory.getInstance().getSlots().add(new Slot(new Armor()));
            } else if (item instanceof Amulet) {
                Inventory.getInstance().getSlots().add(new Slot(new Amulet()));
            } else if (item instanceof Weapon) {
                Inventory.getInstance().getSlots().add(new Slot(new Weapon()));
            } else {
                Inventory.getInstance().getSlots().add(new Slot(new component.item.HpPotion(10)));
            }
        }
        Inventory.getInstance().updateInventory();

        a.getChildren().add(ItemList);
        Button backButton = new Button("back");
        backButton.setPrefSize(200,20);
        backButton.setFont(new Font(20));
        // back
        backButton.setOnMouseClicked(e -> {
            GameViewManager.getInstance().getGameStage().show();
            GameViewManager.getInstance().renderSlime(10);
            MarketSubScene.createMarketItems();
            BossFight.getMainStage().close();
        });
        //
        AnchorPane.setBottomAnchor(backButton,50.0);
        AnchorPane.setLeftAnchor(backButton,300.0);
        a.getChildren().add(backButton);
        bossFight.mainPane.getChildren().add(a);
    }
    public static void drawGameOver(BossFight bossFight){
        Canvas canvas = new Canvas(800,600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw the game over message
        gc.setGlobalAlpha(0.8);
        gc.setLineWidth(10.0);
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(10, 10, 780, 580,20,20);
        gc.fillRoundRect(10, 10, 780, 580,20,20);
        gc.setFont(new Font(40));
        gc.setFill(Color.RED);
        gc.fillText("Game Over",280,250);
        gc.setFont(new Font(20));
        gc.fillText("You've defeated, try again next time.",280,300);

        // Play the loss sound
        AudioClip lossSound = new AudioClip(ClassLoader.getSystemResource("sound/loss.mp3").toString());
        lossSound.play();

        AnchorPane a = new AnchorPane(canvas);
        Button backButton = new Button("exit");
        backButton.setPrefSize(200,20);
        backButton.setFont(new Font(20));
        // exit
        backButton.setOnMouseClicked(e -> {
            ViewManager.getInstance().getMainStage().show();
            BossFight.getMainStage().close();
        });
        //
        AnchorPane.setBottomAnchor(backButton,50.0);
        AnchorPane.setLeftAnchor(backButton,300.0);
        a.getChildren().add(backButton);
        bossFight.mainPane.getChildren().add(a);
    }
    public static void drawLastResultMsg(Label t){
        // Draw the last result message
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("Verdana", FontWeight.BOLD,16));
        t.setText(BossFight.myChoice+ " VS "+ BossPane.boss.getChoice() + ", "+ BossPane.atk.myResult);
        t.setBackground(new Background(new BackgroundFill(Color.rgb(188,158,130),null,null)));

    }
}
