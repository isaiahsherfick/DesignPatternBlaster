package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class CommanderBehavior2 implements Behavior {
	
	private int secondsBetweenShots;
	private double counter = 0.0;
	private Behavior shootBehavior;
	
	public CommanderBehavior2() {
		secondsBetweenShots = 4;
	}
	
	public void setShootSpriteBehavior(Behavior ssb)
	{
		shootBehavior = ssb;
	}
	@Override
	public void performBehavior(Sprite sprite) {
		// TODO Auto-generated method stub
		
		counter += App.model.getTimeDelta();
		sprite.setDirection(Constants.RIGHT);
		sprite.setX(sprite.getX() - sprite.getVelocityX());
//		System.out.println(counter);
		if (counter > secondsBetweenShots)
		{
			shootBehavior.performBehavior(sprite);
			counter = 0.0;
		}
	}
	
	public Behavior copy()
	{
		return new CommanderBehavior2();
	}
	
}
