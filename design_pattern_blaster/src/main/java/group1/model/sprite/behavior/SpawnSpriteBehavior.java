package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.model.sprite.Sprite;

public class SpawnSpriteBehavior implements Behavior
{
	private Sprite blueprint;
	int offsetX, offsetY;
	
	public SpawnSpriteBehavior(int offsetX, int offsetY, Sprite blueprint)
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.blueprint = blueprint;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type", "SpawnSpriteBehavior");
		json.put("blueprint", blueprint.save());
		json.put("offsetX", offsetX);
		json.put("offsetY", offsetY);
		return json;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		int x = (int)sprite.getX() + offsetX;
		int y = (int)sprite.getY() + offsetY;
		System.out.println("SPAWNING at " + x + "," + y);
		Sprite newSprite = blueprint.copy();
		newSprite.setX(x);
		newSprite.setY(y);
		App.model.addSprite(newSprite);
	}

}
