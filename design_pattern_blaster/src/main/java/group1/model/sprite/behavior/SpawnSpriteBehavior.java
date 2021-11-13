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
	public void performBehavior(Sprite sprite) 
	{
		int x = (int)sprite.getX() + offsetX;
		int y = (int)sprite.getY() + offsetY;
		Sprite newSprite = blueprint.copy();
		newSprite.setX(x);
		newSprite.setY(y);
		App.model.addSprite(newSprite);
	}

}
