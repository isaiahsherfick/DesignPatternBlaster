package group1.megaman_dp.model;

import java.util.ArrayList;

import group1.megaman_dp.interfaces.Saveable;

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
