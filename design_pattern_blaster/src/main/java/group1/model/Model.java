package group1.model;

import java.util.ArrayList;
import java.util.Iterator;

import group1.interfaces.Observable;
import group1.interfaces.Observer;
import group1.model.collision.CollisionManager;
import group1.model.level.LevelManager;
import group1.model.player.PlayerManager;
import group1.model.sprite.GameEvent;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteManager;


//Model class which serves as a mediator between the various managers in our backend
//as well as an observable which is observed by the View
public class Model implements Observable
{
	//List of observers
	private ArrayList<Observer> observers;
	
	//Manages all sprites in the game including updating their state on each tick
	private SpriteManager spriteManager;


	//Manages the levels in the game, sends the list of sprites in a given level to the sprite manager
	private LevelManager levelManager;
	
	//Responsible for ticking 
	private GameTimer gameClock;
	
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
		gameClock = new GameTimer();
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
	public GameTimer getGameClock()
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

	//Receive an event, pass it through the sprite manager
	public void receiveEvent(GameEvent g)
	{
		spriteManager.updateSprites(g);
		notifyObservers();
	}

	public void addSprite(Sprite s1) 
	{
		spriteManager.addSprite(s1);
		notifyObservers();
	}
}
