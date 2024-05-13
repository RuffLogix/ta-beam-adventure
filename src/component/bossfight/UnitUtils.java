
package component.bossfight;
import component.item.Item;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class UnitUtils {

    public enum Outcome{
        ROCK,
        PAPER,
        SCISSORS;
    }
    public enum Result{
        WIN,
        LOSS,
        DRAW
    }
    public static Outcome generateOutcome(double probRock, double probPaper, double probScissors){
        Outcome outcome;
        double randomNumber = Math.random();
        if(randomNumber <= probRock){outcome = Outcome.ROCK;}
        else if(randomNumber <= probRock+probPaper){outcome = Outcome.PAPER;}
        else{outcome = Outcome.SCISSORS;}
        return outcome;
    }
    public static int evaluate(Outcome player, Outcome computer){
            if (player == computer) return 0;
            if (player == Outcome.ROCK && computer == Outcome.SCISSORS) return 1;
            if (player == Outcome.PAPER && computer == Outcome.ROCK) return 1;
            if (player == Outcome.SCISSORS && computer == Outcome.PAPER) return 1;
            return -1;
    }
    public static Image outcomeToImage(Outcome outcome){
        Image image;
        if(outcome.equals(Outcome.ROCK)){
            image = new Image(ClassLoader.getSystemResource("r.png").toString());
        }
        else if(outcome.equals(Outcome.PAPER)){
            image = new Image(ClassLoader.getSystemResource("p.png").toString());
        }
        else{
            image = new Image(ClassLoader.getSystemResource("s.png").toString());
        }
        return image;
    }

    public static ArrayList<Item> generateReward() {
        ArrayList<Item> items = new ArrayList<Item>();
        int n = (int) (Math.random() * 3) + 1;

        for (int i=0; i<n; i++) {
            double random = Math.random();
            if (random < 0.2) {
                items.add(new component.item.equipment.Armor());
            } else if (random < 0.4) {
                items.add(new component.item.equipment.Amulet());
            } else if (random < 0.6) {
                items.add(new component.item.equipment.Weapon());
            } else if (random < 0.8) {
                items.add(new component.item.HpPotion(10));
            } else {
                items.add(new component.item.RefineIron());
            }
        }

        return items;
    }
}
