package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class UpdateVelocityYBehavior implements Behavior
{
	
    private double velocity;
	
	public UpdateVelocityYBehavior(double dy)
	{
        velocity = dy;
	}

	public void performBehavior(Sprite sprite)
	{
        sprite.setVelocityY(velocity);
	}
	
	public Behavior copy()
	{
		return new UpdateVelocityYBehavior(velocity);
	}
}
