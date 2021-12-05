package group1.model.sprite.behavior;

import java.util.HashMap;
import java.util.Map.Entry;
import group1.App;

import group1.model.sprite.Sprite;

public class AbstractFactoryBehavior implements Behavior
{
	private HashMap<Integer, Sprite> blueprintFamily; //Maps xOffset from the factory to Sprite
	
	private boolean spawned;
	
	public AbstractFactoryBehavior(HashMap<Integer, Sprite> blueprintMap)
	{
		blueprintFamily = blueprintMap;
		spawned = false;
	}
	
	
	@Override
	public void performBehavior(Sprite sprite) 
	{
		if (!spawned)
		{
			spawned = true;
			for (Entry<Integer, Sprite> e : blueprintFamily.entrySet())
			{
				int offsetX = e.getKey();
				Sprite blueprint = e.getValue();
				blueprint.setX(sprite.getX() + offsetX);
				App.model.addSprite(blueprint);
			}
		}
	}
	@Override
	public Behavior copy() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
