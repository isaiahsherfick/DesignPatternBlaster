package group1.model.sprite.behavior;

import group1.App;
import java.util.Iterator;
import java.util.ArrayList;
import group1.model.sprite.Sprite;

public class PlayerGravityBehavior implements Behavior 
{
	private double gravity;
	public PlayerGravityBehavior(double gravity) 
    {
		this.gravity = gravity;
	}

	public void setGravity(double gravity) 
    {
		this.gravity = gravity;
	}

	public double getGravity() 
    {
		return gravity;
	}

	@Override
	public Behavior copy() 
    {
        return new PlayerGravityBehavior(gravity);
	}

	@Override
	public void performBehavior(Sprite sprite) 
    {
        double velY = sprite.getVelocityY();
        if (velY != 0)
        {
            velY = velY + gravity * App.model.getTimeDelta(); // v = u + gt
            sprite.setVelocityY(velY);
        }
        else
        {
            //Determine if the sprite is touching a floor
            ArrayList<Sprite> floors = App.model.getFloorSprites();
            Iterator<Sprite> iter = floors.iterator();
            while (iter.hasNext())
            {
                Sprite floor = iter.next();
                if (sprite.getY()  == floor.getY() - sprite.getHeight() - 1) //The sprite is level with this floor
                {
                    if (sprite.getX() > floor.getX() + floor.getWidth() || sprite.getX() + sprite.getWidth() < floor.getX()) // The sprite has walked off the cliff
                    {
                        sprite.setVelocityY(-0.1);
                    }
                }
            }
        }
    }
}
