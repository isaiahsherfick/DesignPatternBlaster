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
public class TimeScoreBehavior implements Behavior {

    private double timeElapsed = 1;
    private boolean scoreFunctionality = true;
    private int maxScore = 10000;
    private int minScore = 500;
    private int timeBeforeMinScore = 30;
    private int decreasePerSecond = (maxScore - minScore)/(timeBeforeMinScore-1);

    public TimeScoreBehavior(boolean scoreFunctionality) {
        this.scoreFunctionality = scoreFunctionality;
    }
    @Override
    public void performBehavior(Sprite sprite) {
        timeElapsed += App.model.getTimeDelta();
        double roundedTime = getRounded(timeElapsed);
        double roundedScore = Math.max(minScore, getRounded(maxScore - (decreasePerSecond* timeElapsed)));
        if (!scoreFunctionality)
            sprite.getAnimation().primeAnimationForStringDisplay("Time Elapsed: " + roundedTime, sprite.getX(), sprite.getY());
        else
            sprite.getAnimation().primeAnimationForStringDisplay("Score: " + roundedScore, sprite.getX(), sprite.getY());

    }

    private double getRounded(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double rounded = bd.doubleValue();
        return rounded;
    }

    @Override
    public Behavior copy() {
        return new TimeScoreBehavior(scoreFunctionality);
    }
}


