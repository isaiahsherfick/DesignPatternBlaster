package group1.megaman_dp.model;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import group1.megaman_dp.App;
import group1.megaman_dp.constants.Constants;
import group1.megaman_dp.interfaces.Saveable;
import group1.megaman_dp.model.level.LevelManager;
import group1.megaman_dp.model.player.PlayerManager;

public class SaveAndLoadManager 
{
	private String saveFilePath;
	
	public SaveAndLoadManager()
	{
		saveFilePath = Constants.DEFAULT_SAVE_FILE;
	}
	
	public void setSaveFilePath(String sfp)
	{
		saveFilePath = sfp;
	}
	
	public String getSaveFilePath()
	{
		return saveFilePath;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject save()
	{
		JSONObject json = new JSONObject();
		
		//Get reference to levelManager
		LevelManager levelManager = App.model.getLevelManager();
		
		//Get list of completed and unfinished levels from level manager
		ArrayList<Saveable> completedLevels = levelManager.getCompletedLevels();
		ArrayList<Saveable> unfinishedLevels = levelManager.getUnfinishedLevels();
		
		int completed = completedLevels.size();
		int unfinished = unfinishedLevels.size();
		
		//Store the size of both lists
		json.put("completed",completed);
		json.put("unfinished", unfinished);
		
		//Save the completed levels
		for (int i = 0; i < completed; i++)
		{
			json.put(i, completedLevels.get(i).save());
		}
		
		//Save the unfinished levels
		for (int i = completed; i < unfinished; i++)
		{
			json.put(i, unfinishedLevels.get(i).save());
		}
		
		//Get reference to PlayerManager
		PlayerManager playerManager = App.model.getPlayerManager();
		
		ArrayList<Saveable> players = playerManager.getPlayers();
		
		//Store the number of players
		json.put("players", players.size());
		
		//Save each player
		for (int i = 0; i < players.size(); i++)
		{
			json.put(String.format("player%d",i),players.get(i).save());
		}
		
		
		return json;
	}
	
	public void load(JSONObject json)
	{
		Model m = App.model;
		//TODO
		m.notifyObservers();
	}
	
	
}
