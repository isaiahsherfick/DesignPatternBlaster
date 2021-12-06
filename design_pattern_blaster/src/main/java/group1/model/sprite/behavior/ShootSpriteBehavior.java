package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;
import javafx.scene.paint.Color;

public class ShootSpriteBehavior implements Behavior
{
    private Sprite blueprint;
    int offsetX, offsetY;
    Color spriteColor;

    public ShootSpriteBehavior(int offsetX, int offsetY, Sprite blueprint, Color spriteColor)
    {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.blueprint = blueprint;
        this.spriteColor = spriteColor;
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
        newSprite.setX(x);
        newSprite.setY(y);
        newSprite.setColor(spriteColor);
        newSprite.setDirection(sprite.getDirection());
        App.model.addSprite(newSprite);
    }

    public Behavior copy()
    {
        return new ShootSpriteBehavior(offsetX, offsetY, blueprint.copy(), spriteColor);
    }
}
