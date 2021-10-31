package group1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import group1.constants.Constants;
import group1.model.sprite.Animation;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import group1.model.sprite.behavior.Behavior;
import group1.model.sprite.behavior.MoveBehavior;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.input.KeyCode;

public class SpriteTest 
{
	@Test
	public void setAnimationTest() 
	{
		Sprite s = new Sprite();
		Animation a = new Animation();
		assertNotEquals(a,s.getAnimation());
		s.setAnimation(a);
		assertEquals(a,s.getAnimation());
	}
	@Test
	public void respondToEventTest()
	{
		Sprite s = new Sprite();
		
		s.setVelocityX(5);

		//Factory method
		GameEvent g = GameEvent.KeyPressedEvent(KeyCode.S); //Arbitrary key
		
		Behavior b = new MoveBehavior();
		
		EventBehavior eb = new EventBehavior(g,b);
		
		s.addEventBehavior(eb);
		
		double xBefore = s.getX();
		
		s.respondToEvent(GameEvent.ClockTickEvent());
		
		//The sprite shouldn't do anything on a clock tick
		assertEquals(xBefore, s.getX());
		
		s.respondToEvent(GameEvent.KeyPressedEvent(KeyCode.S));
		
		assertEquals(xBefore + s.getVelocityX(), s.getX());
		
	}
	
	@Test
	public void getIdTest()
	{
		Sprite s = new Sprite();
		int expected = 420;
		s.setSpriteId(expected);
		assertEquals(expected,s.getSpriteId());
	}
	
	@Test
	public void getXTest()
	{
		Sprite s = new Sprite();
		int expected = 420;
		s.setX(expected);
		assertEquals(expected,s.getX());
	}
	@Test
	public void getYTest()
	{
		Sprite s = new Sprite();
		int expected = 420;
		s.setY(expected);
		assertEquals(expected,s.getY());
	}

	@Test
	public void getVelocityYTest()
	{
		Sprite s = new Sprite();
		int expected = 420;
		s.setVelocityY(expected);
		assertEquals(expected,s.getVelocityY());
	}
	@Test
	public void getVelocityXTest()
	{
		Sprite s = new Sprite();
		int expected = 420;
		s.setVelocityX(expected);
		assertEquals(expected,s.getVelocityX());
	}
	@Test
	public void getWidthTest()
	{
		Sprite s = new Sprite();
		int expected = 420;
		s.setWidth(expected);
		assertEquals(expected,s.getWidth());
	}
	@Test
	public void getHeightTest()
	{
		Sprite s = new Sprite();
		int expected = 420;
		s.setHeight(expected);
		assertEquals(expected,s.getHeight());
	}
	@Test
	public void getLayerTest()
	{
		Sprite s = new Sprite();
		int expected = 420;
		s.setLayer(expected);
		assertEquals(expected,s.getLayer());
	}
	@Test
	public void getHealthTest()
	{
		Sprite s = new Sprite();
		int expected = 420;
		s.setHealth(expected);
		assertEquals(expected,s.getHealth());
	}
	@Test
	public void EnabledTest()
	{
		Sprite s = new Sprite();
		assertEquals(false,s.isEnabled());
		s.enable();
		assertEquals(true, s.isEnabled());
		s.disable();
		assertEquals(false,s.isEnabled());
	}
	
	@Test
	public void getEventBehaviorsTest()
	{
		Sprite s = new Sprite();
		assertEquals(0, s.getEventBehaviors().size());
		s.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new MoveBehavior()));
		assertEquals(1,s.getEventBehaviors().size());
		assertEquals(s.getEventBehaviors().get(0).getEvent(), GameEvent.ClockTickEvent());
	}
	
	@Test
	public void getSpriteClassIdTest()
	{
		Sprite s = new Sprite();
		assertEquals(SpriteClassIdConstants.DEFAULT_SPRITE_CLASS, s.getSpriteClassId());
	}
	
	@Test
	public void setSpriteClassIdTest()
	{
		Sprite s = new Sprite();
		assertNotEquals(420, s.getSpriteClassId());
		s.setSpriteClassId(420);
		assertEquals(420, s.getSpriteClassId());
	}
}
