package group1.model.sprite.behavior;

import java.util.ArrayList;

import group1.model.sprite.Sprite;

public class RegisterObserversBehavior implements Behavior
{
	private ObservableBehavior observable;
	private ArrayList<ObserverBehaviorI> observers;
	
	public RegisterObserversBehavior(ObservableBehavior observable, ArrayList<ObserverBehaviorI> observers)
	{
		this.observable = observable;
		this.observers = observers;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		observable.registerObservers(observers);
	}

	@Override
	public Behavior copy() 
	{
		//TODO if we need this actually implement it
		System.out.println("Don't copy register observers behaviors!");
		return new DoNothingBehavior();
	}
}
