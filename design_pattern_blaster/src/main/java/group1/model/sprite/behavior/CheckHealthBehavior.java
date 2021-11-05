package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.model.sprite.Sprite;
import group1.model.sprite.game_event.GameEvent;

public class CheckHealthBehavior implements Behavior
{

	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		// TODO Auto-generated method stub
		return json;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		if (sprite.getHealth() <= 0)
		{
			sprite.respondToEvent(GameEvent.HealthDepletedEvent());
		}
	}

}
