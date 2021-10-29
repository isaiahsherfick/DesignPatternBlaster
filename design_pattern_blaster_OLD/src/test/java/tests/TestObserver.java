package tests;
import design_pattern_blaster.interfaces.Observer;

//Test class to test Model.notifyObservers()
public class TestObserver implements Observer
{
	private int counter;
	
	//Init counter to 0
	public TestObserver()
	{
		counter = 0;
	}
	
	public int getCounter()
	{
		return counter;
	}
	
	//On update, increment the counter
	public void update()
	{
		counter++;
	}
}
