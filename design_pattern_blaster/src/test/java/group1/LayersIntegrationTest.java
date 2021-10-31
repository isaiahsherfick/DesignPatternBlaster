package group1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import group1.model.collision.CollisionManager;
import group1.model.collision.HitBoxOverlapType;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteManager;
import group1.model.sprite.behavior.MoveBehavior;

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
	void layeredCollisionTest()
	{
		//Setup
		SpriteManager sm = new SpriteManager();
		Sprite s1 = new Sprite();
		Sprite s2 = new Sprite();
		s1.setLayer(0);
		s2.setLayer(1);
		sm.addSprite(s1);
		sm.addSprite(s2);
		int xVelocity = 20;
		s1.setVelocityX(20);
		s2.setVelocityX(20);
		s1.setDefaultCollisionBehavior(new MoveBehavior());
		s2.setDefaultCollisionBehavior(new MoveBehavior());
		double x1 = s1.getX();
		double x2 = s2.getX();
		assertNotEquals(HitBoxOverlapType.NO_OVERLAP, s1.getHitBox().overlaps(s2.getHitBox())); //assert that the two hitboxes ARE overlapping without considering layers
		
		//put the sprites in the spriteManager
		sm.addSprite(s1);
		sm.addSprite(s2);
		
		CollisionManager collisionManager = new CollisionManager();
		collisionManager.checkCollisions(sm); //Check all collisions while considering layers - after this happens, the sprites should NOT move
		
		assertEquals(x1, s1.getX());
		assertEquals(x2, s2.getX());
		
		//now create a third sprite which exists in layer 0 for s1 to collide with
		
		Sprite s3 = new Sprite();
		assertNotEquals(HitBoxOverlapType.NO_OVERLAP, s1.getHitBox().overlaps(s3.getHitBox())); //assert that s1 is overlapping with s3's hitbox
		s3.setLayer(0);
		sm.addSprite(s3);
		
		collisionManager.checkCollisions(sm);
		
		assertEquals(x1 + xVelocity, s1.getX()); //now S1 should have executed its behavior since s1 and s3 are in the same layer
		
	}

}
