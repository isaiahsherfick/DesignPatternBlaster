package group1.model.collision;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import group1.constants.Constants;
import group1.model.sprite.AnimationState;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteManager;
import group1.model.sprite.behavior.DisableBehavior;
import group1.model.sprite.behavior.FaceLeftBehavior;
import group1.model.sprite.behavior.FaceRightBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehaviorWhileKeyIsBeingHeld;
import group1.model.sprite.behavior.ShootSpriteBehavior;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class CollisionManagerTest {

	static CollisionManager collisionManager;
	SpriteManager spriteManager;

	@BeforeAll
	public static void setUp() {
		collisionManager = new CollisionManager();
	}

	@BeforeEach
	public void setUpSpriteManager() {
		spriteManager = new SpriteManager();

		Sprite sprite0 = new Sprite();
		sprite0.setSpriteId(0);
		sprite0.setX(100);
		sprite0.setY(100);
		sprite0.setWidth(50);
		sprite0.setHeight(50);
		sprite0.setVelocityX(-20);
		sprite0.setVelocityY(0);
		sprite0.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));

		Sprite sprite1 = new Sprite();
		sprite1.setSpriteId(1);
		sprite1.setX(200);
		sprite1.setY(100);
		sprite1.setWidth(50);
		sprite1.setHeight(50);
		sprite1.setVelocityX(0);
		sprite1.setVelocityY(0);

		sprite0.getCustomCollisionMap().addCustomCollision(sprite1.getSpriteId(), new DisableBehavior());
		sprite1.getCustomCollisionMap().addCustomCollision(sprite0.getSpriteId(), new DisableBehavior());
		spriteManager.addSprite(sprite0);
		spriteManager.addSprite(sprite1);
	}

	@Test
	public void checkCollisionsTest() {
		// Checking for collisions at the initialized state
		collisionManager.checkCollisions(spriteManager);
		Iterator<Sprite> iter = spriteManager.getSpriteList().iterator();
		while(iter.hasNext()) {
			Assertions.assertTrue(iter.next().getEnabled());
		}

		// Running equivalent of 6 clock ticks
		for(int i=0; i<6; i++) {
			spriteManager.updateSprites(GameEvent.ClockTickEvent());
		}

		// Checking for collisions after 6 clock ticks
		collisionManager.checkCollisions(spriteManager);
		iter = spriteManager.getSpriteList().iterator();
		while(iter.hasNext()) {
			Assertions.assertFalse(iter.next().getEnabled());
		}
	}
}
