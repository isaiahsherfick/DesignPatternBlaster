package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.model.sprite.Sprite;

public class LoadNextLevelBehavior implements Behavior
{

	@Override
	public void performBehavior(Sprite sprite) 
	{
		App.model.loadNextLevel();
	}
	
	public Behavior copy()
	{
		return new LoadNextLevelBehavior();
	}
	
}
