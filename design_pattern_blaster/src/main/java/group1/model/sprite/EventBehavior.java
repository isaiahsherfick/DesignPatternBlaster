package group1.model.sprite;

import group1.model.sprite.behavior.Behavior;
import group1.model.sprite.game_event.GameEvent;
import group1.model.sprite.Sprite;

//Bridge between events and behaviors
//Sprites will be able to link a behavior to an event
//I.e move on tick
//    move on keypress
//    disable on death
public class EventBehavior 
{
	private GameEvent event;
	private Behavior behavior;
	public EventBehavior(GameEvent g, Behavior b)
	{
		event = g;
		behavior = b;
	}
	public GameEvent getEvent() 
	{
		return event;
	}
	public void setEvent(GameEvent g)
	{
		event = g;
	}
	public void setBehavior(Behavior b)
	{
		behavior = b;
	}
	public void performBehavior(Sprite sprite) 
	{
		behavior.performBehavior(sprite);
	}
	public void executeIfEventMatches(GameEvent eventToCompare, Sprite sprite)
	{
		if (eventToCompare.equals(event))
		{
			behavior.performBehavior(sprite);
		}
	}
	public EventBehavior copy() 
	{
		return new EventBehavior(event, behavior.copy());
	}
}
