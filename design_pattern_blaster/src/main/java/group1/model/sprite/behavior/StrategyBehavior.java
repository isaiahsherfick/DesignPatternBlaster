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
	private MoveTowardsBehavior moveTowardsBehavior;
	private double counter = 0.0;
	private double  secondsBetweenShots;
	private boolean shouldExecuteStrategy = false; 

	public Sprite getSpriteToMoveTowards() {
		return spriteToMoveTowards;
	}

	public void setSpriteToMoveTowards(Sprite spriteToMoveTowards) {
		this.spriteToMoveTowards = spriteToMoveTowards;
	}
	
	public StrategyBehavior (Sprite sprite) {
		spriteToMoveTowards = sprite;
		moveTowardsBehavior = new MoveTowardsBehavior(spriteToMoveTowards);
	}
	
	public void setExecution(boolean value) {
		shouldExecuteStrategy = value;
	}
	
	public void setSpriteStrategy(Behavior strategy)
	{
		spriteStrategy = strategy;
//		secondsBetweenShots = Constants.OBSERVER_SECONDS_BETWEEN_SHOTS;
		secondsBetweenShots = 0.5;
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
		
		
			if(shouldExecuteStrategy)
				spriteStrategy.performBehavior(sprite);
			
			counter += App.model.getTimeDelta();
			if (counter > secondsBetweenShots)
			{
				shootBehavior.performBehavior(sprite);
				counter = 0.0;
			}
			
			moveTowardsBehavior.setSpriteToMoveTowards(spriteToMoveTowards);
			moveTowardsBehavior.performBehavior(sprite);
			
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
