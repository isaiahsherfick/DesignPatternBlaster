package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.constants.Constants;
import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;

public class FaceLeftBehavior implements Behavior
{
	@Override
	public void performBehavior(Sprite sprite)
	{
		sprite.setDirection(Constants.LEFT);
		sprite.getAnimation().setState(AnimationState.LEFT_MOVEMENT);
	}

}
