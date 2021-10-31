package group1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import group1.TestObserver;
import group1.model.GameCamera;
import group1.model.GameTimer;
import group1.model.Model;
import group1.model.collision.CollisionManager;
import group1.model.level.LevelManager;
import group1.model.player.PlayerManager;
import group1.model.sprite.SpriteManager;

public class ModelTest 
{

	@Test
	public void RegisterObserverTest() 
	{
		
		App.resetModel();
		Model m = App.model;
		
		//It has no observers in it other than the viewcontroller
		assertEquals(1, m.getNumberOfObservers());
		
		//Create 50 observers
		ArrayList<TestObserver> observers = new ArrayList<>();
		for (int i = 0; i < 50; i++)
		{
			observers.add(new TestObserver());
		}

		//Register and assert that they're being registered
		for (int i = 0; i < 50; i++)
		{
			m.registerObserver(observers.get(i));
			assertEquals(i+1,m.getNumberOfObservers());
		}

		//Unregister and assert that they're being unregistered
		for (int i = 49; i > -1 ; i--)
		{
			m.unregisterObserver(observers.get(i));
			assertEquals(i,m.getNumberOfObservers());
		}
	}
	
	@Test 
	public void notifyObserversTest()
	{
		App.resetModel();
		Model m = App.model;

		//Create 50 observers, assert that their counter is initialized to 0, register them
		ArrayList<TestObserver> observers = new ArrayList<>();
		for (int i = 0; i < 50; i++)
		{
			observers.add(new TestObserver());
			assertEquals(0, observers.get(i).getCounter());
			m.registerObserver(observers.get(i));
		}
		
		m.notifyObservers();

		//Assert that the counters got incremented for all 50 observers
		for (int i = 0; i < 50; i++)
		{
			assertEquals(1, observers.get(i).getCounter());
		}
	}
	
	@Test 
	public void getSpriteManagerTest()
	{
		App.resetModel();
		Model m = App.model;
		
		Object o = m.getSpriteManager();
		
		assertTrue(o instanceof SpriteManager);
	}
	@Test 
	public void getLevelManagerTest()
	{
		App.resetModel();
		Model m = App.model;
		
		Object o = m.getLevelManager();
		
		assertTrue(o instanceof LevelManager);
	}
	@Test 
	public void getGameClockTest()
	{
		App.resetModel();
		Model m = App.model;
		
		Object o = m.getGameClock();
		
		assertTrue(o instanceof GameTimer);
	}
	@Test 
	public void getGameCameraTest()
	{
		App.resetModel();
		Model m = App.model;
		
		Object o = m.getGameCamera();
		
		assertTrue(o instanceof GameCamera);
	}
	@Test 
	public void getCollisionManagerTest()
	{
		App.resetModel();
		Model m = App.model;
		
		Object o = m.getCollisionManager();
		
		assertTrue(o instanceof CollisionManager);
	}
	@Test 
	public void getPlayerManagerTest()
	{
		App.resetModel();
		Model m = App.model;
		
		Object o = m.getPlayerManager();
		
		assertTrue(o instanceof PlayerManager);
	}
	

}
