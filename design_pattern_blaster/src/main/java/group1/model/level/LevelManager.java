package group1.model.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import group1.interfaces.Saveable;

public class LevelManager 
{
	
	ArrayList<Level> CompletedLevels;
	ArrayList<Level> UnfinishedLevels;
	
	public LevelManager() 
	{
		CompletedLevels = new ArrayList<>();
		UnfinishedLevels = new ArrayList<>();
	}
	
	public Level getLevel(int levelNumber) 
	{
		Iterator<Level> UnfinishedlevelIterator = UnfinishedLevels.iterator();
		while(UnfinishedlevelIterator.hasNext()) 
		{
			Level Unfinishedlevel = (Level) UnfinishedlevelIterator.next();
			if(Unfinishedlevel.getLevel()==levelNumber) 
			{
				return Unfinishedlevel;
			}
		}
		Iterator<Level> CompletedlevelIterator = CompletedLevels.iterator();
		while(CompletedlevelIterator.hasNext()) 
		{
			Level CompletedLevel = CompletedlevelIterator.next();
			if(CompletedLevel.getLevel()==levelNumber) 
			{
				return CompletedLevel;
			}
		}
		return null;
	}
	
	public ArrayList<Level> getCompletedLevelsList() 
	{
		return CompletedLevels;
	}
	
	public ArrayList<Level> getUnfinishedLevelsList() 
	{
		return UnfinishedLevels;
	}
	
	public void addLevelToCompletedLevelList(Level level) 
	{
		if(UnfinishedLevels.contains(level)) 
		{
			UnfinishedLevels.remove(level);
		}
		if(!CompletedLevels.contains(level)) 
		{
			CompletedLevels.add(level);
		}
	}
	
	public void addLevelToUnfinishedLevelList(Level level) 
	{
		if(!UnfinishedLevels.contains(level)) 
		{
			UnfinishedLevels.add(level);
		}
	}
	
	public void removeLevelFromCompletedLevelList(Level level) 
	{
		if(CompletedLevels.contains(level)) 
		{
			CompletedLevels.remove(level);
		}
		if(!UnfinishedLevels.contains(level)) 
		{
			UnfinishedLevels.add(level);
		}
	}
	

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
