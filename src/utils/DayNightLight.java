package utils;

import javafx.animation.FadeTransition;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import view.ViewManager;

public class DayNightLight extends Pane {
    private FadeTransition toNightTransition;
    private FadeTransition toDayTransition;
    private boolean isDay;
    public DayNightLight() {
            isDay = false;
            setPrefSize(ViewManager.WINDOW_WIDTH*20, ViewManager.WINDOW_HEIGHT*20);
            setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 50, 0.55), null, null)));

            toNightTransition = new FadeTransition();
            toNightTransition.setNode(this);
            toNightTransition.setDuration(Duration.seconds(3));
            toNightTransition.setFromValue(0);
            toNightTransition.setToValue(1);

            toDayTransition = new FadeTransition();
            toDayTransition.setNode(this);
            toDayTransition.setDuration(Duration.seconds(3));
            toDayTransition.setFromValue(1);
            toDayTransition.setToValue(0);
    }

    public void toNight() {
        if(isDay){
            toNightTransition.play();
            isDay = false;
        }
    }

    public void toDay() {
        if(!isDay){
            toDayTransition.play();
            isDay = true;
        }
    }
}
