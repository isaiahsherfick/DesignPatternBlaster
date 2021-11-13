package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.constants.Constants;
import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;

public class FaceRightBehavior implements Behavior
{

	@Override
	public void performBehavior(Sprite sprite)
	{
		sprite.setDirection(Constants.RIGHT);
		sprite.getAnimation().setState(AnimationState.RIGHT_MOVEMENT);

	}

}
