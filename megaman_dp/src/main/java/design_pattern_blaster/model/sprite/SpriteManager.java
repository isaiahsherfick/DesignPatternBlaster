package design_pattern_blaster.model.sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import design_pattern_blaster.App;
import design_pattern_blaster.model.level.Level;

public class SpriteManager 
{
		private HashMap<Integer, Sprite> spriteMap;
		private int highestSpriteId;
		
		public SpriteManager()
		{
			this.spriteMap = new HashMap<>();
			highestSpriteId = 0;
		}
		
		public void addSprite(Sprite s) 
		{
			if (s.getId() > highestSpriteId)
			{
				highestSpriteId = s.getId();
				spriteMap.put(s.getId(), s);
			}
			else
			{
				highestSpriteId++;
				s.setId(highestSpriteId);
				spriteMap.put(s.getId(),s);
			}
		}
		
		public Sprite getSprite(int spriteId) {
			return spriteMap.get(spriteId);
		}
		
		public void modifySprite(Sprite newSprite) {
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
		
		public ArrayList<Sprite> getSpriteList(){
			ArrayList<Sprite> allSprites = new ArrayList<>();
			Iterator<Sprite> spriteIterator = spriteMap.values().iterator();
			while (spriteIterator.hasNext())
			{
				allSprites.add(spriteIterator.next());
			}
			return allSprites;
		}
		
		public void clearAllSprites() {
			spriteMap.clear();
		}
		
		public void loadLevel(Level level) {
			//load next level implementation goes here
		}		
}
