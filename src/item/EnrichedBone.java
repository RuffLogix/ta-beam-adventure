package item;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnrichedBone extends Item{
    private int power;

    public EnrichedBone(int price) {
        super(price, "EnrichedBone");
        setPower();
    }

    public int getPower() {
        return power;
    }

    public void setPower() {
        List<Integer> list = Arrays.asList(1,1,1,2,2,3);
        Random random = new Random();
        int randomNumber = list.get(random.nextInt(list.size()));
        this.power = randomNumber;
    }
}
