package group1.model.sprite.behavior;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.NullSprite;
import group1.model.sprite.Sprite;

public class ObserverBehavior implements Behavior 
{
	
	private Sprite observable;
	private ArrayList<Sprite> playerSprites;
	private Sprite observableReference;
	
	private int secondsBetweenShots;
	
	private double counter = 0.0;
	
	private ShootSpriteBehavior shootBehavior;

	public ObserverBehavior(Sprite observable)
	{
		this.observable = observable;
		this.observableReference = observable.copy();
		playerSprites = App.model.getPlayerSprites();
		secondsBetweenShots = Constants.OBSERVER_SECONDS_BETWEEN_SHOTS;
	}
	
	public boolean observableChangedState()
	{
		double x = observable.getX();
		double y = observable.getY();
		double expectedX = observableReference.getX();
		double expectedY = observableReference.getY();
		
		if (x != expectedX || y != expectedY)
		{
			observableReference = observable.copy();
			return true;
		}
		return false;
	}
	
	public void setShootSpriteBehavior(ShootSpriteBehavior ssb)
	{
		shootBehavior = ssb;
	}

	@Override
	public JSONObject save() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		counter += App.model.getTimeDelta();
		if (observableChangedState())
		{
			System.out.println("ObserverBehavior.java The sprite changed state!");
			//Figure out which player is closest
			double x = sprite.getX();
			double minDistance = Double.MAX_VALUE;
			Sprite nearestPlayerSprite = new NullSprite();
			for (Sprite s : playerSprites)
			{
				if (Math.abs(s.getX() - x) < minDistance)
				{
					nearestPlayerSprite = s;
					minDistance = Math.abs(s.getX() - x);
				}
			}
			
			//Turn towards the nearest player
			
			if (nearestPlayerSprite.getX() > x)
				sprite.setDirection(Constants.RIGHT);
			else
				sprite.setDirection(Constants.LEFT);
			
			//Move towards them
			
			if (sprite.getDirection() == Constants.RIGHT)
				sprite.setX(sprite.getX() + sprite.getVelocityX());
			else 
				sprite.setX(sprite.getX() - sprite.getVelocityX());
			
			//Shoot them
			if (counter > secondsBetweenShots)
			{
				shootBehavior.performBehavior(sprite);
				counter = 0.0;
			}
		}
	}

}
