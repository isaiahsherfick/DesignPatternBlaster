package group1.model.sprite.behavior;

import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;
import group1.App;

public class ChangeAnimationStateWhenDamagedBehavior implements Behavior
{
	
	private AnimationState damagedState, previousState;
	private double timeSinceTakenDamage;
	private Integer spriteHealth;
	
	public ChangeAnimationStateWhenDamagedBehavior(AnimationState stateToChangeTo)
	{
		damagedState = stateToChangeTo;
		timeSinceTakenDamage = 0.0;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		if (spriteHealth == null)
		{
			spriteHealth = sprite.getHealth();
		}
		if (sprite.getAnimation().getState() != damagedState && sprite.getHealth() != spriteHealth)
		{
			previousState = sprite.getAnimation().getState();
			sprite.getAnimation().setState(damagedState);
			timeSinceTakenDamage = 0.0;
			spriteHealth = sprite.getHealth();
		}
		else
		{
			timeSinceTakenDamage += App.model.getTimeDelta();
			if (sprite.getAnimation().getState() == damagedState && timeSinceTakenDamage > 0.1)
			{
				sprite.getAnimation().setState(previousState);
			}
		}
	}

	@Override
	public Behavior copy() 
	{
		return new ChangeAnimationStateWhenDamagedBehavior(damagedState);
	}

}
