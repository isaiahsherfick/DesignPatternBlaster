package group1.model.sprite.game_event;

import org.json.simple.JSONObject;

import group1.interfaces.Loadable;
import javafx.scene.input.KeyCode;

//GameEvent abstraction to represent what kind of event has occurred
//used in EventBehavior.java
public class GameEvent implements Loadable
{
	//Add more of these as we need them
	//Putting them here instead of Constants because this is the only place
	//They will be needed I hope, we could move them regardless but this feels better to me
	public static int CLOCK_TICK = 0;
	public static int COLLISION = 1;
	public static int KEY_PRESSED = 2;
	public static int HEALTH_DEPLETED = 3;
	public static int PLAYER_DEATH = 4;
	public static int KEY_RELEASED = 5;
	public static int POWER_UP = 6;
	public static int POWER_UP_END = 7;
	
	private int eventType;
	
	//Use the factory methods!
	protected GameEvent(int eventType)
	{
		this.eventType = eventType;
	}
	
	public int getEventType()
	{
		return eventType;
	}
	
	//Factory methods
	public static GameEvent ClockTickEvent()
	{
		return new GameEvent(CLOCK_TICK);
	}
	public static GameEvent CollisionEvent()
	{
		return new GameEvent(COLLISION);
	}
	public static GameEvent KeyPressedEvent(KeyCode keyCode)
	{
		return new KeyPressEvent(keyCode);
	}
	public static GameEvent HealthDepletedEvent()
	{
		return new GameEvent(HEALTH_DEPLETED);
	}
	public static GameEvent PlayerDeathEvent()
	{
		return new GameEvent(PLAYER_DEATH);
	}
	public static GameEvent KeyReleasedEvent(KeyCode keyCode)
	{
		return new KeyReleasedEvent(keyCode);
	}
	public static GameEvent PowerUpEvent()
	{
		return new GameEvent(POWER_UP);
	}
	public static GameEvent PowerUpEndEvent()
	{
		return new GameEvent(POWER_UP_END);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","GameEvent");
		json.put("eventType",eventType);
		return json;
	}
	
	public void load(JSONObject json)
	{
		try
		{
			eventType = (int)json.get("eventType");
		} 
		//This happens when you write the JSON to a file, for some reason it wants to load a long
		//but when you load the JSON from RAM, it knows it has an int
		//hence the nasty try catch
		catch(ClassCastException e)
		{
			eventType = ((Long)json.get("eventType")).intValue();
		}
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof GameEvent)
		{
			GameEvent g = (GameEvent)o;
			return eventType == g.getEventType();
		}
		return false;
	}

}
