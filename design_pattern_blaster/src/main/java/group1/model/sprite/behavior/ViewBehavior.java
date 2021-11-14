package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class ViewBehavior implements Behavior {

    private final int secondsBetweenShots;

    private double counter = 0.0;

    private Behavior shootBehavior;

    public ViewBehavior() {
        secondsBetweenShots = Constants.OBSERVER_SECONDS_BETWEEN_SHOTS;
    }


    public void setShootSpriteBehavior(Behavior ssb) {
        shootBehavior = ssb;
    }

    @Override
    public void performBehavior(Sprite sprite) {
        counter += App.model.getTimeDelta();

        if (counter > secondsBetweenShots) {
            shootBehavior.performBehavior(sprite);
            counter = 0.0;
        }
    }
}


