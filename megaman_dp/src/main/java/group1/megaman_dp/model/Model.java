package group1.megaman_dp.model;

import group1.megaman_dp.interfaces.Observer;

import java.util.ArrayList;
import java.util.Iterator;

import group1.megaman_dp.interfaces.Observable;


//Model class which serves as a mediator between the various managers in our backend
//as well as an observable which is observed by the View

//The model also observes each of its managers so that when they experience a state change,
//it automatically knows to call update() for the view. 
public class Model implements Observable, Observer
{
	//List of observers
	private ArrayList<Observer> observers;
	
	//Manages all sprites in the game including updating their state on each tick
	private SpriteManager spriteManager;


	//Manages the levels in the game, sends the list of sprites in a given level to the sprite manager
	private LevelManager levelManager;
	
	//Responsible for ticking 
	private GameClock gameClock;
	
	//Responsible for knowing the bounds of the screen. Capable of saying if a sprite is in the shot or not - used in SpriteManager to enable sprites 
	//As they enter the screen
	private GameCamera gameCamera;
	
	//Responsible for saving/loading the game to a file. Only the levels which have been completed are saved as well as the mode chosen by the player. (Easy/Hard).
	private SaveAndLoadManager saveAndLoadManager;
	
	//Responsible for handling all collisions in the game. Receives a list of sprites from the sprite manager and checks for collisions within each layer.
	private CollisionManager collisionManager;
	
	//Responsible for managing the Player objects in the game and attaching sprites to them. Will be simple until multiplayer is added.
	private PlayerManager playerManager;
	
	//Default constructor
	public Model()
	{
		observers = new ArrayList<>();
		spriteManager = new SpriteManager();
		levelManager = new LevelManager();
		gameClock = new GameClock();
		gameCamera = new GameCamera();
		saveAndLoadManager = new SaveAndLoadManager();
		collisionManager = new CollisionManager();
		playerManager = new PlayerManager();
	}
	
	
	//Register an observer to the model
	public void registerObserver(Observer o)
	{
		observers.add(o);
	}
	
	//Unregister an observer to the model
	public void unregisterObserver(Observer o)
	{
		observers.remove(o);
	}
	
	//Notify each observer watching the model
	public void notifyObservers()
	{
		//Use an Iterator to avoid ConcurrentAccessExceptions
		Iterator<Observer> iterator = observers.iterator();
		while (iterator.hasNext())
		{
			Observer o = iterator.next();
			o.update();
		}
	}
	
	//When one of the things the model is observing (its children)
	//changes state, we need to tell the view
	
	//This is the cleanest way to do it
	public void update()
	{
		notifyObservers();
	}


	//Return the number of observers registered to the model, mainly for testing
	public int getNumberOfObservers() 
	{
		return observers.size();
	}

	//Return a reference to the sprite manager
	public SpriteManager getSpriteManager() 
	{
		return spriteManager;
	}

	//Return a reference to the level manager
	public LevelManager getLevelManager() 
	{
		return levelManager;
	}

	//Return a reference to the game clock
	public GameClock getGameClock() 
	{
		return gameClock;
	}

	//Return a reference to the game camera
	public GameCamera getGameCamera() 
	{
		return gameCamera;
	}

	//Return a reference to the s/l manager -- probably not needed
//	public SaveAndLoadManager getSaveAndLoadManager() 
//	{
//		return saveAndLoadManager;
//	}

	//Return a reference to the collision manager
	public CollisionManager getCollisionManager() 
	{
		return collisionManager;
	}

	//Return a reference to the player manager
	public PlayerManager getPlayerManager() 
	{
		return playerManager;
	}
}
