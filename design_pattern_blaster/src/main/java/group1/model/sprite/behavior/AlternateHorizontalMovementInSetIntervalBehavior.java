package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.model.sprite.Sprite;

public class AlternateHorizontalMovementInSetIntervalBehavior implements Behavior
{
	
	private int numberOfSecondsToMoveInEachDirection;
	private int currentTicksPassedSincePreviousFlip;
	
	public AlternateHorizontalMovementInSetIntervalBehavior(int interval)
	{
		numberOfSecondsToMoveInEachDirection = interval;
		currentTicksPassedSincePreviousFlip = 0;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		//int ticksUntilIFlip = Constants.$NUMBER_OF_CLOCK_TICKS_PER_SECOND * numberOfSecondsToMoveInEachDirection

		//I don't know how many times our clock ticks per second TODO ask Maazin how it works
		
	}

}
