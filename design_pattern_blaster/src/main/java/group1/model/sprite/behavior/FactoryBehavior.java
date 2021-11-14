package group1.model.sprite.behavior;

import group1.App;
import group1.model.sprite.Sprite;

public class FactoryBehavior implements Behavior
{
	
	private Sprite blueprint;
	private double offsetX, offsetY;
	private int spawnInterval;
	private double counter;
	
	public FactoryBehavior(Sprite blueprint, int spawnInterval)
	{
		this.blueprint = blueprint;
		this.spawnInterval = spawnInterval;
		counter = spawnInterval;
	}
	
	public void performBehavior(Sprite sprite) 
	{
		if (counter >= spawnInterval)
		{
			Sprite newEnemy = blueprint.copy();
			newEnemy.setX(sprite.getX() + offsetX);
			newEnemy.setY(sprite.getY() + offsetY);
			App.model.addSprite(newEnemy);
			System.out.println("FactoryBehavior.java: Spawned the new enemy!");
			counter = 0;
		}
		else
		{
			counter += App.model.getTimeDelta();
		}
	}
	
	public Behavior copy()
	{
		return new FactoryBehavior(blueprint.copy(), spawnInterval);
	}

}
