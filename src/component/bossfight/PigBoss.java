package component.bossfight;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PigBoss extends Boss {
    public PigBoss(){
        super();
        Image image = new Image(ClassLoader.getSystemResource("Pig.png").toString());
        ImageView imageView = new ImageView(image);
        setImageView(imageView);
    }
    public void shoot(){
        UnitUtils.Outcome choice = UnitUtils.generateOutcome(0.4,0.3,0.3);
        setChoice(choice);
    }
}
