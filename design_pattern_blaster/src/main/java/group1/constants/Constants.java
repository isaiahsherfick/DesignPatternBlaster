package group1.constants;

public final class Constants 
{
	public static final int WINDOW_HEIGHT = 700; //Height of the main stage, App.java
	public static final int WINDOW_WIDTH = 810; //Width of the main stage, App.java
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
}
