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
	
}
