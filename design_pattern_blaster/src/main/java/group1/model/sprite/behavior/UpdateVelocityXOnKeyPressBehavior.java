package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;
import javafx.scene.input.KeyCode;

public class UpdateVelocityXOnKeyPressBehavior implements Behavior
{
	
	private KeyCode key;
    private double velocity;
	
	public UpdateVelocityXOnKeyPressBehavior(KeyCode key, double dx)
	{
		this.key = key;
        velocity = dx;
	}
	public KeyCode getKey()
	{
		return key;
	}
	
	public void setKey(KeyCode k)
	{
		key = k;
	}
	
	public void performBehavior(Sprite sprite)
	{
		if (App.model.getKeyInputManager().isPressed(key))
		{
            sprite.setVelocityX(velocity);
		}
	}
	
	public Behavior copy()
	{
		return new UpdateVelocityXOnKeyPressBehavior(key, velocity);
	}
}
