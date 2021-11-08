package group1.model.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import group1.App;
import group1.interfaces.Saveable;

public class LevelManager 
{
	
	private ArrayList<Level> completedLevels;
	private ArrayList<Level> unfinishedLevels;
	private Level currentLevel;
	
	public LevelManager() 
	{
		completedLevels = new ArrayList<>();
		unfinishedLevels = new ArrayList<>();
	}
	
	public Level getLevel(int levelNumber) 
	{
		Iterator<Level> UnfinishedlevelIterator = unfinishedLevels.iterator();
		while(UnfinishedlevelIterator.hasNext()) 
		{
			Level Unfinishedlevel = (Level) UnfinishedlevelIterator.next();
			if(Unfinishedlevel.getLevelNumber()==levelNumber) 
			{
				return Unfinishedlevel;
			}
		}
		Iterator<Level> CompletedlevelIterator = completedLevels.iterator();
		while(CompletedlevelIterator.hasNext()) 
		{
			Level CompletedLevel = CompletedlevelIterator.next();
			if(CompletedLevel.getLevelNumber()==levelNumber) 
			{
				return CompletedLevel;
			}
		}
		return null;
	}
	
	public ArrayList<Level> getCompletedLevelsList() 
	{
		return completedLevels;
	}
	
	public ArrayList<Level> getUnfinishedLevelsList() 
	{
		return unfinishedLevels;
	}
	
	public void addLevelToCompletedLevelList(Level level) 
	{
		if(unfinishedLevels.contains(level)) 
		{
			unfinishedLevels.remove(level);
		}
		if(!completedLevels.contains(level)) 
		{
			completedLevels.add(level);
		}
	}
	
	public void addLevelToUnfinishedLevelList(Level level) 
	{
		if(!unfinishedLevels.contains(level)) 
		{
			unfinishedLevels.add(level);
		}
	}
	
	public void removeLevelFromCompletedLevelList(Level level) 
	{
		if(completedLevels.contains(level)) 
		{
			completedLevels.remove(level);
		}
		if(!unfinishedLevels.contains(level)) 
		{
			unfinishedLevels.add(level);
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

	public void addLevel(Level level) 
	{
		unfinishedLevels.add(level);
	}

	public void loadNextLevel() 
	{
		if (currentLevel != null)
		{
			currentLevel = unfinishedLevels.get(0);
			unfinishedLevels.remove(currentLevel); //will require a .equals() method in Level.java
			completedLevels.add(currentLevel);
		}
		App.model.clearSprites();
		currentLevel = unfinishedLevels.get(0);
		App.model.loadLevel(currentLevel);
		//System.out.println("Loaded level#" + currentLevel.getLevelNumber());
	}
}
