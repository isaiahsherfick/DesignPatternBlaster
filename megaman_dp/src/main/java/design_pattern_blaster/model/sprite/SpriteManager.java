package design_pattern_blaster.model.sprite;

import java.util.ArrayList;
import java.util.HashMap;

import design_pattern_blaster.model.level.Level;

public class SpriteManager 
{
		private HashMap<Integer, Sprite> spriteMap;
		
		public SpriteManager()
		{
			this.spriteMap = new HashMap<>();
		}
		
		public void addSprite(Sprite s) {
			spriteMap.put(s.getId(), s);
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
		
		public void updateSprites() {
			//to do
		}
		
		public ArrayList<Sprite> getSpriteList(){
			ArrayList<Sprite> allSprites = new ArrayList<>();
			for (Sprite sprite : spriteMap.values()) {
				allSprites.add(sprite);
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
