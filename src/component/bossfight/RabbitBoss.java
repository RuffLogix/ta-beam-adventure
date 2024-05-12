package component.bossFight;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RabbitBoss extends Boss {
    public RabbitBoss(){
        super();
        Image image = new Image(ClassLoader.getSystemResource("rabbit.png").toString());
        ImageView imageView = new ImageView(image);
        setImageView(imageView);
    }
    public void shoot(){
        UnitUtils.Outcome choice = UnitUtils.generateOutcome((double) 1 /3,(double) 1/3,(double) 1/3);
        setChoice(choice);
    }
}
