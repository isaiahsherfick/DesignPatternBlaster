package group1.model.sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import group1.App;
import group1.model.level.Level;
import group1.model.sprite.game_event.GameEvent;

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
			}
			else
			{
				highestSpriteId++;
				s.setSpriteId(highestSpriteId);
			}
			spriteMap.put(s.getSpriteId(), s);
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
			HashMap<Integer,Sprite> copy = spriteMapCopy();
			Iterator<Sprite> spriteIterator = copy.values().iterator();
			while (spriteIterator.hasNext())
			{
				spriteIterator.next().respondToEvent(g);
			}
		}

		private HashMap<Integer,Sprite> spriteMapCopy()
		{
			HashMap<Integer, Sprite> copy = new HashMap<>();
			Iterator<Entry<Integer,Sprite>> iterator = spriteMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Entry<Integer,Sprite> entry = iterator.next();
				copy.put(entry.getKey(), entry.getValue());
			}
			return copy;
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
			highestSpriteId = 0;
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


        //Returns all ENABLED sprites with SpriteClassId
		public ArrayList<Sprite> getSpriteListByClassId(int spriteClassId)
		{
			ArrayList<Sprite> spritesWithTargetClassId = new ArrayList<>();
			Iterator<Sprite> spriteIterator = spriteMap.values().iterator();
			while (spriteIterator.hasNext())
			{
				Sprite current = spriteIterator.next();
				if (current.getSpriteClassId() == (spriteClassId) && current.isEnabled())
				{
					spritesWithTargetClassId.add(current);
				}
			}
			return spritesWithTargetClassId;
		}

        //Returns all ENABLED sprites in layer
		public ArrayList<Sprite> getSpriteListByLayer(int layer)
		{
			ArrayList<Sprite> spritesInTargetLayer = new ArrayList<>();
			Iterator<Sprite> spriteIterator = spriteMap.values().iterator();
			while (spriteIterator.hasNext())
			{
				Sprite current = spriteIterator.next();
				if (current.getLayer() == (layer) && current.isEnabled())
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

		public ArrayList<Sprite> getPlayerSprites()
		{
			ArrayList<Sprite> playerSprites = new ArrayList<>();
			Iterator<Sprite> spriteIterator = spriteMap.values().iterator();
			while (spriteIterator.hasNext())
			{
				Sprite current = spriteIterator.next();
				if (current.getSpriteClassId() == SpriteClassIdConstants.PLAYER)
				{
					playerSprites.add(current);
				}
			}
			return playerSprites;
		}

        public ArrayList<Sprite> getFloorSprites()
        {
			ArrayList<Sprite> playerSprites = new ArrayList<>();
			Iterator<Sprite> spriteIterator = spriteMap.values().iterator();
			while (spriteIterator.hasNext())
			{
				Sprite current = spriteIterator.next();
				if (current.getSpriteClassId() == SpriteClassIdConstants.FLOOR)
				{
					playerSprites.add(current);
				}
			}
			return playerSprites;
        }
}
