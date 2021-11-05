package group1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.behavior.CheckHealthBehavior;
import group1.model.sprite.behavior.MoveBehavior;
import group1.model.sprite.game_event.GameEvent;

class SpriteHealthTest 
{
	
	@Test
	public void SpriteHealthTest()
	{
		Sprite s = new Sprite();
		s.setHealth(10);
		int x = 0;
		int dx = 10;
		s.setX(x);
		s.setVelocityX(dx);
		s.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
		s.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new MoveBehavior()));
		s.respondToEvent(GameEvent.ClockTickEvent());
		assertEquals(x, s.getX());
		s.setHealth(0);
		s.respondToEvent(GameEvent.ClockTickEvent());
		assertEquals(dx, s.getX());
		
	}
}
