package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.model.sprite.Sprite;

import java.text.ParseException;

public class LoadNextLevelBehavior implements Behavior
{
	private Sprite scoreSprite;
	public LoadNextLevelBehavior(){

	}

	/**
	 * Used when the score for the just-completed level needs to be registered
	 */
	public LoadNextLevelBehavior(Sprite scoreSprite){
		this.scoreSprite = scoreSprite;
	}
	@Override
	public void performBehavior(Sprite sprite) 
	{
		try{
			if(scoreSprite != null) {
				String scoreString = scoreSprite.getAnimation().getNextStringDisplay().split(" ")[1];
				double scoreForLevel = Double.parseDouble(scoreString);
				App.model.getCurrentLevel().setLevelScore(scoreForLevel);
			}
		} catch(NumberFormatException parseException) {
			parseException.printStackTrace();
		}
		App.model.loadNextLevel();
	}
	
	public Behavior copy()
	{
		return new LoadNextLevelBehavior();
	}
	
}
