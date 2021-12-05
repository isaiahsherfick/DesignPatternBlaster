package group1.model.sprite.behavior;

import group1.model.sprite.Sprite;
import group1.model.sprite.game_event.GameEvent;

public class InteractPopupBehavior implements Behavior
{
	private Sprite attachedSprite;
	
	public InteractPopupBehavior(Sprite attachedSprite)
	{
		this.attachedSprite = attachedSprite;
	}

	@Override
	public void performBehavior(Sprite sprite) 
	{
		attachedSprite.respondToEvent(GameEvent.InteractEvent());
	}

	@Override
	public Behavior copy() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
