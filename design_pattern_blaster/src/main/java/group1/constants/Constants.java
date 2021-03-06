package group1.constants;

public final class Constants
{
	public static final int WINDOW_HEIGHT = 700; //Height of the main stage, App.java
	public static final int WINDOW_WIDTH = 1200; //Width of the main stage, App.java
	public static final int WINDOW_X = 0; //X coordinate of the main stage's top left, App.java
	public static final int WINDOW_Y = 0; //Y coordinate of the main stage's top left, App.java
	public static final String DEFAULT_SAVE_FILE = "save"; //Default save file
	public static final int DEFAULT_SPRITE_ID = -2; //Sprite ID assigned in default constructor, overriden by Sprite Manager
	public static final int FOREGROUND = 0; //layer value for sprites in the foreground
	public static final int BACKGROUND = 1; //layer value for sprites in the background
	public static final short DEFAULT_SPRITE_X = 0; //Default x location for sprites
	public static final short DEFAULT_SPRITE_Y = 0; //Default y location for sprites
	public static final short DEFAULT_SPRITE_DX = 0; //Default x velocity for sprites
	public static final short DEFAULT_SPRITE_DY = 0; //Default y velocity for sprites
	public static final short DEFAULT_SPRITE_WIDTH = 0; //Default width for sprites
	public static final short DEFAULT_SPRITE_HEIGHT = 0; //Default height for sprites
	public static final short LEFT = 0; //Sprite is facing LEFT
	public static final short RIGHT = 1; //Sprite is facing RIGHT
	public static final short NULL_SPRITE_ID =-3; //ID for null sprites
	public static final int OBSERVER_SECONDS_BETWEEN_SHOTS = 2; //Seconds the observer enemies wait before shooting again
    public static final int TAKE_NO_DAMANGE_INTERVAL = 5; //seconds for which enemy sprites will take no damage
    public static final double VOLUME_LEVEL = .1; //Volume level for the music used in LevelManager.java
    public static final double PLAYER_DX = 15; //Default speedX value for the player
	public static final short GRAVITY = 30; //Gravity acceleration value
    public static final double FLOOR_Y = WINDOW_HEIGHT - 100;
    public static final int PLAYER_HEIGHT = 152; //Player's height
    public static final short PLAYER_Y = WINDOW_HEIGHT / 5; //Player's spawn Y
    public static final double MAX_JETPACK_DY = -20; //Player's terminal jetpack velocity
	public static final double SPRITE_POOL_X = 1000;
	public static final int DEFAULT_POOL_SPAWN_INDEX = 0;
	public static final int OBSERVER_LEVEL_NUMBER = 1;
	public static final int STRATEGY_LEVEL_NUMBER = 2;
	public static final int COMMANDER_LEVEL_NUMBER = 3;
	public static final int COMPOSITE_LEVEL_NUMBER = 4;
	public static final int SINGLETON_LEVEL_NUMBER = 5;
	public static final int FACTORY_LEVEL_NUMBER = 6;
	public static final int MVC_LEVEL_NUMBER = 7;
}
