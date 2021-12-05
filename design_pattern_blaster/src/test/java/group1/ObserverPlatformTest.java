package group1;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import group1.factories.SpriteFactory;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.behavior.CollideWithFloorNoClipBehavior;
import group1.model.sprite.behavior.ObservableBehavior;
import group1.model.sprite.game_event.GameEvent;

class ObserverPlatformTest 
{

	@Test
	void observerPlatformRightTest() 
	{
		App.resetModel();
		Sprite player = SpriteFactory.testPlayer();
		player.setVelocityY(20);
		player.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new ObservableBehavior()));
		Sprite observerPlatformRight = SpriteFactory.observerPlatformRight(300, 50, 100, 1000, 1600, 2);
		ArrayList<Sprite> observers = new ArrayList<>(Arrays.asList(observerPlatformRight));
		Sprite registerButton = SpriteFactory.registerObserverButton(player, observers);
		int platformId = 2000;
		observerPlatformRight.setSpriteId(platformId);
		player.addCustomCollision(platformId, new CollideWithFloorNoClipBehavior(observerPlatformRight));
		ArrayList<Sprite> sprites = new ArrayList<>(Arrays.asList(player, observerPlatformRight, registerButton));
		player.setX(100);
		player.setY(0); //The player is directly above the platform
		for (Sprite s : sprites)
		{
			App.model.addSprite(s);
		}
		double expected = observerPlatformRight.getY() - player.getHeight() - 1;
		for (int i = 0 ; i < 500; i++) //Make the player fall onto the platform
		{
			App.model.receiveEvent(GameEvent.ClockTickEvent());
		}
		assertEquals(expected, player.getY()); //Assert that the player landed on the platform
		player.setY(player.getY() - 200); //Move the player back up
		registerButton.collideWith(player); //Register the platform
		double platformX = observerPlatformRight.getX();
		for (int i = 0; i < 5; i++)
		{
			player.setX(player.getX() + 1);
			App.model.receiveEvent(GameEvent.ClockTickEvent());
			assertEquals(platformX + 2, observerPlatformRight.getX()); //Assert that the platform is moving when it detects the player's movement
			platformX = observerPlatformRight.getX();
		}
		
		player.setVelocityY(0);
		player.setVelocityX(0);
		for (int i = 0; i < 100; i++)
		{
			App.model.receiveEvent(GameEvent.ClockTickEvent());
			assertEquals(platformX, observerPlatformRight.getX()); //Assert that it doesn't move when the player is stationary
		}
	}
}
