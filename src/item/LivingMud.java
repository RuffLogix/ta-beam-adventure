package item;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LivingMud extends Item{
    private int efficiency;
    public LivingMud(int price){
        super(price,"LivingMud");

    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency() {
        List<Integer> list = Arrays.asList(0,1);
        Random random = new Random();
        int randomNumber = list.get(random.nextInt(list.size()));
        this.efficiency = randomNumber;
    }
}
