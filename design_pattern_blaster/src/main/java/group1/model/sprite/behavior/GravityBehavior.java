package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.model.sprite.Sprite;

public class GravityBehavior implements Behavior 
{
	private double gravity;
	public GravityBehavior(double gravity) 
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
        return new GravityBehavior(gravity);
	}

	@Override
	public void performBehavior(Sprite sprite) 
    {
		double velY = sprite.getVelocityY();
		velY = velY + gravity * App.model.getTimeDelta(); // v = u + gt
        if (velY == 0)
        {
            velY = gravity;
        }
		sprite.setVelocityY(velY);
	}
}
