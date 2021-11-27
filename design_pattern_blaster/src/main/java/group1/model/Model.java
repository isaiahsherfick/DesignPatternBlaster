package group1.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import group1.interfaces.Observable;
import group1.interfaces.Observer;
import group1.model.collision.CollisionManager;
import group1.model.level.Level;
import group1.model.level.LevelManager;
import group1.model.player.PlayerManager;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import group1.model.sprite.SpriteManager;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.input.KeyCode;


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
	
	//Responsible for managing the key presses
	private KeyInputManager keyInputManager;
	
	//Default constructor
	public Model()
	{
		observers = new ArrayList<>();
		spriteManager = new SpriteManager();
		levelManager = new LevelManager();
		gameClock = new GameTimer();
		gameCamera = new GameCamera(0,0);
		saveAndLoadManager = new SaveAndLoadManager();
		collisionManager = new CollisionManager();
		playerManager = new PlayerManager();
		keyInputManager = new KeyInputManager();
	}
	
	public boolean isPressed(KeyCode k)
	{
		return keyInputManager.isPressed(k);
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
		if (g.equals(GameEvent.ClockTickEvent()))
		{
			collisionManager.checkCollisions(spriteManager);
		}
		notifyObservers();
	}

	public void addSprite(Sprite s1) 
	{
		spriteManager.addSprite(s1);
		notifyObservers();
	}

	public KeyInputManager getKeyInputManager() 
	{
		return keyInputManager;
	}

	public void startGameClock() 
	{
		gameClock.start();
		gameClock.play();
		setCameraToFollowPlayer();
	}
	
	//set which sprite the camera should follow by comparing the class id's
	public void setCameraToFollowPlayer() {
		boolean found = false;
		for(int i=0; i< spriteManager.getNumberOfSprites();i++) {
			if(spriteManager.getSprite(i).getSpriteClassId() == SpriteClassIdConstants.PLAYER) {
				found = true;
				gameCamera.setFocusSprite(spriteManager.getSprite(i));
			}
		}
		if(found)
			gameCamera.moveCamera();
	}

	public void clearSprites() 
	{
		spriteManager.clearAllSprites();
	}

	public void loadLevel(Level currentLevel) 
	{
		ArrayList<Sprite> sprites = currentLevel.getAllSpritesOnLevel();
		Iterator<Sprite> iterator = sprites.iterator();
		while (iterator.hasNext())
		{
			addSprite(iterator.next());
		}
		gameCamera.setFocusSprite(currentLevel.getFocusSprite());
	}
	
	public void startGame()
	{
		startGameClock();
//		levelManager.
		levelManager.loadNextLevel();
	}

	public double getTimeDelta(){
		return getGameClock().getSecondsSincePreviousFrame();
	}

	public Level getCurrentLevel(){
		return levelManager.getCurrentLevel();
	}

	public float getTimeElapsed(){
		return gameClock.getTotalTime();
	}

	public void loadNextLevel() 
	{
		levelManager.loadNextLevel();
	}

	public ArrayList<Sprite> getPlayerSprites() 
	{
		return spriteManager.getPlayerSprites();
	}

    public boolean spriteIsCollidingWithFloor(Sprite sprite)
    {
        return collisionManager.isCollidingWithFloor(sprite, spriteManager);
    }

    public ArrayList<Sprite> getFloorSprites()
    {
        return spriteManager.getFloorSprites();
    }
}
