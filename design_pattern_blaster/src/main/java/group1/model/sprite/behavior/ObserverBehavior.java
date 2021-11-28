package group1.model.sprite.behavior;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import group1.App;
import group1.model.sprite.*;
import group1.constants.Constants;
import group1.model.sprite.NullSprite;
import group1.model.sprite.Sprite;
import group1.physics.Vector;

public class ObserverBehavior implements Behavior 
{
	private boolean updated;
	
	private int secondsBetweenShots;
	
	private double maxY;
	private double speed;
	
	private double counter = 0.0;
	
	private Behavior shootBehavior;

	public ObserverBehavior(double maxY, double speed)
	{
		secondsBetweenShots = Constants.OBSERVER_SECONDS_BETWEEN_SHOTS;
		this.maxY = maxY;
		this.speed = speed;
		updated = false;
	}
	
	public boolean observableChangedState()
	{
		return updated;
	}
	
	//Method called by ObservableBehavior to update the observer
	public void update()
	{
		updated = true;
	}
	
	public void setShootSpriteBehavior(Behavior ssb)
	{
		shootBehavior = ssb;
	}
	
	private Sprite getNearestPlayerSprite(Sprite sprite)
	{
			ArrayList<Sprite> playerSprites = App.model.getPlayerSprites();
			//Figure out which player is closest
			double x = sprite.getX();
			double y = sprite.getY();
			double minDistance = Double.MAX_VALUE;
			Sprite nearestPlayerSprite = new NullSprite();
			for (Sprite s : playerSprites)
			{
                //System.out.println(s.getX());
				if (Math.abs(s.getX() - x) < minDistance)
				{
					nearestPlayerSprite = s;
					minDistance = Math.abs(s.getX() - x);
				}
			}
			return nearestPlayerSprite;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		counter += App.model.getTimeDelta();

		if (observableChangedState())
		{
            //TODO fix all this stuff
            sprite.getAnimation().setState(AnimationState.RIGHT_MOVEMENT);
			Sprite nearestPlayerSprite = getNearestPlayerSprite(sprite);
			sprite.turnTowards(nearestPlayerSprite);
			double x = sprite.getX();
			double y = sprite.getY();
				
			//Generate dx and dy
			double dx = (nearestPlayerSprite.getX() - x);
			double dy = (nearestPlayerSprite.getY() - y);
			//Calculate angle -- SOH CAH TOA
			double angle = Math.atan(dy / dx);
			//System.out.println("angle: " + angle);
			//Magnitude is projectileSpeed
			double magnitude = (double)speed;
			//System.out.println("magnitude: " + magnitude);
			//Create vector object
			Vector moveVector = new Vector(magnitude, angle);
			double velocityX = moveVector.getCosComponent();
			//System.out.println("velocityX: " + velocityX);
			double velocityY = moveVector.getSinComponent();
			//System.out.println("velocityY: " + velocityY);

			if (Math.toDegrees(angle) > 90)
				velocityY *= -1;
			if (Math.toDegrees(angle) <= 0)
			{
				velocityY *= -1;
				velocityX *= -1;
			}
			
			//Move
			sprite.setX(sprite.getX() + velocityX);
			if (sprite.getY() < maxY)
			{
				//System.out.println("ObserverBehavior.java: y " + sprite.getY() + " is less than maxY " + maxY);
				sprite.setY(sprite.getY() + velocityY);
			}
			
			//Shoot them
			if (counter > secondsBetweenShots)
			{
				shootBehavior.performBehavior(sprite);
				counter = 0.0;
			}
			updated = false; //reset boolean to not get stuck 
		}
        else
        {
            sprite.getAnimation().setState(AnimationState.LEFT_MOVEMENT);
        }
	}
	public Behavior copy()
	{
		ObserverBehavior copy = new ObserverBehavior(maxY, speed);
		copy.setShootSpriteBehavior(shootBehavior.copy());
		return copy;
	}
}
