package group1.model.level;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;

import group1.interfaces.Loadable;
import group1.model.sprite.Sprite;

public class Level2 extends Level implements Loadable {
	
	private int level;
	private ArrayList<Sprite> allSprites;
	
	
	public Level2() 
	{
		level = 2;
		allSprites = new ArrayList<>();
	}
	
	public Level2(ArrayList<Sprite> sprites) 
	{
		this.level = 2;
		Iterator<Sprite> iterator = sprites.iterator();
		allSprites = new ArrayList<>();
		while (iterator.hasNext())
		{
			Sprite s = iterator.next();
			allSprites.add(s);
		}
	}
	
	public Level2(int level, ArrayList<Sprite> sprites) 
	{
		this.level = level;
		Iterator<Sprite> iterator = sprites.iterator();
		allSprites = new ArrayList<>();
		while (iterator.hasNext())
		{
			Sprite s = iterator.next();
			allSprites.add(s);
		}
	}
	

	public Level getLevel() 
	{
		return this;
	}
	
	public int getLevelNumber() {
		return level;
	}
	
	public void setLevelNumber(int level) 
	{
		this.level = level;
	}
	
	public void addSpriteToList(Sprite s) 
	{
		allSprites.add(s);
	}
	
	public void removeSpriteFromList(Sprite s) 
	{
		if(allSprites.contains(s)) 
		{
			allSprites.remove(s);
		}
		else 
		{
//			System.out.println("Sprite not in this list.");
			//TODO return null sprite
		}
	}
	
	public ArrayList<Sprite> getAllSpritesOnLevel() 
	{
		return allSprites;
	}
	
	public void setAllSpritesOnLevel(ArrayList<Sprite> allSprites) 
	{
		this.allSprites = allSprites;
	}
	
	
	
	@Override
	public JSONObject save() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(JSONObject json) 
	{
		// TODO Auto-generated method stub
	}

	
}
