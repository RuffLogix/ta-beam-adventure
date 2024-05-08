package unit;

import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;

public class Monster extends BasedUnit{
    private int power;
    public Monster(int hp, Point2D position, ImageView imageview, int power, int level){
        super(hp,position,imageview);
        setPower(power);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = Math.max(0,power);
    }
}
