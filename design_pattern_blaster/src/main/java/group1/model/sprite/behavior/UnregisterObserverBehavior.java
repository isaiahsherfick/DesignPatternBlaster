package group1.model.sprite.behavior;

import java.util.ArrayList;

import group1.model.sprite.Sprite;

public class UnregisterObserverBehavior implements Behavior
{
	private ObservableBehavior observable;
	
	public UnregisterObserverBehavior(ObservableBehavior observable)
	{
		this.observable = observable;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		observable.unregisterAllObservers();
	}

	@Override
	public Behavior copy() 
	{
		return new UnregisterObserverBehavior((ObservableBehavior)observable.copy());
	}

}
