package group1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import group1.factories.SpriteFactory;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import group1.model.sprite.behavior.FaceLeftBehavior;
import group1.model.sprite.behavior.FaceRightBehavior;
import group1.model.sprite.behavior.UpdateVelocityXBehavior;
import group1.model.sprite.behavior.UpdateVelocityXOnKeyPressBehavior;
import group1.model.sprite.behavior.GravityBehavior;
import group1.model.sprite.behavior.MoveBehavior;
import group1.model.sprite.behavior.ObservableBehavior;
import group1.constants.Constants;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.input.KeyCode;

class ObservableBehaviorTest 
{
	@Test
	void test() 
	{
		//Initial setup
		App.resetModel();
		ArrayList<Sprite> observers = new ArrayList<>();
		for (int i =0; i < 5; i++)
		{
			observers.add(SpriteFactory.observer());
			assertTrue(observers.get(i).containsObserverBehavior());
		}
        Sprite floor = SpriteFactory.floor(5000, 20);
        Sprite player = new Sprite();
        player.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
        player.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
        player.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.A, -1*Constants.PLAYER_DX)));
        player.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.D, Constants.PLAYER_DX)));
        player.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.A), new UpdateVelocityXBehavior(0)));
        player.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.D), new UpdateVelocityXBehavior(0)));
        player.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new GravityBehavior(Constants.GRAVITY)));
        player.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new ObservableBehavior()));
        player.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new MoveBehavior()));
        player.setSpriteClassId(SpriteClassIdConstants.PLAYER);
		Sprite registerButton = SpriteFactory.registerObserverButton(player, observers); //Observable , ArrayList<Sprite> observers; custom collision with player to register the observers 
		Sprite unregisterButton = SpriteFactory.unregisterObserverButton(player); //Observable 
		App.model.addSprite(floor);
		App.model.addSprite(player);
		App.model.addSprite(registerButton);
		App.model.addSprite(unregisterButton);
		for (int i = 0; i < 5; i++)
		{
			App.model.addSprite(observers.get(i));
		}
		
		//Store initial locations of observers
		double x1 = observers.get(0).getX();
		double y1 = observers.get(0).getY();
		double x2 = observers.get(1).getX();
		double y2 = observers.get(1).getY();
		double x3 = observers.get(2).getX();
		double y3 = observers.get(2).getY();
		double x4 = observers.get(3).getX();
		double y4 = observers.get(3).getY();
		double x5 = observers.get(4).getX();
		double y5 = observers.get(4).getY();
		
		ArrayList<Double> xs = new ArrayList<Double>();
		xs.add(x1);
		xs.add(x2);
		xs.add(x3);
		xs.add(x4);
		xs.add(x5);

		ArrayList<Double> ys = new ArrayList<Double>();
		ys.add(y1);
		ys.add(y2);
		ys.add(y3);
		ys.add(y4);
		ys.add(y5);

		for (int i = 0; i < 1000; i++) // Make 1000 clock ticks occur
		{
			App.model.receiveEvent(GameEvent.ClockTickEvent());
		}
		
		for (int i  =0 ; i < 5; i++)
		{
			 assertEquals(xs.get(i), observers.get(i).getX()); //Assert that the observers haven't moved because our player hasn't and also they aren't registered
			 assertEquals(ys.get(i), observers.get(i).getY()); 
		}
		
		double playerX = player.getX();
		//Move the player 
		player.setX(10);
		for (int i = 0; i < 10; i++) // Make 10 clock ticks occur
		{
			App.model.receiveEvent(GameEvent.ClockTickEvent());
		}
		//Assert that the player did in fact move
		assertNotEquals(playerX, player.getX());

		for (int i  =0 ; i < 5; i++)
		{
			 assertEquals(xs.get(i), observers.get(i).getX()); //Assert that the observers haven't moved because even though the player moved, they aren't registered yet 
			 assertEquals(ys.get(i), observers.get(i).getY()); 
		}
		
		//Now make the player step on the register button
		registerButton.collideWith(player);
		
		playerX = player.getX();
		//Move the player 
		player.setX(20);
		assertNotEquals(playerX, player.getX());
		for (int i = 0; i < 10; i++) // Make 10 clock ticks occur
		{
			App.model.receiveEvent(GameEvent.ClockTickEvent());
		}
		
		ArrayList<Double> previousXs = new ArrayList<>();

		for (int i  =0 ; i < 5; i++)
		{
			 assertNotEquals(xs.get(i), observers.get(i).getX()); //Assert that the observers did move since they are now registered
			 previousXs.add(observers.get(i).getX());
		}
		
		//Now make the player step on the unregister button
		unregisterButton.collideWith(player);
		playerX = player.getX();

		//Move the player 
		player.setX(30);
		
		//Assert that the player did in fact move
		assertNotEquals(playerX, player.getX());

		for (int i  =0 ; i < 5; i++)
		{
			 assertEquals(previousXs.get(i), observers.get(i).getX()); //Assert that the observers haven't moved because even though the player moved, they aren't registered yet 
		}
		
	}
}
