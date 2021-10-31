package group1.model.sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import group1.App;
import group1.model.level.Level;

public class SpriteManager 
{
		private HashMap<Integer, Sprite> spriteMap;
		private int highestSpriteId;
		
		public SpriteManager()
		{
			this.spriteMap = new HashMap<>();
			highestSpriteId = -1;
		}
		
		public void addSprite(Sprite s) 
		{
			if (s.getSpriteId() > highestSpriteId)
			{
				highestSpriteId = s.getSpriteId();
				spriteMap.put(s.getSpriteId(), s);
			}
			else
			{
				highestSpriteId++;
				s.setSpriteId(highestSpriteId);
				spriteMap.put(s.getSpriteId(),s);
			}
		}
		
		public Sprite getSprite(int spriteId) 
		{
			return spriteMap.get(spriteId);
		}
		
		public void modifySprite(Sprite newSprite) 
		{
			//If the sprite exists already
			if (spriteMap.containsKey(newSprite.getSpriteId()))
			{
				//Replace the sprite stored there with the new version
				spriteMap.put(newSprite.getSpriteId(), newSprite);
			}
		}
		
		//Receive a game event, send it to all the sprites
		public void updateSprites(GameEvent g) 
		{
			Iterator<Sprite> spriteIterator = spriteMap.values().iterator();
			while (spriteIterator.hasNext())
			{
				spriteIterator.next().respondToEvent(g);
			}
			
			//Tell the model to update the view since the sprites have changed
			App.model.notifyObservers();
		}
		
		public ArrayList<Sprite> getSpriteList()
		{
			ArrayList<Sprite> allSprites = new ArrayList<>();
			Iterator<Sprite> spriteIterator = spriteMap.values().iterator();
			while (spriteIterator.hasNext())
			{
				allSprites.add(spriteIterator.next());
			}
			return allSprites;
		}
		
		public void clearAllSprites() 
		{
			spriteMap.clear();
		}
		
		public void loadLevel(Level level) 
		{
			ArrayList<Sprite> allSprites = new ArrayList<>();
			Iterator<Sprite> spriteIterator = spriteMap.values().iterator();
			while (spriteIterator.hasNext())
			{
				allSprites.add(spriteIterator.next());
			}
		}

		public ArrayList<Sprite> getSpriteListByLayer(int layer) 
		{
			ArrayList<Sprite> spritesInTargetLayer = new ArrayList<>();
			Iterator<Sprite> spriteIterator = spriteMap.values().iterator();
			while (spriteIterator.hasNext())
			{
				Sprite current = spriteIterator.next();
				if (current.getLayer() == (layer))
				{
					spritesInTargetLayer.add(current);
				}
			}
			return spritesInTargetLayer;
		}

		public Set<Integer> getLayerSet() 
		{
			SortedSet<Integer> layers = new TreeSet<>();
			Iterator<Sprite> spriteIterator = spriteMap.values().iterator();
			while (spriteIterator.hasNext())
			{
				layers.add(spriteIterator.next().getLayer());
			}
			return layers;
		}

		public int getNumberOfSprites() 
		{
			return spriteMap.size();
		}		
}
