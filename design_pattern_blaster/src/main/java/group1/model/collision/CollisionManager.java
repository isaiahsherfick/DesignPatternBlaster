package group1.model.collision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import group1.model.sprite.SpriteManager;
import group1.model.sprite.behavior.Behavior;
import group1.model.sprite.behavior.DoNothingBehavior;

public class CollisionManager
{
	//Check for collisions between all sprites in the spriteManager
	public void checkCollisions(SpriteManager spriteManager)
	{
		//Get the set of layers
		Set<Integer> layerSet = spriteManager.getLayerSet();

		//For each layer
		for (Integer layer : layerSet)
		{
			//Get the sprites in that layer
			ArrayList<Sprite> spritesInLayer = spriteManager.getSpriteListByLayer(layer);

			//Iterate over them once
			Iterator<Sprite> firstIterator = spritesInLayer.iterator();
			while (firstIterator.hasNext())
			{
				//This is the sprite we're going to modify if it's colliding with something
				Sprite currentSprite = firstIterator.next();

				//Get its hitbox
				HitBox currentHitBox = currentSprite.getHitBox();

				//Iterate through the list of sprites in the layer
				Iterator<Sprite> secondIterator = spritesInLayer.iterator();
				while (secondIterator.hasNext())
				{
					Sprite spriteToCheck = secondIterator.next();

					//Avoid checking if the sprite collides with itself
					if (spriteToCheck.getSpriteId() != currentSprite.getSpriteId() && !checkIfDoNothingCollision(currentSprite, spriteToCheck))
					{
						HitBox hitBoxToCheck = spriteToCheck.getHitBox();

						//If the hitboxes overlap
						if (currentHitBox.overlaps(hitBoxToCheck) != HitBoxOverlapType.NO_OVERLAP)
						{
							//Execute the sprite's collision behavior for that sprite
							currentSprite.collideWith(spriteToCheck);
						}
					}
				}
			}
		}
	}

    //Returns true if the sprite is colliding with another sprite which has sprite classid constant FLOOR. 
    //could be more efficient if we use a hash table and update it in checkCollisions(), but this is quicker for now
    //used for gravity behavior 
    public boolean isCollidingWithFloor(Sprite sprite, SpriteManager spriteManager)
    {
        int layer = sprite.getLayer();
        HitBox currentHitBox = sprite.getHitBox();
        ArrayList<Sprite> spritesInLayer = spriteManager.getSpriteListByLayer(layer);

        //Iterate over them 
        Iterator<Sprite> spriteIterator = spritesInLayer.iterator();
        while (spriteIterator.hasNext())
        {
            Sprite spriteToCheck = spriteIterator.next();
            //If the sprite is a floor
            if (spriteToCheck.getSpriteClassId() == SpriteClassIdConstants.FLOOR)
            {
                HitBox hitBoxToCheck = spriteToCheck.getHitBox();

                //If the hitboxes overlap
                if (currentHitBox.overlaps(hitBoxToCheck) != HitBoxOverlapType.NO_OVERLAP)
                {
                    return true;
                }
            }
        }
        return false;
    }

	private boolean checkIfDoNothingCollision(Sprite currentSprite, Sprite spriteToCheck) {
		HashMap<Integer, Behavior> collisionBehaviorMap = currentSprite.getCustomCollisionMap().getCollisionBehaviorMap();
		Behavior behavior = collisionBehaviorMap.get(spriteToCheck.getSpriteId());
		if(behavior instanceof DoNothingBehavior) {
			return true;
		}
		else if(behavior == null) {

			behavior = collisionBehaviorMap.get(spriteToCheck.getSpriteClassId());
			if(behavior instanceof DoNothingBehavior) {
				return true;
			}
			else if(behavior == null) {
				if(currentSprite.getCustomCollisionMap().getDefaultCollisionBehavior() instanceof DoNothingBehavior) {
					return true;
				}
			}
		}
		return false;
	}
}
