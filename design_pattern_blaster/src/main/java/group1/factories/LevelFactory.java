package group1.factories;

import java.util.ArrayList;
import java.util.Arrays;

import group1.constants.Constants;
import group1.model.sprite.behavior.*;
import group1.model.sprite.game_event.GameEvent;
import group1.model.level.Level;
import group1.model.sprite.CompositeSprite;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import group1.model.sprite.SpriteManager;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class LevelFactory

{

	private LevelFactory() {}

	public static Level observerLevelFlashScreen() 
    {
		Sprite screen = SpriteFactory.observerFlashScreen();
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(screen);
		Level observerLevelFlashScreen = new Level(0,sprites,"Menu_Music.mp3");
		return observerLevelFlashScreen;
	}

	public static Level strategyLevelFlashScreen() 
    {
		Sprite screen = SpriteFactory.strategyFlashScreen();
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(screen);
		Level strategyLevelFlashScreen = new Level(0,sprites,"Menu_Music.mp3");
		return strategyLevelFlashScreen;
	}

	public static Level commanderLevelFlashScreen() 
    {
		Sprite screen = SpriteFactory.commanderFlashScreen();
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(screen);
		Level commanderLevelFlashScreen = new Level(0,sprites,"Menu_Music.mp3");
		return commanderLevelFlashScreen;
	}

	public static Level compositeLevelFlashScreen() 
    {
		Sprite screen = SpriteFactory.compositeFlashScreen();
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(screen);
		Level compositeLevelFlashScreen = new Level(0,sprites,"Menu_Music.mp3");
		return compositeLevelFlashScreen;
	}

	public static Level factoryLevelFlashScreen() 
    {
		Sprite screen = SpriteFactory.factoryFlashScreen();
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(screen);
		Level factoryLevelFlashScreen = new Level(0,sprites,"Menu_Music.mp3");
		return factoryLevelFlashScreen;
	}

	public static Level MVCLevelFlashScreen() 
    {
		Sprite screen = SpriteFactory.MVCFlashScreen();
		ArrayList<Sprite> sprites = new ArrayList<>();
		sprites.add(screen);
		Level MVCLevelFlashScreen = new Level(0,sprites,"Menu_Music.mp3");
		return MVCLevelFlashScreen;
	}


	public static Level observerLevel()
	{
        Sprite floor = SpriteFactory.floor(5000, 20);
        floor.setX(0);
        Sprite platform = SpriteFactory.platform(100, 20, 300, 500);
        platform.setSpriteId(100); //set it high so we know it won't get overwritten upon insertion
        Sprite player = SpriteFactory.observablePlayer();
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
        player.addCustomCollision(100, new CollideWithFloorNoClipBehavior(platform));

		Sprite observer = SpriteFactory.observer(3000, 25);

		Sprite observer2 = SpriteFactory.observer(2500, 25);
        ArrayList<Sprite> observers = new ArrayList<>();
        observers.add(observer);
        observers.add(observer2);

        Sprite registerButton = SpriteFactory.registerObserverButton(player, observers, 2500, 20, 40, 20);
        Sprite registerInformationSign = SpriteFactory.informationalSign((int)registerButton.getX() - 175, (int)registerButton.getY() - 300, "src/main/resources/assets/signs/RegisterObserverInformationSign.png");
        Sprite unregisterButton = SpriteFactory.unregisterObserverButton(player, 4000, 20, 40, 20);
        Sprite unregisterInformationSign = SpriteFactory.informationalSign((int)unregisterButton.getX() - 175, (int)unregisterButton.getY() - 300, "src/main/resources/assets/signs/UnregisterObserverInformationSign.png");
        
        Sprite observerPlatform = SpriteFactory.observerPlatformRight(300, 20, 5100, (int)Constants.FLOOR_Y + 1, 6000, 4);
        Sprite platformInformationSign = SpriteFactory.dummyInformationalSign((int)observerPlatform.getX() - 175, (int)observerPlatform.getY() - 300);
        Sprite observerPlatform2 = SpriteFactory.observerPlatformRight(300, 20, 6700, 499, 7850, 4);
        ArrayList<Sprite> platList = new ArrayList<>(Arrays.asList(observerPlatform));
        Sprite registerButton2 = SpriteFactory.registerObserverButton(player, platList, 5100, 40, 40, 20);
        observerPlatform.setSpriteId(150);
        observerPlatform2.setSpriteId(151);

        player.addCustomCollision(150, new CollideWithFloorNoClipBehavior(observerPlatform));
        player.addCustomCollision(151, new CollideWithFloorNoClipBehavior(observerPlatform2));
        

		Sprite scoreDisplay = SpriteFactory.Timer(true);
		Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite(scoreDisplay);

		nextLevelSprite.setX(8000);


		ArrayList<Sprite> sprites = new ArrayList<>();

		Sprite computerIcon = SpriteFactory.computerIcon();
		Sprite messageFromHQ = SpriteFactory.compositeMessageFromHQ();
		Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
		Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
		sprites.add(computerIcon);
		sprites.add(popup);
		sprites.add(interactTrigger);
        sprites.add(registerButton);
        sprites.add(unregisterButton);
        sprites.add(floor);
        sprites.add(platform);
		sprites.add(observerPlatform);
		sprites.add(observerPlatform2);
		sprites.add(registerButton2);
		sprites.add(player);
		sprites.add(observer);
		sprites.add(observer2);
		sprites.add(nextLevelSprite);
		sprites.add(scoreDisplay);
		sprites.add(registerInformationSign);
		sprites.add(unregisterInformationSign);
		sprites.add(platformInformationSign);
		double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH/2);
		double maxXBoundary = Math.abs(nextLevelSprite.getX() - Constants.WINDOW_WIDTH + nextLevelSprite.getWidth());
		Level observerLevel = new Level(Constants.OBSERVER_LEVEL_NUMBER,sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
		observerLevel.setFocusSprite(player);



		return observerLevel;
	}

	public static Level commanderLevel()
	{
        Sprite floor = SpriteFactory.floor(10000, 20);
		Sprite player = SpriteFactory.player();
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
		Sprite subordinate = SpriteFactory.subordinate();
		Sprite invisibleSubordinate = SpriteFactory.InvisibleSubordinate();
		invisibleSubordinate.setDirection(Constants.RIGHT);
		Sprite commander = SpriteFactory.commander(invisibleSubordinate);
		commander.setY(250);
		subordinate.setDirection(Constants.RIGHT);
		Sprite scoreDisplay = SpriteFactory.Timer(true);
		Sprite levelend = SpriteFactory.endOfLevelSprite(scoreDisplay);

		ArrayList<Sprite> sprites = new ArrayList<>();
		Sprite computerIcon = SpriteFactory.computerIcon();
		Sprite messageFromHQ = SpriteFactory.compositeMessageFromHQ();
		Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
		Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
		sprites.add(computerIcon);
		sprites.add(popup);
		sprites.add(interactTrigger);

        sprites.add(floor);
		sprites.add(player);
		sprites.add(commander);
		sprites.add(subordinate);
		sprites.add(invisibleSubordinate);
		sprites.add(levelend);
		sprites.add(scoreDisplay);

		double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH/2);
		double maxXBoundary = Math.abs(levelend.getX() - Constants.WINDOW_WIDTH + levelend.getWidth());
		Level commanderLevel = new Level(Constants.COMMANDER_LEVEL_NUMBER,sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
		commanderLevel.setFocusSprite(player);
		return commanderLevel;
	}

	public static Level compositeLevel()
	{
		Sprite floor = SpriteFactory.floor(10000, 20);
		Sprite player = SpriteFactory.player();
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
		player.setX(player.getX()-300);
		CompositeSprite enemy = SpriteFactory.compositeEnemy();
		ArrayList<Sprite> sprites = new ArrayList<>();
		Sprite computerIcon = SpriteFactory.computerIcon();
		Sprite messageFromHQ = SpriteFactory.compositeMessageFromHQ();
		Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
		Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
		Sprite scoreDisplay = SpriteFactory.Timer(true);
		Sprite levelend = SpriteFactory.endOfLevelSprite(scoreDisplay);
        sprites.add(floor);
		sprites.add(player);
		sprites.add(computerIcon);
		sprites.add(popup);
		sprites.add(interactTrigger);
		sprites.addAll(enemy.getChildren());
		sprites.add(levelend);
		sprites.add(scoreDisplay);
		double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH/2);
		double maxXBoundary = Math.abs(levelend.getX() - Constants.WINDOW_WIDTH + levelend.getWidth());
		Level compositeLevel = new Level(Constants.COMMANDER_LEVEL_NUMBER,sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
		compositeLevel.setFocusSprite(player);
		return compositeLevel;
	}

	public static Level singletonLevel() {
		Sprite floor = SpriteFactory.floor(10000, 20);
		Sprite player = SpriteFactory.player();
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
		ArrayList<Sprite> singletonEnemies = SpriteFactory.singletonEnemies();
		Sprite spritePool = SpriteFactory.spritePool();
		Sprite scoreDisplay = SpriteFactory.Timer(true);
		Sprite levelend = SpriteFactory.endOfLevelSprite(scoreDisplay);
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		Sprite computerIcon = SpriteFactory.computerIcon();
		Sprite messageFromHQ = SpriteFactory.compositeMessageFromHQ();
		Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
		Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
		sprites.add(computerIcon);
		sprites.add(popup);
		sprites.add(interactTrigger);
        sprites.add(floor);
		sprites.add(player);
		sprites.add(spritePool);
		sprites.addAll(singletonEnemies);
		sprites.add(levelend);
		sprites.add(scoreDisplay);
		double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH/2);
		double maxXBoundary = Math.abs(levelend.getX() - Constants.WINDOW_WIDTH + levelend.getWidth());
		Level singletonLevel = new Level(Constants.SINGLETON_LEVEL_NUMBER,sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
		singletonLevel.setFocusSprite(player);
		return singletonLevel;	}

	public static Level factoryLevel()
	{
		Sprite player = SpriteFactory.player();

        Sprite floor = SpriteFactory.floor(10000, 20);
        Sprite platform = SpriteFactory.platform(100, 20, 300, 500);
        platform.setSpriteId(2000); //set it high so we know it won't get overwritten upon insertion
        player.addCustomCollision(2000, new CollideWithFloorNoClipBehavior(platform));
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
		ArrayList<Sprite> sprites = new ArrayList<>();
		Sprite enemyFactory = SpriteFactory.factory(SpriteFactory.observer(player, 1000, 25), 2);
		Sprite scoreDisplay = SpriteFactory.Timer(true);
		Sprite endOfLevelSprite = SpriteFactory.endOfLevelSprite(scoreDisplay);
        endOfLevelSprite.setX(1500);
		Sprite computerIcon = SpriteFactory.computerIcon();
		Sprite messageFromHQ = SpriteFactory.compositeMessageFromHQ();
		Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
		Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
		sprites.add(computerIcon);
		sprites.add(popup);
		sprites.add(interactTrigger);
        sprites.add(floor);
		sprites.add(player);
		sprites.add(enemyFactory);
        sprites.add(platform);
        sprites.add(endOfLevelSprite);
		sprites.add(SpriteFactory.Timer(true));
        double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH/2);
        double maxXBoundary = Math.abs(endOfLevelSprite.getX() - Constants.WINDOW_WIDTH + endOfLevelSprite.getWidth());
		Level factoryLevel = new Level(Constants.FACTORY_LEVEL_NUMBER, sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
		factoryLevel.setFocusSprite(player);
		return factoryLevel;
	}

	public static Level MVCLevel()
	{
        Sprite floor = SpriteFactory.floor(10000, 20);
		Sprite player  = SpriteFactory.MVCPlayer();
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));

		Sprite viewEnemy = SpriteFactory.viewSprite();
		viewEnemy.setX(viewEnemy.getX() + 1000);
		Sprite wall = SpriteFactory.wall();
		wall.setX(-1025);
		Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite();
		nextLevelSprite.setX(viewEnemy.getX()+90);
		nextLevelSprite.getEventBehaviors().clear();
		nextLevelSprite.addCustomCollision(SpriteClassIdConstants.PLAYER, new WonGameBehavior());



		ArrayList<Sprite> sprites = new ArrayList<>();
		Sprite computerIcon = SpriteFactory.computerIcon();
		Sprite messageFromHQ = SpriteFactory.compositeMessageFromHQ();
		Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
		Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
		sprites.add(computerIcon);
		sprites.add(popup);
		sprites.add(interactTrigger);
        sprites.add(floor);
		sprites.add(player);
		sprites.add(viewEnemy);
		sprites.add(nextLevelSprite);
		sprites.add(wall);
		double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH/2);
		double maxXBoundary = Math.abs(nextLevelSprite.getX() - Constants.WINDOW_WIDTH + nextLevelSprite.getWidth());
		Level MVCLevel = new Level(Constants.MVC_LEVEL_NUMBER,sprites, "Boss_Music.mp3", minXBoundary, maxXBoundary);
		//nullify focus
		MVCLevel.setFocusSprite(SpriteFactory.dummyFocusSprite());
		return MVCLevel;
	}

	public static Level strategyLevel() {
		Sprite player = SpriteFactory.player();
        Sprite floor = SpriteFactory.floor(5000, 20);
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
		Sprite scoreDisplay = SpriteFactory.Timer(true);
		Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite(scoreDisplay);
		nextLevelSprite.setX(2000);
		Sprite platform = SpriteFactory.platform(100, 20, 700, 500);
        platform.setSpriteId(500); //set it high so we know it won't get overwritten upon insertion
        player.addCustomCollision(500, new CollideWithFloorNoClipBehavior(platform));
		//take no damage power up
//		Sprite tndPowerUp = new Sprite();
//		tndPowerUp.setWidth(80);
//		tndPowerUp.setSpriteClassId(SpriteClassIdConstants.TAKE_NO_DAMAGE_POWERUP);
//		tndPowerUp.setHeight(50);
//		tndPowerUp.setX(100);
//		tndPowerUp.setY(Constants.WINDOW_HEIGHT / 2);
//		tndPowerUp.setColor(Color.RED);
//
//		Sprite enemyStrategy1 = SpriteFactory.strategyEnemies(tndPowerUp, tndPowerUp.getSpriteClassId(), 1000, 25);

		//bullet size increase power up
//		Sprite bulletSizePowerUp = new Sprite();
//		bulletSizePowerUp.setWidth(80);
//		bulletSizePowerUp.setSpriteClassId(SpriteClassIdConstants.SIZE_INCREASE_POWERUP);
//		bulletSizePowerUp.setHeight(50);
//		bulletSizePowerUp.setX(200);
//		bulletSizePowerUp.setY(Constants.WINDOW_HEIGHT / 2 + 50);
//		bulletSizePowerUp.setColor(Color.MAGENTA);

		Sprite bulletSizePowerUp = SpriteFactory.bulletPowerUp();

		Sprite enemyStrategy2 = SpriteFactory.strategyEnemies(bulletSizePowerUp, bulletSizePowerUp.getSpriteClassId(), 1000, 80);

		//new gun

		Sprite newGun = SpriteFactory.pickNewGun();
		player.addCustomCollision(SpriteClassIdConstants.PICKUP_NEW_GUN, new PickNewGunBehavior(player));
		newGun.addCustomCollision(player.getSpriteClassId(), new DisableBehavior());
		ArrayList<Sprite> sprites = new ArrayList<>();
		Sprite computerIcon = SpriteFactory.computerIcon();
		Sprite messageFromHQ = SpriteFactory.compositeMessageFromHQ();
		Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
		Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
		sprites.add(computerIcon);
		sprites.add(popup);
		sprites.add(interactTrigger);
        sprites.add(floor);
//		sprites.add(enemyStrategy1);
		sprites.add(enemyStrategy2);
		sprites.add(player);
//		sprites.add(tndPowerUp);
		sprites.add(bulletSizePowerUp);
		sprites.add(nextLevelSprite);
		sprites.add(newGun);
		sprites.add(scoreDisplay);
		sprites.add(platform);
		double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH/2);
		double maxXBoundary = Math.abs(nextLevelSprite.getX() - Constants.WINDOW_WIDTH + nextLevelSprite.getWidth());
		Level strategyLevel = new Level(Constants.STRATEGY_LEVEL_NUMBER, sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
		strategyLevel.setFocusSprite(player);
		return strategyLevel;
	}
}
