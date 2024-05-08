package equipment;

import item.LivingMud;
import javafx.scene.image.ImageView;

public abstract class Equipment {
    private String name;
    private ImageView imageview;
    private int durability;

    public Equipment(String name, ImageView imageview, int durability) {
        setName(name);
        setDurability(durability);
        setImageview(imageview);
    }
    public void repair(LivingMud livingMud){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getImageview() {
        return imageview;
    }

    public void setImageview(ImageView imageview) {
        this.imageview = imageview;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = Math.max(0,durability);
    }
}
