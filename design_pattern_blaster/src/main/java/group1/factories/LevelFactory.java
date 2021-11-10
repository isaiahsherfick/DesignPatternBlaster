package group1.factories;

import java.util.ArrayList;

import group1.model.level.Level;
import group1.model.sprite.Sprite;

public class LevelFactory 
{
	private LevelFactory() {}
	public static Level demoLevel1()
	{
		
		Sprite player = SpriteFactory.player();
		Sprite floor = SpriteFactory.demoFloor();

		Sprite enemy1 = SpriteFactory.demoEnemy1();  
		Sprite enemy2 = SpriteFactory.demoEnemy2();
		
		Sprite endOfLevelSprite = SpriteFactory.endOfLevelSprite();
		
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(player);
		sprites.add(floor);
		sprites.add(enemy1);
		sprites.add(enemy2);
		sprites.add(endOfLevelSprite);
		
		Level level1=new Level(1, sprites);
		
		level1.setFocusSprite(player);
		
		return level1;
	}
	
	public static Level demoLevel2()
	{
		Sprite player = SpriteFactory.player();
		Sprite floor = SpriteFactory.demoFloor();

		Sprite enemy1 = SpriteFactory.demoEnemy1();  
		Sprite enemy2 = SpriteFactory.demoEnemy2();
		
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(player);
		sprites.add(floor);
		sprites.add(enemy1);
		sprites.add(enemy2);
		
		Level level2=new Level(2, sprites);
		level2.setFocusSprite(player);
		return level2;
	}
	
	public static Level observerLevel()
	{
		Sprite player  = SpriteFactory.player();
		
		Sprite observer = SpriteFactory.observer(player, 500, 25);
		
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(player);
		sprites.add(observer);
		
		Level observerLevel = new Level(1,sprites);
		observerLevel.setFocusSprite(player);
		return observerLevel;
	}
}
