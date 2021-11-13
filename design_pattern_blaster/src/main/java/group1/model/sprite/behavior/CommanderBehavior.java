package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class CommanderBehavior implements Behavior {
	
	private int secondsBetweenShots;
	private double counter = 0.0;
	private ShootSpriteBehavior shootBehavior;
	
	public CommanderBehavior() {
		secondsBetweenShots = 3;
	}
	
	public void setShootSpriteBehavior(ShootSpriteBehavior ssb)
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
	
}
