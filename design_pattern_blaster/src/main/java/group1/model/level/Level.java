package group1.model.level;

import java.util.ArrayList;

import group1.model.sprite.Sprite;

public abstract class Level {
	public abstract Level getLevel();
	public abstract int getLevelNumber();
	public abstract void setLevelNumber(int level);
	public abstract void addSpriteToList(Sprite s);
	public abstract void removeSpriteFromList(Sprite s);
	public abstract ArrayList<Sprite> getAllSpritesOnLevel();
	public abstract void setAllSpritesOnLevel(ArrayList<Sprite> allSprites);
}
