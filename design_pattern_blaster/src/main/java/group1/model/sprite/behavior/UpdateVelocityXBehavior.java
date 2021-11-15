package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class UpdateVelocityXBehavior implements Behavior
{
    
    private double velocity;
    
    public UpdateVelocityXBehavior(double dx)
    {
        velocity = dx;
    }

    public void performBehavior(Sprite sprite)
    {
        sprite.setVelocityX(velocity);
    }
    
    public Behavior copy()
    {
        return new UpdateVelocityXBehavior(velocity);
    }
}

