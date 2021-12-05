package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;
import group1.physics.Vector;
import group1.model.sprite.NullSprite;

public class ShootAtTargetBehavior implements Behavior
{
	private Sprite blueprint, target;
	int offsetX, offsetY;
	
	public ShootAtTargetBehavior(int offsetX, int offsetY, Sprite blueprint, Sprite target)
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.blueprint = blueprint;
		this.target = target;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
        double x,y;
        
        //Spawn the copy of the blueprint sprite
        Sprite newSprite = blueprint.copy();
        double velocityX = newSprite.getVelocityX();
        
        if (sprite.getX() < target.getX())
        {
        	newSprite.setDirection(Constants.RIGHT);
        	x = (int) (sprite.getX() + offsetX + blueprint.getWidth()/2);
            y = sprite.getY() + offsetY;
        }
        else
        {
        	newSprite.setDirection(Constants.LEFT);
        	x = (int)(sprite.getX() - (blueprint.getWidth()+1));
            y = sprite.getY() + offsetY;
            velocityX *= -1;
        }

        
        //Set its position
        newSprite.setX(x);
        newSprite.setY(y);
        
        
        newSprite.setVelocityX(velocityX);
//        newSprite.setVelocityY(velocityY);
        //System.out.println("VelocityX : " + velocityX);
        //System.out.println("VelocityY : " + velocityY);

//        newSprite.setDirection(sprite.getDirection());
        App.model.addSprite(newSprite);
	}
	
	public Behavior copy()
	{
		return new ShootAtTargetBehavior(offsetX, offsetY, blueprint.copy(), target);
	}
}
