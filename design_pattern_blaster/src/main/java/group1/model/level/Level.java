package group1.model.level;

import java.util.ArrayList;

import org.json.simple.JSONObject;


import group1.interfaces.Loadable;
import group1.model.sprite.Sprite;

public class Level implements Loadable
{
	// level corresponds to the level number in the game
	private int level;
	
	// allSprites contains the list of all Sprites in that level
	ArrayList<Sprite> allSprites;
	
	// Default Constructor initializing current level as -1 and creating the new ArrayList of Sprites
	public Level() {
		level = -1;
		allSprites = new ArrayList<>();
	}
	
	// Parameterized Constructor initializing level based on argument and creating the new ArrayList of Sprites
	public Level(int level) {
		this.level = level;
		allSprites = new ArrayList<>();
	}
	
	//**********************************//
	//									//
	//		Getters And Setters			// 
	//									//
	//**********************************//
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void addSpriteToList(Sprite s) {
		allSprites.add(s);
	}
	
	public void removeSpriteFromList(Sprite s) {
		if(allSprites.contains(s)) {
			allSprites.remove(s);
		}
		else {
			System.out.println("Sprite not in this list.");
		}
	}
	
	public ArrayList<Sprite> getAllSpritesOnLevel() {
		return allSprites;
	}
	
	public void setAllSpritesOnLevel(ArrayList<Sprite> allSprites) {
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
