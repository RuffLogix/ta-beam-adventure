package component.item;

import component.player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class HpPotion extends Item {
    private int healAmount;
    private static final String IMAGE_PATH = ClassLoader.getSystemResource("sprite/item.png").toString();
    private static final Image IMAGE = new Image(IMAGE_PATH);
    private static WritableImage writableImage = new WritableImage(IMAGE.getPixelReader(), 11, 3, 40, 52);

    public HpPotion(int healAmount) {
        super("Hp Potion", writableImage);
        setHealAmount(healAmount);
    }

    @Override
    public void use() {
        Player player = Player.getInstance();
        player.heal(healAmount);
    }

    public void setHealAmount(int healAmount) {
        healAmount = Math.max(0, healAmount);
        this.healAmount = healAmount;
    }
}
