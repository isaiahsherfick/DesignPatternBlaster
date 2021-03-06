package group1.model.sprite.behavior;

import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;

public class HorizontalObserverPlatformBehavior implements ObserverBehaviorI
{
	
	private int maxX;
	private boolean updated;
	private double xVelocity;
	
	public HorizontalObserverPlatformBehavior(int maxX, double xVelocity)
	{
		this.xVelocity = xVelocity;
		this.maxX = maxX;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		if (updated && sprite.getX() < maxX)
		{
			sprite.setX(sprite.getX() + xVelocity);
			sprite.getAnimation().setState(AnimationState.LEFT_MOVEMENT);
			updated = false;
		}
		else 
		{
			sprite.getAnimation().setState(AnimationState.IDLE);
		}
	}

	@Override
	public Behavior copy() 
	{
		return null;
	}

	@Override
	public void update() 
	{
		updated = true;
	}

}
