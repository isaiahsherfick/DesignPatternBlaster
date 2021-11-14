package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.sprite.Sprite;

public class TakeNoDamageBehavior implements Behavior{

	private Sprite safeSprite;
	private int interval;
	private double counter = 0.0;
	
	public TakeNoDamageBehavior(Sprite safeSprite) {
		this.safeSprite = safeSprite;
		interval = Constants.TAKE_NO_DAMANGE_INTERVAL;
	}
	
	public boolean isBehaviorSet() {
		return true;
	}
	
	@Override
	public void performBehavior(Sprite sprite) {
		
		counter += App.model.getTimeDelta();
		
		
		if (counter > interval)
		{
			//disable behavior
		}
		
	}

	@Override
	public Behavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
