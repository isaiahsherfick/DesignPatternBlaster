package group1.model.level;

import java.util.ArrayList;

import group1.interfaces.Saveable;

public class LevelManager 
{

	//Needed by Save/Load manager
	public ArrayList<Saveable> getCompletedLevels()
	{
		//Return the list of levels which the player has completed
		return null;
	}

	//Needed by Save/Load manager
	public ArrayList<Saveable> getUnfinishedLevels() 
	{
		//Return the list of levels which the player has not completed yet
		return null;
	}

}
