package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.model.sprite.Sprite;

//Null object for Behavior interface
public class DoNothingBehavior implements Behavior 
{
	@Override
	public void performBehavior(Sprite sprite) 
	{
		//Do nothing
		return;
	}
	
	public Behavior copy()
	{
		return new DoNothingBehavior();
	}

}
