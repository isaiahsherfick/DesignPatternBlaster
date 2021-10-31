package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.model.sprite.Sprite;

//Null object for Behavior interface
public class DoNothingBehavior implements Behavior 
{

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","DoNothingBehavior");
		return json;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		//Do nothing
		return;
	}

}
