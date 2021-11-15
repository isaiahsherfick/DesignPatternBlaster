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
        //System.out.println("UpdateVelocityYBehavior.java: setting dy to " + velocity);
        sprite.setVelocityY(velocity);
        //System.out.println("UpdateVelocityYBehavior.java: sprite's dy after I touched it: " + sprite.getVelocityY());
	}
	
	public Behavior copy()
	{
		return new UpdateVelocityYBehavior(velocity);
	}
}
