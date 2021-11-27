package group1.model.sprite.behavior;

import java.util.ArrayList;
import java.util.Iterator;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class SingletonPoolBehavior implements Behavior {

//	private ArrayList<Sprite> spritePool;
	private int spawnIndex;

	public SingletonPoolBehavior()
	{
		spawnIndex = Constants.DEFAULT_POOL_SPAWN_INDEX;
//		spritePool = new ArrayList<Sprite>();
	}

	public void setSpawnIndex(int spawnIndex) {
		this.spawnIndex = spawnIndex;
	}

	@Override
	public void performBehavior(Sprite sprite)
	{
//		Sprite s;
//		Iterator<Sprite> iter = spritePool.iterator();
//		while(iter.hasNext())
//		{
//			s = iter.next();
//			if(s.isEnabled())
//			{
//				continue;
//			}
//			s.setX(1000);
//			s.setY(Constants.FLOOR_Y-100);
//			s.setEnabled(true);
//			System.out.println("This is the pool");
//			App.model.addSprite(s);
//		}
//		for(Sprite s: spritePool) {
//			if(s.isEnabled())
//				continue;
//
//			s.setX(1000);
//			s.setY(Constants.FLOOR_Y-100);
//			s.enable();
//			System.out.println("This is the pool");

//		}

		if(!sprite.isEnabled())
		{
			sprite.setX(Constants.SPRITE_POOL_X + (sprite.getWidth()+50)*spawnIndex);
			sprite.setY(Constants.FLOOR_Y-100);
			sprite.enable();
//			System.out.println("This is the pool");
		}
	}

	@Override
	public Behavior copy()
	{
		// TODO Auto-generated method stub
		return null;
	}

//	public void setSpritePool(ArrayList<Sprite> spritePool)
//	{
//		this.spritePool = spritePool;
//	}

}
