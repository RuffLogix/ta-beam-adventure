package component.bossFight;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SheepBoss extends Boss {
    public SheepBoss(){
        super();
        Image image = new Image(ClassLoader.getSystemResource("sheep.png").toString());
        ImageView imageView = new ImageView(image);
        setImageView(imageView);
    }
    public void shoot(){
        UnitUtils.Outcome choice = UnitUtils.generateOutcome(0.3,0.4,0.3);
       setChoice(choice);
    }
}
