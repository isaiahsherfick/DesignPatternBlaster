package group1.megaman_dp.model;

import group1.megaman_dp.interfaces.Observer;

import java.util.ArrayList;
import java.util.Iterator;

import group1.megaman_dp.interfaces.Observable;


//Model class which serves as a mediator between the various managers in our backend
//as well as an observable which is observed by the View
public class Model implements Observable
{
	//List of observers
	private ArrayList<Observer> observers;
	
	//Default constructor
	public Model()
	{
		observers = new ArrayList<>();
	}
	
	
	//Register an observer to the model
	public void registerObserver(Observer o)
	{
		observers.add(o);
	}
	
	//Notify each observer watching the model
	public void notifyObservers()
	{
		//Use an Iterator to avoid ConcurrentAccessExceptions
		Iterator<Observer> iterator = observers.iterator();
		while (iterator.hasNext())
		{
			Observer o = iterator.next();
			o.update();
		}
	}
}
