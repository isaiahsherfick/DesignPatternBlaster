package group1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import group1.constants.Constants;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.GameEvent;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteManager;
import group1.model.sprite.behavior.MoveBehavior;

class SpriteManagerTest 
{

	@Test
	public void addSpriteTest() 
	{
		App.resetModel();
		SpriteManager sm = new SpriteManager();
		assertEquals(0, sm.getNumberOfSprites());
		Sprite newSprite = new Sprite();
		sm.addSprite(newSprite);
		assertEquals(1, sm.getNumberOfSprites());
	}
	
	@Test
	public void getSpriteTest()
	{
		App.resetModel();
		SpriteManager sm = new SpriteManager();
		Sprite newSprite = new Sprite();
		sm.addSprite(newSprite);
		assertEquals(newSprite, sm.getSprite(newSprite.getSpriteId()));
	}
	
	@Test
	public void spriteIdAssignmentTest()
	{
		App.resetModel();
		SpriteManager sm = new SpriteManager();
		Sprite newSprite = new Sprite();
		assertEquals(newSprite.getSpriteId(), Constants.DEFAULT_SPRITE_ID);
		sm.addSprite(newSprite);
		assertEquals(newSprite.getSpriteId(), 0);
		newSprite = new Sprite();
		assertEquals(newSprite.getSpriteId(), Constants.DEFAULT_SPRITE_ID);
		sm.addSprite(newSprite);
		assertEquals(newSprite.getSpriteId(), 1);
		newSprite = new Sprite();
		assertEquals(newSprite.getSpriteId(), Constants.DEFAULT_SPRITE_ID);
		sm.addSprite(newSprite);
		assertEquals(newSprite.getSpriteId(), 2);
		newSprite = new Sprite();
		assertEquals(newSprite.getSpriteId(), Constants.DEFAULT_SPRITE_ID);
		sm.addSprite(newSprite);
		assertEquals(newSprite.getSpriteId(), 3);
		newSprite = new Sprite();
		assertEquals(newSprite.getSpriteId(), Constants.DEFAULT_SPRITE_ID);
		sm.addSprite(newSprite);
		assertEquals(newSprite.getSpriteId(), 4);
		newSprite = new Sprite();
		assertEquals(newSprite.getSpriteId(), Constants.DEFAULT_SPRITE_ID);
		sm.addSprite(newSprite);
		assertEquals(newSprite.getSpriteId(), 5);
	}
	
	@Test
	public void modifySpriteTest()
	{
		App.resetModel();
		SpriteManager sm = new SpriteManager();
		Sprite newSprite = new Sprite();
		assertEquals(newSprite.getSpriteId(), Constants.DEFAULT_SPRITE_ID);
		sm.addSprite(newSprite);
		assertEquals(newSprite.getSpriteId(), 0);
		
		newSprite = sm.getSprite(0);
		newSprite.setX(420);
		newSprite.setY(69);
		
		sm.modifySprite(newSprite);
		
		assertEquals(420, sm.getSprite(0).getX());
		assertEquals(69, sm.getSprite(0).getY());
	}
	
	@Test
	public void updateSpritesTest()
	{
		App.resetModel();
		SpriteManager sm = new SpriteManager();
		Sprite newSprite = new Sprite();
		newSprite.setVelocityX(200);
		newSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new MoveBehavior()));
		assertEquals(newSprite.getSpriteId(), Constants.DEFAULT_SPRITE_ID);
		sm.addSprite(newSprite);
		assertEquals(newSprite.getSpriteId(), 0);
		assertEquals(Constants.DEFAULT_SPRITE_X, newSprite.getX());
		
		sm.updateSprites(GameEvent.ClockTickEvent());
		
		newSprite = sm.getSprite(0);
		
		assertEquals(Constants.DEFAULT_SPRITE_X + 200, newSprite.getX());
		
		sm.updateSprites(GameEvent.CollisionEvent());
		
		assertEquals(Constants.DEFAULT_SPRITE_X + 200, newSprite.getX());
		
	}
	
	@Test
	public void loadLevelTest()
	{
		//TODO
		assertFalse(!true);
	}
	
	@Test
	public void getSpriteListByLayerTest()
	{
		App.resetModel();
		SpriteManager sm = new SpriteManager();
		Sprite newSprite = new Sprite();
		newSprite.setLayer(0);
		sm.addSprite(newSprite);
		newSprite = new Sprite();
		newSprite.setLayer(1);
		sm.addSprite(newSprite);
		assertEquals(2, sm.getNumberOfSprites());
		
		assertEquals(1, sm.getSpriteListByLayer(0).size());
		assertEquals(1, sm.getSpriteListByLayer(1).size());
	}
	
	@Test
	public void getLayerSetTest()
	{
		App.resetModel();
		SpriteManager sm = new SpriteManager();
		Sprite newSprite = new Sprite();
		newSprite.setLayer(0);
		sm.addSprite(newSprite);
		newSprite = new Sprite();
		newSprite.setLayer(1);
		sm.addSprite(newSprite);
		newSprite = new Sprite();
		newSprite.setLayer(2);
		sm.addSprite(newSprite);
		newSprite = new Sprite();
		newSprite.setLayer(3);
		sm.addSprite(newSprite);
		newSprite = new Sprite();
		newSprite.setLayer(3);
		sm.addSprite(newSprite);
		assertEquals(5, sm.getNumberOfSprites());
		
		assertEquals(4, sm.getLayerSet().size());
		
	}

}
