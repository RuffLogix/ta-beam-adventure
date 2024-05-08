package equipment;

import item.EnrichedBone;
import javafx.scene.image.ImageView;

public class Armor extends Equipment implements Upgradable{
    private int defense;
    private int level;
    private final int MAX_LEVEL = 5;
    public Armor(String name, int durability, ImageView imageView, int defense){
        super(name,imageView,durability);
        setLevel(1);
        setDefense(defense);
    }
    public void upgrade(EnrichedBone enrichBone){
        if(level < MAX_LEVEL){
            setLevel(this.getLevel() +  enrichBone.getPower());
        }
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = Math.max(0,defense);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Math.max(1,level);
    }

    public int getMAX_LEVEL() {
        return MAX_LEVEL;
    }
}
