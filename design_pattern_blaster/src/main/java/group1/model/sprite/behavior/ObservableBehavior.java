package group1.model.sprite.behavior;

import java.util.ArrayList;

import group1.model.sprite.Sprite;
import java.util.Iterator;

public class ObservableBehavior implements Behavior
{
	
	private Double previousX;
	private Double previousY;
	private ArrayList<ObserverBehaviorI> observers;
	
	public ObservableBehavior()
	{
		observers = new ArrayList<>();
	}
	
	public void registerObserver(ObserverBehavior observer)
	{
		observers.add(observer);
	}
	
	public void registerObservers(ArrayList<ObserverBehaviorI> observers)
	{
		for (ObserverBehaviorI o : observers)
		{
			this.observers.add(o);
		}
	}
	
	public void unregisterAllObservers()
	{
		observers.clear();
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		if (previousX == null || previousY == null)
		{
			previousX = sprite.getX();
			previousY = sprite.getY();
		}
		
		//If the sprite has moved
		if (previousX != sprite.getX() || previousY != sprite.getY())
		{
			Iterator<ObserverBehaviorI> iter = observers.iterator();
			while (iter.hasNext())
			{
				//Update the observers
				iter.next().update();
			}
			//Store new location
			previousX = sprite.getX();
			previousY = sprite.getY();
		}
	}

	@Override
	public Behavior copy() 
	{
		return new ObservableBehavior();
	}

}
