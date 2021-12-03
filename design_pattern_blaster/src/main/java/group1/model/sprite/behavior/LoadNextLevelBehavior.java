package group1.model.sprite.behavior;

import org.json.simple.JSONObject;

import group1.App;
import group1.model.sprite.Sprite;

public class LoadNextLevelBehavior implements Behavior
{
	int scoreToRegister = 0;
	public LoadNextLevelBehavior(){

	}

	/**
	 * Used when the score for the just-completed level needs to be registered
	 * @param score the score to register
	 */
	public LoadNextLevelBehavior(int score){
		scoreToRegister = score;
	}
	@Override
	public void performBehavior(Sprite sprite) 
	{
		App.model.getCurrentLevel().setLevelScore(scoreToRegister);
		App.model.loadNextLevel();
	}
	
	public Behavior copy()
	{
		return new LoadNextLevelBehavior();
	}
	
}
