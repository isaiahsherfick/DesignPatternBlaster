package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class ScreenBehavior implements Behavior {
	
	private double counter = 0.0;
	
	public ScreenBehavior()
	{
	}

	@Override
	public void performBehavior(Sprite sprite) {
		// TODO Auto-generated method stub
		counter += App.model.getTimeDelta();
		if (counter > 3.0)
		{
			App.model.loadNextLevel();
		}
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return new ScreenBehavior();
	}
	
}
