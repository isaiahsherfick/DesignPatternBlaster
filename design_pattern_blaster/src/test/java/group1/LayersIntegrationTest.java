package group1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import group1.model.CollisionManager;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteManager;

class LayersIntegrationTest 
{

	@Test
	void spriteManagerLayersTest() 
	{
		SpriteManager sm = new SpriteManager();
		Sprite s1 = new Sprite();
		Sprite s2 = new Sprite();
		s1.setLayer(0);
		s2.setLayer(1);
		sm.addSprite(s1);
		sm.addSprite(s2);
		assertEquals(1,sm.getSpriteListByLayer(1).size());
		assertEquals(1,sm.getSpriteListByLayer(0).size());
		assertEquals(2,sm.getSpriteList().size());
	}
	
	@Test
	void collisionManagerReceivesRightLayerSizesTest()
	{
		//Setup
		SpriteManager sm = new SpriteManager();
		Sprite s1 = new Sprite();
		Sprite s2 = new Sprite();
		s1.setLayer(0);
		s2.setLayer(1);
		sm.addSprite(s1);
		sm.addSprite(s2);
		
		
		//CollisionManager
		CollisionManager cm = new CollisionManager();
	}

}
