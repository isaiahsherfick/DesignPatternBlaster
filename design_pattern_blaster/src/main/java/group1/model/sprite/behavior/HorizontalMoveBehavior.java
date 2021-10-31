package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class HorizontalMoveBehavior implements Behavior
{

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type", "HorizontalMoveBehavior");
		return json;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		if (sprite.getDirection() == Constants.LEFT)
		{
			sprite.setX(sprite.getX() - sprite.getVelocityX());
		}
		else
		{
			sprite.setX(sprite.getX() + sprite.getVelocityX());
		}
	}
	
}
