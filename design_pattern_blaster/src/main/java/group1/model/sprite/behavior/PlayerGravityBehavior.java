package group1.model.sprite.behavior;

import group1.App;
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
        velY = velY + gravity * App.model.getTimeDelta(); // v = u + gt
        sprite.setVelocityY(velY);
	}
}
