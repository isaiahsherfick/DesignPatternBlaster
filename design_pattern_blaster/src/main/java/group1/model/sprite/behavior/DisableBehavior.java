package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.model.sprite.Sprite;

public class DisableBehavior implements Behavior
{
	@Override
	public void performBehavior(Sprite sprite) 
	{
		sprite.disable();
	}
	
	public Behavior copy()
	{
		return new DisableBehavior();
	}

}
