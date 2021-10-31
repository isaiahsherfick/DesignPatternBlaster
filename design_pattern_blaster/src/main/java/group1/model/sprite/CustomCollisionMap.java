package group1.model.sprite;

import java.util.HashMap;

import org.json.simple.JSONObject;

import group1.interfaces.Loadable;
import group1.model.sprite.behavior.Behavior;
import group1.model.sprite.behavior.DoNothingBehavior;

public class CustomCollisionMap implements Loadable
{
	private HashMap<Integer, Behavior> collisionBehaviorMap;
	private Behavior defaultCollisionBehavior;
	public CustomCollisionMap()
	{
		collisionBehaviorMap = new HashMap<>();
		defaultCollisionBehavior = new DoNothingBehavior();
	}
	
	public void setDefaultCollisionBehavior(Behavior behavior)
	{
		defaultCollisionBehavior = behavior;
	}
	
	public Behavior getDefaultCollisionBehavior()
	{
		return defaultCollisionBehavior;
	}
	
	public HashMap<Integer,Behavior> getCollisionBehaviorMap()
	{
		return collisionBehaviorMap;
	}
	
	//
	public void addCustomCollision(int collisionId, Behavior behavior)
	{
		
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

	public void handleCollision(Sprite spriteToModify, Sprite collidee) 
	{
		//First check if the sprite id is in the map, as that means we have a custom collision with that specific sprite
		if (collisionBehaviorMap.containsKey(collidee.getSpriteId()))
		{
			collisionBehaviorMap.get(collidee.getSpriteId()).performBehavior(spriteToModify);
		}
		//Next check if the sprite class id is in the map, as that means we have a custom collision with that class of sprite
		else if (collisionBehaviorMap.containsKey(collidee.getSpriteClassId()))
		{
			collisionBehaviorMap.get(collidee.getSpriteClassId()).performBehavior(spriteToModify);
		}
		//If neither of those are true, execute the default collision behavior on spriteToModify
		else
		{
			defaultCollisionBehavior.performBehavior(spriteToModify);
		}
	}
	
}
