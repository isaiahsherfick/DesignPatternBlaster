package group1.model.level;

import java.util.ArrayList;
import java.util.Iterator;

import group1.model.sprite.NullSprite;
import group1.model.sprite.Sprite;

public class Level 
{
	private int levelNumber;
	
	private Sprite focusSprite = new NullSprite();
	
	private ArrayList<Sprite> sprites;
	
	public Level()
	{
		this.levelNumber = 0;
		sprites = new ArrayList<>();
	}
	
	public Level(int levelNumber)
	{
		this.levelNumber = levelNumber;
		sprites = new ArrayList<>();
	}
	
	public Level(int levelNumber, ArrayList<Sprite> spritesToAdd)
	{
		this.levelNumber = levelNumber;
		sprites = new ArrayList<>();
		Iterator<Sprite> i = spritesToAdd.iterator();
		while (i.hasNext())
		{
			sprites.add(i.next());
		}
	}
	
	public void setFocusSprite(Sprite sprite)
	{
		focusSprite = sprite;
		if (!sprites.contains(sprite))
		{
			sprites.add(sprite);
		}
	}
	
	public Sprite getFocusSprite()
	{
		return focusSprite;
	}
	
	public int getLevelNumber()
	{
		return levelNumber;
	}
	
	public void setLevelNumber(int level)
	{
		levelNumber = level;
	}
	
	public void addSprite(Sprite sprite)
	{
		sprites.add(sprite);
	}
	
	public void removeSprite(Sprite sprite)
	{
		sprites.remove(sprite);
	}
	
	public ArrayList<Sprite> getAllSpritesOnLevel()
	{
		return sprites;
	}
}
