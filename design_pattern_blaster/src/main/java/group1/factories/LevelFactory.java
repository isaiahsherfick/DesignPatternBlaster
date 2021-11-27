package group1.factories;

import java.util.ArrayList;

import group1.constants.Constants;
import group1.model.level.Level;
import group1.model.sprite.CompositeSprite;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import group1.model.sprite.SpriteManager;
import javafx.scene.paint.Color;

public class LevelFactory
{

	private LevelFactory() {}

	public static Level observerLevel()
	{
        Sprite floor = SpriteFactory.floor(5000, 20);
        Sprite ladder = SpriteFactory.platform(100, 20, 300, 500);
		Sprite player  = SpriteFactory.observablePlayer();

		Sprite observer = SpriteFactory.observer(1000, 25);

		Sprite observer2 = SpriteFactory.observer(500, 25);
        ArrayList<Sprite> observers = new ArrayList<>();
        observers.add(observer);
        observers.add(observer2);

        Sprite registerButton = SpriteFactory.registerObserverButton(player, observers, 20, 20, 40, 20);
        Sprite unregisterButton = SpriteFactory.unregisterObserverButton(player, 1000, 20, 40, 20);

		Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite();
		nextLevelSprite.setX(2000);


		ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(registerButton);
        sprites.add(unregisterButton);
        sprites.add(floor);
        sprites.add(ladder);
		sprites.add(player);
		sprites.add(observer);
		sprites.add(observer2);
		sprites.add(nextLevelSprite);

		Level observerLevel = new Level(1,sprites, "Level_Music.mp3");
		observerLevel.setFocusSprite(player);
		return observerLevel;
	}



	public static Level commanderLevel()
	{
        Sprite floor = SpriteFactory.floor(10000, 20);
		Sprite player = SpriteFactory.player();
		Sprite subordinate = SpriteFactory.subordinate();
		Sprite commander = SpriteFactory.commander(subordinate);
		commander.setY(250);
		subordinate.setDirection(Constants.RIGHT);
		Sprite levelend = SpriteFactory.endOfLevelSprite();

		ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(floor);
		sprites.add(player);
		sprites.add(commander);
		sprites.add(subordinate);
		sprites.add(levelend);


		Level commanderLevel = new Level(2,sprites, "Level_Music.mp3");
		commanderLevel.setFocusSprite(player);
		return commanderLevel;
	}

	public static Level compositeLevel()
	{
		Sprite floor = SpriteFactory.floor(10000, 20);
		Sprite player = SpriteFactory.player();
		player.setX(player.getX()-300);
		CompositeSprite enemy = SpriteFactory.compositeEnemy();
		ArrayList<Sprite> sprites = new ArrayList<>();
		Sprite levelend = SpriteFactory.endOfLevelSprite();
        sprites.add(floor);
		sprites.add(player);
		sprites.addAll(enemy.getChildren());
		sprites.add(levelend);
		Level compositeLevel = new Level(4,sprites, "Level_Music.mp3");
		compositeLevel.setFocusSprite(player);
		return compositeLevel;
	}

	public static Level singletonLevel() {
		Sprite floor = SpriteFactory.floor(10000, 20);
		Sprite player = SpriteFactory.player();
		ArrayList<Sprite> singletonEnemies = SpriteFactory.singletonEnemies();
		Sprite spritePool = SpriteFactory.spritePool();
		Sprite levelend = SpriteFactory.endOfLevelSprite();
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        sprites.add(floor);
		sprites.add(player);
		sprites.add(spritePool);
		sprites.addAll(singletonEnemies);
		sprites.add(levelend);
		Level singletonLevel = new Level(5,sprites, "Level_Music.mp3");
		singletonLevel.setFocusSprite(player);
		return singletonLevel;	}

	public static Level factoryLevel()
	{
        Sprite floor = SpriteFactory.floor(10000, 20);
        Sprite ladder = SpriteFactory.platform(100, 20, 300, 500);
		ArrayList<Sprite> sprites = new ArrayList<>();
		Sprite player = SpriteFactory.player();
		Sprite enemyFactory = SpriteFactory.factory(SpriteFactory.observer(player, 1000, 25), 2);
        Sprite endOfLevelSprite = SpriteFactory.endOfLevelSprite();
        endOfLevelSprite.setX(1500);
        sprites.add(floor);
		sprites.add(player);
		sprites.add(enemyFactory);
        sprites.add(ladder);
        sprites.add(endOfLevelSprite);
		Level factoryLevel = new Level(3, sprites, "Level_Music.mp3");
		factoryLevel.setFocusSprite(player);
		return factoryLevel;
	}

	public static Level MVCLevel()
	{
        Sprite floor = SpriteFactory.floor(10000, 20);
		Sprite player  = SpriteFactory.MVCPlayer();

		Sprite viewEnemy = SpriteFactory.viewSprite();
		viewEnemy.setX(viewEnemy.getX() + 1000);
		Sprite wall = SpriteFactory.wall();
		wall.setX(-1025);
		Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite();
		nextLevelSprite.setX(2000);

		ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(floor);
		sprites.add(player);
		sprites.add(viewEnemy);
		sprites.add(nextLevelSprite);
		sprites.add(wall);

		Level MVCLevel = new Level(3,sprites, "Boss_Music.mp3");
		//nullify focus
		MVCLevel.setFocusSprite(SpriteFactory.dummyFocusSprite());

		return MVCLevel;
	}

	public static Level strategyLevel() {
		Sprite player = SpriteFactory.player();

        Sprite floor = SpriteFactory.floor(5000, 20);
		Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite();
		nextLevelSprite.setX(2000);

		//take no damage power up
		Sprite tndPowerUp = new Sprite();
		tndPowerUp.setWidth(80);
		tndPowerUp.setSpriteClassId(SpriteClassIdConstants.TAKE_NO_DAMAGE_POWERUP);
		tndPowerUp.setHeight(50);
		tndPowerUp.setX(100);
		tndPowerUp.setY(Constants.WINDOW_HEIGHT / 2);
		tndPowerUp.setColor(Color.RED);

		Sprite enemyStrategy1 = SpriteFactory.strategyEnemies(tndPowerUp, tndPowerUp.getSpriteClassId(), 1000, 25);

		//bullet size increase power up
		Sprite bulletSizePowerUp = new Sprite();
		bulletSizePowerUp.setWidth(80);
		bulletSizePowerUp.setSpriteClassId(SpriteClassIdConstants.SIZE_INCREASE_POWERUP);
		bulletSizePowerUp.setHeight(50);
		bulletSizePowerUp.setX(200);
		bulletSizePowerUp.setY(Constants.WINDOW_HEIGHT / 2 + 50);
		bulletSizePowerUp.setColor(Color.MAGENTA);

		Sprite enemyStrategy2 = SpriteFactory.strategyEnemies(bulletSizePowerUp, bulletSizePowerUp.getSpriteClassId(), 1000, 80);

		ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(floor);
		sprites.add(enemyStrategy1);
		sprites.add(enemyStrategy2);
		sprites.add(player);
		sprites.add(tndPowerUp);
		sprites.add(bulletSizePowerUp);
		sprites.add(nextLevelSprite);

		Level strategyLevel = new Level(5, sprites, "Level_Music.mp3");
		strategyLevel.setFocusSprite(player);
		return strategyLevel;
	}
}
