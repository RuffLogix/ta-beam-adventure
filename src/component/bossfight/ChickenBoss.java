package component.bossfight;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChickenBoss extends Boss{
    public ChickenBoss(){
        super();
        Image image = new Image(ClassLoader.getSystemResource("chicken.png").toString());
        ImageView imageView = new ImageView(image);
        setImageView(imageView);
    }
    public void shoot(){
        UnitUtils.Outcome choice = UnitUtils.generateOutcome(0.3,0.3,0.4);
        setChoice(choice);
    }
}
