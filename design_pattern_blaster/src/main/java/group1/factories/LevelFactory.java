package group1.factories;

import java.util.ArrayList;

import group1.constants.Constants;
import group1.model.level.Level;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteManager;

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
		
		Sprite observer = SpriteFactory.observer(player, 1000, 25);

		Sprite observer2 = SpriteFactory.observer(player, 500, 25);
		
		Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite();
		nextLevelSprite.setX(2000);
		
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(player);
		sprites.add(observer);
		sprites.add(observer2);
		sprites.add(nextLevelSprite);
		
		Level observerLevel = new Level(1,sprites, "Level_Music.mp3");
		observerLevel.setFocusSprite(player);
		return observerLevel;
	}



	public static Level commanderLevel() {
		Sprite player = SpriteFactory.player();
		Sprite subordinates = SpriteFactory.subordinates();
		Sprite commander = SpriteFactory.commander(subordinates);
		subordinates.setDirection(Constants.RIGHT);
		Sprite levelend = SpriteFactory.endOfLevelSprite();
		
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(player);
		sprites.add(commander);
		sprites.add(subordinates);
		sprites.add(levelend);
		
		
		Level commanderLevel = new Level(2,sprites, "Boss_Music.mp3");
		commanderLevel.setFocusSprite(player);
		return commanderLevel;
	}

	public static Level factoryLevel()
	{
		ArrayList<Sprite> sprites = new ArrayList<>();
		Sprite player = SpriteFactory.player();
		Sprite enemyFactory = SpriteFactory.factory(SpriteFactory.observer(player, 1000, 25), 2);
		sprites.add(player);
		sprites.add(enemyFactory);
		Level factoryLevel = new Level(3, sprites, "Level_Music.mp3");
		factoryLevel.setFocusSprite(player);
		return factoryLevel;
	}

	public static Level MVCLevel()
	{
		Sprite player  = SpriteFactory.MVCPlayer();

		Sprite viewEnemy = SpriteFactory.viewSprite();
		viewEnemy.setX(viewEnemy.getX() + 1000);
		Sprite wall = SpriteFactory.wall();
		wall.setX(-1025);
		Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite();
		nextLevelSprite.setX(2000);

		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(player);
		sprites.add(viewEnemy);
		sprites.add(nextLevelSprite);
		sprites.add(wall);

		Level MVCLevel = new Level(3,sprites, "Level_Music.mp3");
		//nullify focus
		MVCLevel.setFocusSprite(SpriteFactory.dummyFocusSprite());

		return MVCLevel;
	}
}
