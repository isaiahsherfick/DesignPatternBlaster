package group1.megaman_dp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import group1.megaman_dp.model.Model;
import group1.megaman_dp.view.View;

class ModelTest {

	@Test
	void RegisterObserverTest() 
	{
		//Create new model
		Model m = new Model();
		
		//It has no observers in it
		assertEquals(0, m.getNumberOfObservers());
		
		//Create 50 observers
		ArrayList<View> observers = new ArrayList<>();
		for (int i = 0; i < 50; i++)
		{
			observers.add(new View());
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

}
