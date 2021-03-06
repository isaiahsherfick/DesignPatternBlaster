package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class ShootSpriteBehavior2 implements Behavior
{
    private Sprite blueprint;
    int offsetX, offsetY;

    public ShootSpriteBehavior2(int offsetX, int offsetY, Sprite blueprint)
    {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.blueprint = blueprint;
    }

    @Override
    public void performBehavior(Sprite sprite)
    {
        int x;
        if (sprite.getDirection() == Constants.RIGHT)
        {
            x = (int) (sprite.getX() + offsetX + blueprint.getWidth()/2);
        }
        else
        {
            x = (int)(sprite.getX() - (blueprint.getWidth()+1));
        }
        int y = (int) sprite.getY() + offsetY;
        Sprite newSprite = blueprint.copy();
        
        double velocityX = newSprite.getVelocityX();
        velocityX *= -1;
        newSprite.setX(x);
        newSprite.setY(y);
        newSprite.setVelocityX(velocityX);
        newSprite.setDirection(sprite.getDirection());
        App.model.addSprite(newSprite);
    }

    public Behavior copy()
    {
        return new ShootSpriteBehavior2(offsetX, offsetY, blueprint.copy());
    }
}
