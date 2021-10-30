package group1.model.collision;

import java.util.ArrayList;
import java.util.HashMap;

import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteManager;

public class CollisionManager 
{
private HashMap<Integer, ArrayList<Integer>> collisionMap;
	
	public CollisionManager() {
		collisionMap = new HashMap<Integer, ArrayList<Integer>>();
	}
	
	public void checkCollisions(SpriteManager spriteManager) {
		ArrayList<Sprite> allSprites = spriteManager.getSpriteList();
		collisionMap = new HashMap<Integer, ArrayList<Integer>>();
		
		if (allSprites.size() > 1)
		{
			for(int i=0; i< allSprites.size(); i++) {
				Sprite collider = allSprites.get(i);
				
				for(int j = i+1; j< allSprites.size(); j++) {
					Sprite collidee = allSprites.get(j);
						if(collider.getLayer() == collidee.getLayer()) {
							
						}
				}
			}
			
		}
	}
	
	public void clearCollisionMap() {
		collisionMap.clear();
	}
}
