package group1.model.sprite.behavior;

import group1.App;
import group1.model.sprite.Sprite;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Encapsulates the display of time on the screen
 * Can be attached to any sprite as the only information taken from the sprite is location
 * "dummySprite()" factory serves as an ideal candidate to hold this behavior
 */
public class TimerBehavior implements Behavior {

    private double timeElapsed = 30;

    public TimerBehavior() {


    }
    @Override
    public void performBehavior(Sprite sprite) {
        timeElapsed += App.model.getTimeDelta();
        double rounded = getRounded(timeElapsed);
        sprite.getAnimation().primeAnimationForStringDisplay("Time: " + rounded, sprite.getX(), sprite.getY());

    }

    private double getRounded(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double rounded = bd.doubleValue();
        return rounded;
    }

    @Override
    public Behavior copy() {
        return new TimerBehavior();
    }
}


