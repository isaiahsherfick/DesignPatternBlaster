package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;
import javafx.scene.input.KeyCode;

public class HorizontalMoveBehaviorWhileKeyIsBeingHeld implements Behavior
{
	
	private KeyCode key;
	
	public HorizontalMoveBehaviorWhileKeyIsBeingHeld(KeyCode key)
	{
		this.key = key;
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
			if (sprite.getDirection() == Constants.LEFT)
			{
				sprite.setX(sprite.getX() - sprite.getVelocityX());
			}
			else
			{
				sprite.setX(sprite.getX() + sprite.getVelocityX());
			}
		}
	}
}
