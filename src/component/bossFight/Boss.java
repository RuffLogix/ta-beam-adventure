package component.bossFight;

import component.player.Player;
import javafx.scene.image.ImageView;

public abstract class Boss{
    private int hp;
    private ImageView imageView;
    private int power;
    private int level;
    private UnitUtils.Outcome choice;
    public Boss(){
        setHp(Player.level*20);
        setPower(5);
        setLevel(1);
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

    public void setChoice(UnitUtils.Outcome choice) {
        this.choice = choice;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0,hp);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public UnitUtils.Outcome getChoice() {
        return choice;
    }
    public abstract void shoot();
}
