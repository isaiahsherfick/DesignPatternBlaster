package group1.model.collision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteManager;

public class CollisionManager 
{
	public void checkCollisions(SpriteManager spriteManager)
	{
		Set<Integer> layerSet = spriteManager.getLayerSet();
		for (Integer layer : layerSet)
		{
			ArrayList<Sprite> spritesInLayer = spriteManager.getSpriteListByLayer(layer);
			Iterator<Sprite> firstIterator = spritesInLayer.iterator();
			while (firstIterator.hasNext())
			{
				Sprite currentSprite = firstIterator.next();
				HitBox currentHitBox = currentSprite.getHitBox();
				Iterator<Sprite> secondIterator = spritesInLayer.iterator();
				while (secondIterator.hasNext())
				{
					Sprite spriteToCheck = secondIterator.next();
					HitBox hitBoxToCheck = spriteToCheck.getHitBox();
					if (currentHitBox.overlaps(hitBoxToCheck) != HitBoxOverlapType.NO_OVERLAP)
					{
						currentSprite.collideWith(spriteToCheck);
					}
				}
			}
		}
	}
}
