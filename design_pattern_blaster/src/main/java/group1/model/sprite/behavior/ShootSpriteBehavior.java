package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class ShootSpriteBehavior implements Behavior {
    private Sprite blueprint;
    int offsetX, offsetY;

    public ShootSpriteBehavior(int offsetX, int offsetY, Sprite blueprint) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.blueprint = blueprint;
    }


    @Override
    public void performBehavior(Sprite sprite) {
        int x;
        if (sprite.getDirection() == Constants.RIGHT) {


            x = (int) sprite.getX() + offsetX;


        } else {
            x = (int) sprite.getX() + offsetX;
        }
        int y = (int) sprite.getY() + offsetY;
        Sprite newSprite = blueprint.copy();
        newSprite.setX(x);
        newSprite.setY(y);
        newSprite.setDirection(sprite.getDirection());
        App.model.addSprite(newSprite);
    }

    public Behavior copy() {
        return new ShootSpriteBehavior(offsetX, offsetY, blueprint.copy());
    }


}
