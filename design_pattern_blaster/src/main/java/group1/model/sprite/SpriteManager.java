package group1.model.sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import group1.App;
import group1.model.level.Level;

public class SpriteManager 
{
		private HashMap<Integer, Sprite> spriteMap;
		private int highestSpriteId;
		private HashMap<Integer, HashMap<Integer, Sprite>> spriteLayerMap;		//stores sprites of same layers together
		
		public SpriteManager()
		{
			this.spriteMap = new HashMap<>();
			this.spriteLayerMap = new HashMap<>();
			highestSpriteId = 0;
		}
		
		public void addSprite(Sprite s) 
		{
			if (s.getId() > highestSpriteId)
			{
				highestSpriteId = s.getId();
				spriteMap.put(s.getId(), s);
				spriteLayerMap.put(s.getLayer(), spriteMap);
			}
			else
			{
				highestSpriteId++;
				s.setId(highestSpriteId);
				spriteMap.put(s.getId(),s);
				spriteLayerMap.put(s.getLayer(), spriteMap);
			}
		}
		
		public Sprite getSprite(int spriteId) 
		{
			return spriteMap.get(spriteId);
		}
		
		public void modifySprite(Sprite newSprite) 
		{
			int newSpriteId = newSprite.getId();
			Sprite oldSprite = spriteMap.get(newSpriteId);
			oldSprite.setX(newSprite.getX());
			oldSprite.setY(newSprite.getY());
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
}
