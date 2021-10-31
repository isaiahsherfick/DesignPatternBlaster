package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class FaceLeftBehavior implements Behavior
{

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type", "ChangeDirectionBehavior");
		return json;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		sprite.setDirection(Constants.LEFT);
	}
	
}
