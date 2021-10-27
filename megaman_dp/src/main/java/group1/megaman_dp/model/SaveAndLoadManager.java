package group1.megaman_dp.model;

import org.json.simple.JSONObject;

import group1.megaman_dp.App;
import group1.megaman_dp.constants.Constants;

public class SaveAndLoadManager 
{
	private String saveFilePath;
	
	public SaveAndLoadManager()
	{
		saveFilePath = Constants.DEFAULT_SAVE_FILE;
	}
	
	public JSONObject save()
	{
		JSONObject json = new JSONObject();
		LevelManager levelManager = App.model.getLevelManager();
		ArrayList<Saveable> completedLevels = levelManager.getCompletedLevels();
		ArrayList<Saveable> unfinishedLevels = levelManager.getUnfinishedLevels();
		
		return json;
	}
	
	public void load(JSONObject json)
	{
		Model m = App.model;
		m.notifyObservers();
	}
	
	
}
