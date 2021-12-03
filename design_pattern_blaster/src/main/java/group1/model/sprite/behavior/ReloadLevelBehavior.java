package group1.model.sprite.behavior;

import group1.App;
import group1.model.sprite.Sprite;

public class ReloadLevelBehavior implements Behavior
{
	private Sprite scoreSprite;
	public ReloadLevelBehavior(){

	}

	/**
	 * Used when the score for the just-completed level needs to be registered
	 */
	public ReloadLevelBehavior(Sprite scoreSprite){
		this.scoreSprite = scoreSprite;
	}
	@Override
	public void performBehavior(Sprite sprite) 
	{
		App.model.reloadLevel();
	}
	
	public Behavior copy()
	{
		return new ReloadLevelBehavior();
	}
	
}
