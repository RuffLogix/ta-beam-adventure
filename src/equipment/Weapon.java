package equipment;

import item.EnrichedBone;
import javafx.scene.image.ImageView;

public class Weapon extends Equipment implements Upgradable{
    private int damage;
    private int level;
    private final int MAX_LEVEL = 5;

    public Weapon(String name, int durability, ImageView imageView, int damage){
        super(name,imageView,durability);
       setLevel(1);
       setDamage(damage);
    }
    public void upgrade(EnrichedBone enrichBone){
        if(level < MAX_LEVEL){
            setLevel(this.getLevel() +  enrichBone.getPower());
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = Math.max(0,damage);
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
