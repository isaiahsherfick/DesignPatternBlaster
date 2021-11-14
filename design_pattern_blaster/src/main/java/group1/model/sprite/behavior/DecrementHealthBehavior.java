package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;

public class DecrementHealthBehavior implements Behavior
{
	
	private int amount;
	
	public DecrementHealthBehavior()
	{
		amount = 1;
	}
	
	public DecrementHealthBehavior(int amount)
	{
		this.amount = amount;
	}
	public void performBehavior(Sprite sprite)
	{
		sprite.setHealth(sprite.getHealth() - amount);
	}
	
	public Behavior copy()
	{
		return new DecrementHealthBehavior();
	}

}
