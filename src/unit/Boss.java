package unit;

import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;

public class Boss extends BasedUnit{
    private int power;
    private int level;
    public Boss(int hp, Point2D position, ImageView imageview, int power, int level){
        super(hp,position,imageview);
        setPower(power);
        setLevel(level);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = Math.max(0,power);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Math.max(1,level);
    }
}
