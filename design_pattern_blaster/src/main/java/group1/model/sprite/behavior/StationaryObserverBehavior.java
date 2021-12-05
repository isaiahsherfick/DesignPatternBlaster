package group1.model.sprite.behavior;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import group1.App;
import group1.model.sprite.*;
import group1.constants.Constants;
import group1.model.sprite.NullSprite;
import group1.model.sprite.Sprite;
import group1.physics.Vector;

public class StationaryObserverBehavior implements ObserverBehaviorI 
{
	private boolean updated;
	
	private int secondsBetweenShots = 1;
	
	private double counter = 0.0;
	
	private Behavior shootBehavior;
	
	public boolean observableChangedState()
	{
		return updated;
	}
	
	//Method called by ObservableBehavior to update the observer
	public void update()
	{
		updated = true;
	}
	
	public void setShootSpriteBehavior(Behavior ssb)
	{
		shootBehavior = ssb;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		counter += App.model.getTimeDelta();
		// System.out.println(sprite.getAnimation().getState());

		if (observableChangedState())
		{
            //TODO fix all this stuff
            sprite.getAnimation().setState(AnimationState.RIGHT_MOVEMENT);
			if (counter > secondsBetweenShots)
			{
				shootBehavior.performBehavior(sprite);
				counter = 0.0;
			}
			updated = false; //reset boolean to not get stuck 
		}
        else
        {
        	sprite.getAnimation().setState(AnimationState.IDLE);
        }
	}
	public Behavior copy()
	{
		StationaryObserverBehavior copy = new StationaryObserverBehavior();
		copy.setShootSpriteBehavior(shootBehavior.copy());
		return copy;
	}
}
