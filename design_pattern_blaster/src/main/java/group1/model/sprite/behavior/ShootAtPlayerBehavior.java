package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class ShootAtPlayerBehavior implements Behavior
{
	private Sprite blueprint;
	int offsetX, offsetY;
	
	public ShootAtPlayerBehavior(int offsetX, int offsetY, Sprite blueprint)
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
		ArrayList<Sprite> playerSprites = App.model.getPlayerSprites();
        Sprite nearestPlayerSprite = new NullSprite();
        for (Sprite s : playerSprites)
        {
            //System.out.println(s.getX());
            if (Math.abs(s.getX() - x) < minDistance)
            {
                nearestPlayerSprite = s;
                minDistance = Math.abs(s.getX() - x);
            }
        }

        //Spawn the bullet sprite
        Sprite newSprite = blueprint.copy();
        newSprite.setX(x);
        newSprite.setY(y);
        newSprite.setDirection(sprite.getDirection());
        App.model.addSprite(newSprite);
	}

}
