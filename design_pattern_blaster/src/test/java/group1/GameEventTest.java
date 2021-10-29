package group1;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import group1.model.sprite.GameEvent;

public class GameEventTest 
{
	@Test
	public void getEventTypeTest() 
	{
		GameEvent g = GameEvent.CollisionEvent();
		assertEquals(GameEvent.COLLISION, g.getEventType());
	}
	
	//Make sure that this grows as more factory methods are added
	@Test
	public void factoryMethodTests()
	{
		GameEvent g0 = GameEvent.ClockTickEvent();
		GameEvent g1 = GameEvent.CollisionEvent();
		GameEvent g2 = GameEvent.KeyPressedEvent();
		GameEvent g3 = GameEvent.PlayerDeathEvent();
		GameEvent g4 = GameEvent.KeyReleasedEvent();
		GameEvent g5 = GameEvent.HealthDepletedEvent();
		
		assertEquals(g0.getEventType(), GameEvent.CLOCK_TICK);
		assertEquals(g1.getEventType(), GameEvent.COLLISION);
		assertEquals(g2.getEventType(), GameEvent.KEY_PRESSED);
		assertEquals(g3.getEventType(), GameEvent.PLAYER_DEATH);
		assertEquals(g4.getEventType(), GameEvent.KEY_RELEASED);
		assertEquals(g5.getEventType(), GameEvent.HEALTH_DEPLETED);
	}
	
	@Test
	public void saveTest()
	{
		GameEvent ct = GameEvent.ClockTickEvent();
		JSONObject saveObj = ct.save();
		
		GameEvent co = GameEvent.CollisionEvent();
		
		assertNotEquals(ct,co);
		
		co.load(saveObj);
		
		assertEquals(ct,co);
	}
}
