package component.bossfight;//import item.HpPotion;
//import item.Item;

import javafx.scene.image.Image;

public class UnitUtils {
//    public static ArrayList<Item> generateItemList(){
//        ArrayList<Item> itemList = new ArrayList<Item>();
//        itemList.add(new HpPotion(5));
//        return itemList;
//    }
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
    public static Outcome generateOutcome(double probRock, double proPaper, double probScissors){
        Outcome outcome;
        double randomNumber = Math.random();
        if(randomNumber <= probRock){outcome = Outcome.ROCK;}
        else if(randomNumber <= probRock+proPaper){outcome = Outcome.PAPER;}
        else{outcome = Outcome.SCISSORS;}
        return outcome;
    }
//    public static ArrayList<Item> generateLootDrop(){
//        ArrayList<Item> list = new ArrayList<Item>();
//        return list;
//    }
//    public static ArrayList<Item> generateRareLootDrop(){
//        ArrayList<Item> list = new ArrayList<Item>();
//        return list;
//    }
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
}
