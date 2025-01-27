package component.bossfight;

import component.player.Player;
import javafx.scene.image.ImageView;

public abstract class Boss{
    private int hp;
    private ImageView imageView;
    private UnitUtils.Outcome choice;
    public static int max_hp;
    public Boss(){
       max_hp = Player.level*50;
        setHp(max_hp);
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
