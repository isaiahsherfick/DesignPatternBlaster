package group1.model.level;

import java.util.ArrayList;
import java.util.Iterator;

import group1.App;
import group1.model.sprite.NullSprite;
import group1.model.sprite.Sprite;
import javafx.scene.media.Media;

public class Level 
{
	private int levelNumber;
	private double levelScore = 0;
	
	private Sprite focusSprite = new NullSprite();
	
	private ArrayList<Sprite> sprites;
	private double minXBoundary, maxXBoundary, minYBoundary, maxYBoundary;

    private Media song;
	
	public Level()
	{
		this.levelNumber = 0;
		sprites = new ArrayList<>();
	}
	
	public Level(int levelNumber)
	{
		this.levelNumber = levelNumber;
		sprites = new ArrayList<>();
	}
	
	public Level(int levelNumber, ArrayList<Sprite> spritesToAdd)
	{
		this.levelNumber = levelNumber;
		sprites = new ArrayList<>();
		Iterator<Sprite> i = spritesToAdd.iterator();
		while (i.hasNext())
		{
			sprites.add(i.next());
		}
	}

	public Level(int levelNumber, ArrayList<Sprite> spritesToAdd, String pathToSongFile)
	{
		this.levelNumber = levelNumber;
		sprites = new ArrayList<>();
		Iterator<Sprite> i = spritesToAdd.iterator();
		while (i.hasNext())
		{
			sprites.add(i.next());
		}
        this.song = new Media((App.class.getResource(pathToSongFile)).toExternalForm());
	}
	
	public Level(int levelNumber, ArrayList<Sprite> spritesToAdd, String pathToSongFile, double minXBoundary, double maxXBoundary) {
		this.levelNumber = levelNumber;
		sprites = new ArrayList<>();
		Iterator<Sprite> i = spritesToAdd.iterator();
		while (i.hasNext())
		{
			sprites.add(i.next());
		}
        this.song = new Media((App.class.getResource(pathToSongFile)).toExternalForm());
        this.setMinXBoundary(minXBoundary);
        this.setMaxXBoundary(maxXBoundary);
	}
	
	public void setFocusSprite(Sprite sprite)
	{
		focusSprite = sprite;
		if (!sprites.contains(sprite))
		{
			sprites.add(sprite);
		}
	}
	
	public Media getSong()
	{
		return song;
	}
	
	public Sprite getFocusSprite()
	{
		return focusSprite;
	}
	
	public int getLevelNumber()
	{
		return levelNumber;
	}
	
	public void setLevelNumber(int level)
	{
		levelNumber = level;
	}
	
	public void addSprite(Sprite sprite)
	{
		sprites.add(sprite);
	}
	
	public void removeSprite(Sprite sprite)
	{
		sprites.remove(sprite);
	}
	
	public ArrayList<Sprite> getAllSpritesOnLevel()
	{
		return sprites;
	}

	public double getMinXBoundary() {
		return minXBoundary;
	}

	public void setMinXBoundary(double minXBoundary) {
		this.minXBoundary = minXBoundary;
		App.model.getGameCamera().setxMinClampPos(minXBoundary);
	}

	public double getMaxXBoundary() {
		return maxXBoundary;
	}

	public void setMaxXBoundary(double maxXBoundary) {
		this.maxXBoundary = maxXBoundary;
		App.model.getGameCamera().setxMaxClampPos(maxXBoundary);
	}

	public double getMinYBoundary() {
		return minYBoundary;
	}

	public void setMinYBoundary(double minYBoundary) {
		this.minYBoundary = minYBoundary;
	}

	public double getMaxYBoundary() {
		return maxYBoundary;
	}

	public void setMaxYBoundary(double maxYBoundary) {
		this.maxYBoundary = maxYBoundary;
	}

	public void setLevelScore(double score){
		this.levelScore = score;
	}

	public double getLevelScore(){
		return levelScore;
	}

	
}
