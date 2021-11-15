package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import group1.physics.Vector;

public class StrategyBehavior implements Behavior{
	
	Sprite spriteToMoveTowards;
	private Behavior spriteStrategy;
	private Behavior shootBehavior;
	private double counter = 0.0;
	private int secondsBetweenShots;

	public StrategyBehavior (Sprite sprite) {
		spriteToMoveTowards = sprite;
	}
	
	
	public void setSpriteStrategy(Behavior strategy)
	{
		spriteStrategy = strategy;
		secondsBetweenShots = Constants.OBSERVER_SECONDS_BETWEEN_SHOTS;
	}
	
	public Behavior getSpriteStrategy() {
		return spriteStrategy;
	}

	
	public void setShootSpriteBehavior(Behavior ssb)
	{
		shootBehavior = ssb;
	}

	@Override
	public void performBehavior(Sprite sprite) {
		
			spriteStrategy.performBehavior(sprite);
			
			counter += App.model.getTimeDelta();
			if (counter > secondsBetweenShots)
			{
				shootBehavior.performBehavior(sprite);
				counter = 0.0;
			}
			
			//needs to be optimized
			sprite.turnTowards(spriteToMoveTowards);
			double x = sprite.getX();
			double y = sprite.getY();
				
			//Generate dx and dy
			double dx = (spriteToMoveTowards.getX() - x);
			double dy = (spriteToMoveTowards.getY() - y);
			//Calculate angle -- SOH CAH TOA
			double angle = Math.atan(dy / dx);
			//System.out.println("angle: " + angle);
			//Magnitude is projectileSpeed
			double magnitude = (double)5;
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
			if (sprite.getY() < 450)
			{
				//System.out.println("ObserverBehavior.java: y " + sprite.getY() + " is less than maxY " + maxY);
				sprite.setY(sprite.getY() + velocityY);
			}
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
