package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class ShootSpriteBehavior implements Behavior
{
	private Sprite blueprint;
	int offsetX, offsetY;
	
	public ShootSpriteBehavior(int offsetX, int offsetY, Sprite blueprint)
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
		if (sprite.getDirection() == Constants.RIGHT)
		{
			int x = (int)sprite.getX() + offsetX;
			int y = (int)sprite.getY() + offsetY;
			Sprite newSprite = blueprint.copy();
			newSprite.setX(x);
			newSprite.setY(y);
			newSprite.setDirection(sprite.getDirection());
			App.model.addSprite(newSprite);
		}
		else
		{
			int x = (int)sprite.getX() - offsetX;
			int y = (int)sprite.getY() + offsetY;
			Sprite newSprite = blueprint.copy();
			newSprite.setX(x);
			newSprite.setY(y);
			newSprite.setDirection(sprite.getDirection());
			App.model.addSprite(newSprite);
		}
	}

}
