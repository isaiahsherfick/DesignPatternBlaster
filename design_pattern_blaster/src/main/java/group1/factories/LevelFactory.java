package group1.factories;

import group1.App;
import group1.constants.Constants;
import group1.model.level.Level;
import group1.model.sprite.CompositeSprite;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import group1.model.sprite.behavior.*;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;


public class LevelFactory {

    private LevelFactory() {
    }

    public static Level videoPlayer(String filepath) {

        Sprite placeholder = new Sprite();

        placeholder.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new PlayVideoBehavior(filepath)));

		return new Level(0,new ArrayList<>(List.of(placeholder)));

    }

    public static Level observerLevelFlashScreen() {
        Sprite screen = SpriteFactory.observerFlashScreen();
        ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(screen);

//		Level observerLevelFlashScreen = new Level(0,sprites);
        Level observerLevelFlashScreen = new Level(0, sprites, "Menu_Music.mp3");
        return observerLevelFlashScreen;
    }

    public static Level strategyLevelFlashScreen() {
        Sprite screen = SpriteFactory.strategyFlashScreen();
        ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(screen);

//		Level strategyLevelFlashScreen = new Level(0,sprites);
        Level strategyLevelFlashScreen = new Level(0, sprites, "Menu_Music.mp3");
        return strategyLevelFlashScreen;
    }

    public static Level commanderLevelFlashScreen() {
        Sprite screen = SpriteFactory.commanderFlashScreen();
        ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(screen);

//		Level commanderLevelFlashScreen = new Level(0,sprites);
        Level commanderLevelFlashScreen = new Level(0, sprites, "Menu_Music.mp3");
        return commanderLevelFlashScreen;
    }

    public static Level compositeLevelFlashScreen() {
        Sprite screen = SpriteFactory.compositeFlashScreen();
        ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(screen);


//		Level compositeLevelFlashScreen = new Level(0,sprites);
        Level compositeLevelFlashScreen = new Level(0, sprites, "Menu_Music.mp3");
        return compositeLevelFlashScreen;
    }

    public static Level singletonLevelFlashScreen() {
        Sprite screen = SpriteFactory.singletonFlashScreen();
        ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(screen);

//		Level singletonLevelFlashScreen = new Level(0,sprites);
        Level singletonLevelFlashScreen = new Level(0, sprites, "Menu_Music.mp3");
        return singletonLevelFlashScreen;
    }

    public static Level factoryLevelFlashScreen() {
        Sprite screen = SpriteFactory.factoryFlashScreen();
        ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(screen);
//		Level factoryLevelFlashScreen = new Level(0,sprites);
        Level factoryLevelFlashScreen = new Level(0, sprites, "Menu_Music.mp3");
        return factoryLevelFlashScreen;
    }

    public static Level MVCLevelFlashScreen() {
        Sprite screen = SpriteFactory.MVCFlashScreen();
        ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(screen);

//		Level MVCLevelFlashScreen = new Level(0,sprites);
        Level MVCLevelFlashScreen = new Level(0, sprites, "Menu_Music.mp3");
        return MVCLevelFlashScreen;
    }


    public static Level observerLevel() {
        Sprite floor = SpriteFactory.floor(1650, 20);
        floor.setX(0);
        Sprite platform = SpriteFactory.platform(100, 20, 300, 500);
        platform.setSpriteId(100); //set it high so we know it won't get overwritten upon insertion
        Sprite player = SpriteFactory.observablePlayer();
        player.setHealth(30);
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
        player.addCustomCollision(100, new CollideWithFloorNoClipBehavior(platform));

        Sprite observer = SpriteFactory.observer(1250, 250);
        Sprite observer3 = SpriteFactory.observer(1050, 250);
        Sprite observer4 = SpriteFactory.observer(1150, 250);
        Sprite observer5 = SpriteFactory.observer(1350, 250);
        Sprite observer6 = SpriteFactory.observer(1450, 250);

        Sprite observer2 = SpriteFactory.observer(2000, 250);
        ArrayList<Sprite> observers = new ArrayList<>(Arrays.asList(observer, observer3, observer4, observer5, observer6));

        Sprite registerButton = SpriteFactory.registerObserverButton(player, observers, 1000, floor.getY() - 20, 40, 20);
        Sprite registerInformationSign = SpriteFactory.informationalSign((int) registerButton.getX() - 175, (int) registerButton.getY() - 300, "src/main/resources/assets/signs/RegisterObserverInformationSign.png");
        Sprite unregisterButton = SpriteFactory.unregisterObserverButton(player, 1550, floor.getY() - 20, 40, 20);
        Sprite unregisterInformationSign = SpriteFactory.informationalSign((int) unregisterButton.getX() - 175, (int) unregisterButton.getY() - 300, "src/main/resources/assets/signs/UnregisterObserverInformationSign.png");

        Sprite observerPlatform = SpriteFactory.observerPlatformHorizontal(300, 20, 1750, (int) Constants.FLOOR_Y + 1, 3500, 4);
        Sprite observerPlatform2 = SpriteFactory.observerPlatformHorizontal(300, 20, 4200, 610, 10000, 4);
        Sprite platform2 = SpriteFactory.platform(100, 20, 2500, 499);
        Sprite platform3 = SpriteFactory.platform(100, 20, 3650, 502);
        Sprite unregisterButton2 = SpriteFactory.unregisterObserverButton(player, platform3.getX(), platform3.getY() - 20, 40, 20);
        Sprite platform4 = SpriteFactory.platform(100, 20, 4000, 375);
        Sprite platform5 = SpriteFactory.platform(100, 20, 4400, 503);
        Sprite observer7 = SpriteFactory.observer(2200, 25);
        Sprite observer8 = SpriteFactory.observer(2400, 25);
        ArrayList<Sprite> platList = new ArrayList<>(Arrays.asList(observerPlatform, observer2, observer7, observer8));
        Sprite registerButton2 = SpriteFactory.registerObserverButton(player, platList, 1850, floor.getY() - 20, 40, 20);
        observerPlatform2.setSpriteId(149);
        observerPlatform.setSpriteId(150);
        platform2.setSpriteId(151);
        platform3.setSpriteId(153);
        platform4.setSpriteId(154);
        platform5.setSpriteId(155);

        player.addCustomCollision(149, new CollideWithFloorNoClipBehavior(observerPlatform2));
        player.addCustomCollision(150, new CollideWithFloorNoClipBehavior(observerPlatform));
        player.addCustomCollision(151, new CollideWithFloorNoClipBehavior(platform2));
        player.addCustomCollision(153, new CollideWithFloorNoClipBehavior(platform3));
        player.addCustomCollision(154, new CollideWithFloorNoClipBehavior(platform4));
        player.addCustomCollision(155, new CollideWithFloorNoClipBehavior(platform5));

        Sprite observer9 = SpriteFactory.observer(4200, 150);
        Sprite observer10 = SpriteFactory.observer(4300, 150);
        Sprite observer11 = SpriteFactory.observer(4400, 150);
        Sprite observer12 = SpriteFactory.observer(4600, 150);
        Sprite observer13 = SpriteFactory.observer(4700, 150);
        Sprite observer14 = SpriteFactory.observer(4800, 150);
        Sprite observer15 = SpriteFactory.observer(4900, 150);
        ArrayList<Sprite> platList2 = new ArrayList<>(Arrays.asList(observerPlatform2, observer9, observer10, observer11, observer12, observer13, observer14, observer15));
        Sprite registerButton3 = SpriteFactory.registerObserverButton(player, platList2, platform5.getX(), platform5.getY() - 20, 40, 20);


        Sprite scoreDisplay = SpriteFactory.Timer(true);
        Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite(scoreDisplay);

        nextLevelSprite.setX(6000);


        ArrayList<Sprite> sprites = new ArrayList<>();

//		Sprite computerIcon = SpriteFactory.computerIcon();
        Sprite messageFromHQ = SpriteFactory.messageFromHQ();
        Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
        Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
//		sprites.add(computerIcon);
        sprites.add(popup);
        sprites.add(interactTrigger);
        sprites.add(registerButton);
        sprites.add(unregisterButton);
        sprites.add(floor);
        sprites.add(platform);
        sprites.add(observerPlatform2);
        sprites.add(observerPlatform);
        sprites.add(platform2);
        sprites.add(platform3);
        sprites.add(platform4);
        sprites.add(platform5);
        sprites.add(registerButton2);
        sprites.add(registerButton3);
        sprites.add(player);
        sprites.add(observer);
        sprites.add(observer2);
        sprites.add(observer3);
        sprites.add(observer4);
        sprites.add(observer5);
        sprites.add(observer6);
        sprites.add(observer7);
        sprites.add(observer8);
        sprites.add(observer9);
        sprites.add(observer10);
        sprites.add(observer11);
        sprites.add(observer12);
        sprites.add(observer13);
        sprites.add(observer14);
        sprites.add(observer15);
        sprites.add(nextLevelSprite);
        sprites.add(scoreDisplay);
        sprites.add(registerInformationSign);
        sprites.add(unregisterInformationSign);
        sprites.add(unregisterButton2);
        double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH / 2);
        double maxXBoundary = Math.abs(nextLevelSprite.getX() - Constants.WINDOW_WIDTH + nextLevelSprite.getWidth());
        Level observerLevel = new Level(Constants.OBSERVER_LEVEL_NUMBER, sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
        observerLevel.setFocusSprite(player);


        return observerLevel;
    }

    public static Level commanderLevel() {
        Sprite floor = SpriteFactory.floor(10000, 20);
        Sprite player = SpriteFactory.player();
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));

        Sprite subordinate = SpriteFactory.subordinate(player);
        Sprite invokerSubordinate = SpriteFactory.invoker();
        invokerSubordinate.setDirection(Constants.LEFT);

        Sprite commander = SpriteFactory.commander(invokerSubordinate);
        commander.setY(150);
        commander.setX(500);

        Sprite commander2 = SpriteFactory.commander2(invokerSubordinate);
        commander2.setX(900);
        commander2.setY(150);

        subordinate.setDirection(Constants.LEFT);
        Sprite scoreDisplay = SpriteFactory.Timer(true);
        Sprite levelend = SpriteFactory.endOfLevelSprite(scoreDisplay);
        levelend.setX(4000);
        Sprite wall = SpriteFactory.wall();
        wall.setX(-1025);

        Sprite commandWall = SpriteFactory.commandWall();

        Sprite subordinate2 = SpriteFactory.subordinate(player);
        subordinate2.setX(2800);
        Sprite invokerSubordinate2 = SpriteFactory.invoker2();
        invokerSubordinate2.setDirection(Constants.LEFT);
        invokerSubordinate2.setX(3600);

        Sprite commander3 = SpriteFactory.commander(invokerSubordinate2);
        commander3.setX(2800);
        commander3.setY(150);

        Sprite commander4 = SpriteFactory.commander(invokerSubordinate2);
        commander4.setX(3300);
        commander4.setY(150);

        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
        levelend.setX(2000);

        player.addCustomCollision(SpriteClassIdConstants.INVOKER, new CollideWithWallBehavior(invokerSubordinate, 50));
        player.addCustomCollision(SpriteClassIdConstants.INVOKER2, new CollideWithWallBehavior(invokerSubordinate2, 50));
        player.getCustomCollisionMap().addCustomCollision(SpriteClassIdConstants.WALL, new MoveSetAmountBehavior(20, 0));
        player.getCustomCollisionMap().addCustomCollision(SpriteClassIdConstants.COMMAND, new DoNothingBehavior());


//		Sprite subordinate2 = SpriteFactory.subordinate(player);
//		subordinate2.setX(2500);
//		Sprite invokerSubordinate2 = SpriteFactory.invoker();
//		invokerSubordinate2.setX(2800);
//		invokerSubordinate2.setDirection(Constants.LEFT);
//
//		Sprite commander3 = SpriteFactory.commander(invokerSubordinate);
//		commander3.setY(150);
//		commander3.setX(2500);
//
//		Sprite commander4 = SpriteFactory.commander2(invokerSubordinate);
//		commander4.setX(2800);
//		commander4.setY(150);

//		subordinate2.setDirection(Constants.LEFT);


        ArrayList<Sprite> sprites = new ArrayList<>();
//		Sprite computerIcon = SpriteFactory.computerIcon();
        Sprite messageFromHQ = SpriteFactory.messageFromHQ();
        Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
        Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
//TODO: change parameters for the command level here.
        Sprite computerIcon2 = SpriteFactory.computerIcon(messageFromHQ, messageFromHQ, sprites, messageFromHQ);

        computerIcon2.setX(2100);

        sprites.add(interactTrigger);
        sprites.add(wall);
        sprites.add(floor);
        sprites.add(player);
        sprites.add(commander);
        sprites.add(subordinate);
        sprites.add(invokerSubordinate);
        sprites.add(levelend);
        sprites.add(commander2);
        sprites.add(scoreDisplay);
        sprites.add(computerIcon2);
        sprites.add(commandWall);
        sprites.add(subordinate2);
        sprites.add(invokerSubordinate2);
        sprites.add(commander4);
        sprites.add(commander3);

        double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH / 2);
        double maxXBoundary = Math.abs(levelend.getX() - Constants.WINDOW_WIDTH + levelend.getWidth());

        Level commanderLevel = new Level(Constants.COMMANDER_LEVEL_NUMBER, sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);


        commanderLevel.setFocusSprite(player);
        return commanderLevel;
    }

    public static Level compositeLevel() {
        Sprite floor = SpriteFactory.floor(10000, 20);
        Sprite player = SpriteFactory.player();
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
        player.setX(player.getX() - 300);
        CompositeSprite enemy = SpriteFactory.compositeEnemy();
        ArrayList<Sprite> sprites = new ArrayList<>();
        Sprite pressToInteractPopup = SpriteFactory.popupInteractE();
        Sprite messageFromHQ = SpriteFactory.messageFromHQ();
        ArrayList<Sprite> puzzleSprites = SpriteFactory.compositePuzzle();
        Sprite scoreDisplay = SpriteFactory.Timer(true);
        Sprite levelend = SpriteFactory.endOfLevelSprite(scoreDisplay);
//		levelend.disable();
        Sprite computerIcon = SpriteFactory.computerIcon(pressToInteractPopup, messageFromHQ, puzzleSprites, levelend);
        sprites.add(floor);
        sprites.add(pressToInteractPopup);
        sprites.add(computerIcon);
        sprites.addAll(enemy.getChildren());
        sprites.add(levelend);
        sprites.add(player);
        sprites.add(scoreDisplay);
        sprites.add(messageFromHQ);
        sprites.addAll(puzzleSprites);
        double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH / 2);
        double maxXBoundary = Math.abs(levelend.getX() - Constants.WINDOW_WIDTH + levelend.getWidth());
        Level compositeLevel = new Level(Constants.COMMANDER_LEVEL_NUMBER, sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
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
//		Sprite computerIcon = SpriteFactory.computerIcon();
        Sprite messageFromHQ = SpriteFactory.messageFromHQ();
        Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
        Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
//		sprites.add(computerIcon);
        sprites.add(popup);
        sprites.add(interactTrigger);
        sprites.add(floor);
        sprites.add(player);
        sprites.add(spritePool);
        sprites.addAll(singletonEnemies);
        sprites.add(levelend);
        sprites.add(scoreDisplay);
        double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH / 2);
        double maxXBoundary = Math.abs(levelend.getX() - Constants.WINDOW_WIDTH + levelend.getWidth());
        Level singletonLevel = new Level(Constants.SINGLETON_LEVEL_NUMBER, sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
        singletonLevel.setFocusSprite(player);
        return singletonLevel;
    }

    public static Level factoryLevel() {
        Sprite player = SpriteFactory.observablePlayer();

        Sprite floor = SpriteFactory.floor(1300, 20);
        floor.setX(0);
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
        ArrayList<Sprite> sprites = new ArrayList<>();
        HashMap<Integer, Sprite> observerPlatformFamily = new HashMap<>();
        Sprite observerPlatform = SpriteFactory.observerPlatformHorizontal(300, 20, 1750, (int) Constants.FLOOR_Y + 5, 2300, 4);
        observerPlatform.setSpriteId(2000);
        player.addCustomCollision(2000, new CollideWithFloorNoClipBehavior(observerPlatform));
        ArrayList<Sprite> observers = new ArrayList<>(Arrays.asList(observerPlatform));
        Sprite registerButton = SpriteFactory.registerObserverButton(player, observers, 1000, floor.getY() - 20, 40, 20);
        observerPlatformFamily.put(200, observerPlatform);
        observerPlatformFamily.put(-200, registerButton);
        Sprite observerPlatformFactory = SpriteFactory.factory(1000, (int) (Constants.FLOOR_Y - 900), observerPlatformFamily);
        Stack<Sprite> platformStack = new Stack<>();
        Sprite platform1 = SpriteFactory.platform(100, 20, 3600, 300);
        platform1.setSpriteId(2700);
        Sprite platform2 = SpriteFactory.platform(100, 20, 3300, 400);
        platform2.setSpriteId(2600);
        Sprite platform3 = SpriteFactory.platform(100, 20, 3000, 500);
        platform3.setSpriteId(2500);
        Sprite platform4 = SpriteFactory.platform(100, 20, 2700, 603);
        platform4.setSpriteId(2400);
        platformStack.add(platform1);
        platformStack.add(platform2);
        platformStack.add(platform3);
        platformStack.add(platform4);
        player.addCustomCollision(2700, new CollideWithFloorNoClipBehavior(platform1));
        player.addCustomCollision(2600, new CollideWithFloorNoClipBehavior(platform2));
        player.addCustomCollision(2500, new CollideWithFloorNoClipBehavior(platform3));
        player.addCustomCollision(2400, new CollideWithFloorNoClipBehavior(platform4));
        Sprite platformFactory = SpriteFactory.stackFactory(2300, Constants.WINDOW_HEIGHT - 900, platformStack);
        Sprite platformFactoryPopup = SpriteFactory.interactPopupE(platformFactory);
        platformFactoryPopup.setX(platformFactory.getX());
        Sprite interactTrigger3 = SpriteFactory.interactTrigger(platformFactoryPopup);
        interactTrigger3.setX(platformFactory.getX() - 50);
        Sprite scoreDisplay = SpriteFactory.Timer(true);
        Sprite endOfLevelSprite = SpriteFactory.endOfLevelSprite(scoreDisplay);
        Sprite observerPlatformInformationSign = SpriteFactory.informationalSign(600, 300, "src/main/resources/assets/signs/ObserverPlatformAbstractFactorySign.png");
        Sprite factoryMotivationsSign = SpriteFactory.informationalSign(1300, 300, "src/main/resources/assets/signs/FactoryMotivations.png");
        Sprite factoryAdvantageSign = SpriteFactory.informationalSign(2500, 300, "src/main/resources/assets/signs/FactoryAdvantage.png");
        Sprite singletonExampleSign = SpriteFactory.informationalSign(3000, 100, "src/main/resources/assets/signs/SingletonExplanation.png");
        endOfLevelSprite.setX(4200);
        endOfLevelSprite.setY(200);
        Sprite observerPlatformFactoryPopup = SpriteFactory.interactPopupE(observerPlatformFactory);
        observerPlatformFactoryPopup.setX(observerPlatformFactory.getX());
        Sprite interactTrigger2 = SpriteFactory.interactTrigger(observerPlatformFactoryPopup);
        interactTrigger2.setX(observerPlatformFactory.getX() - 50);
        sprites.add(factoryAdvantageSign);
        sprites.add(singletonExampleSign);
        sprites.add(interactTrigger2);
        sprites.add(interactTrigger3);
        sprites.add(observerPlatformFactoryPopup);
        sprites.add(floor);
        sprites.add(observerPlatformFactory);
        sprites.add(player);
        sprites.add(endOfLevelSprite);
        sprites.add(observerPlatformInformationSign);
        sprites.add(factoryMotivationsSign);
        sprites.add(SpriteFactory.Timer(true));
        sprites.add(platformFactory);
        sprites.add(platformFactoryPopup);
        double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH / 2);
        double maxXBoundary = Math.abs(endOfLevelSprite.getX() - Constants.WINDOW_WIDTH + endOfLevelSprite.getWidth());
        Level factoryLevel = new Level(Constants.FACTORY_LEVEL_NUMBER, sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
        factoryLevel.setFocusSprite(player);
        return factoryLevel;
    }

    public static Level MVCLevel() {
        Sprite floor = SpriteFactory.floor(10000, 20);
        Sprite player = SpriteFactory.MVCPlayer();
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));

        Sprite viewEnemy = SpriteFactory.viewSprite();
        viewEnemy.setX(viewEnemy.getX() + 1000);
        Sprite wall = SpriteFactory.wall();
        wall.setX(-1025);
        Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite();
        nextLevelSprite.setX(viewEnemy.getX() + 90);
        nextLevelSprite.getEventBehaviors().clear();
        nextLevelSprite.addCustomCollision(SpriteClassIdConstants.PLAYER, new WonGameBehavior());


        ArrayList<Sprite> sprites = new ArrayList<>();
//		Sprite computerIcon = SpriteFactory.computerIcon();
        Sprite messageFromHQ = SpriteFactory.messageFromHQ();
        Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
        Sprite interactTrigger = SpriteFactory.interactTrigger(popup);
//		sprites.add(computerIcon);
        sprites.add(popup);
        sprites.add(interactTrigger);
        sprites.add(floor);
        sprites.add(player);
        sprites.add(viewEnemy);
        sprites.add(nextLevelSprite);
        sprites.add(wall);
        double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH / 2);
        double maxXBoundary = Math.abs(nextLevelSprite.getX() - Constants.WINDOW_WIDTH + nextLevelSprite.getWidth());
        Level MVCLevel = new Level(Constants.MVC_LEVEL_NUMBER, sprites, "Boss_Music.mp3", minXBoundary, maxXBoundary);
        //nullify focus
        MVCLevel.setFocusSprite(SpriteFactory.dummyFocusSprite());
        return MVCLevel;
    }

    public static Level strategyLevel() {
        Sprite player = SpriteFactory.player();
        Sprite floor = SpriteFactory.floor(10000, 20);
        player.setY(floor.getY() - player.getHeight() - 50);
        player.addCustomCollision(SpriteClassIdConstants.FLOOR, new CollideWithFloorNoClipBehavior(floor));
        Sprite scoreDisplay = SpriteFactory.Timer(true);
        Sprite nextLevelSprite = SpriteFactory.endOfLevelSprite(scoreDisplay);
        nextLevelSprite.setX(4000);
        Sprite registerInformationSign = SpriteFactory.informationalSign(200, (int) floor.getY() - 220, "src/main/resources/assets/signs/StrategyInformationSign.png");

        Image activeFrame = new Image(Paths.get("src/main/resources/assets/strategies/jetpack/jetpack.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> enemyImageIdle = new ArrayList<>();
        enemyImageIdle.add(activeFrame);

        //Add them to animation object
//        nextLevelSprite.getAnimation().setAnimationLoopForState(AnimationState.IDLE, enemyImageIdle);
        //Set the observer to be idle
//        nextLevelSprite.getAnimation().setState(AnimationState.IDLE);
        Sprite platform1 = SpriteFactory.platform(100, 20, 1000, 450);
        platform1.setSpriteId(500); //set it high so we know it won't get overwritten upon insertion
        player.addCustomCollision(500, new CollideWithFloorNoClipBehavior(platform1));

        //bottom
        Sprite platform2 = SpriteFactory.floorPlatform(700, 20, Constants.WINDOW_X - 100, 300);
        platform2.setSpriteId(501);
        player.addCustomCollision(501, new CollideWithFloorNoClipBehavior(platform2));
        //up
        Sprite platform3 = SpriteFactory.floorPlatform(700, 20, Constants.WINDOW_X - 100, (int) (300 - player.getHeight()) - 20);
        platform3.setSpriteId(502);
        player.addCustomCollision(502, new CollideWithFloorNoClipBehavior(platform3));

        Sprite platform4 = SpriteFactory.platform(100, 20, (int) (platform2.getX() + platform2.getWidth() + 250), (int) (platform1.getY() - player.getHeight()));
        platform4.setSpriteId(503);
        player.addCustomCollision(503, new CollideWithFloorNoClipBehavior(platform4));

        Sprite redDoor1 = SpriteFactory.door(80, player.getHeight() + platform2.getHeight(), platform3.getX() + platform3.getWidth(), platform2.getY() - player.getHeight() - 10, Color.RED, "src/main/resources/assets/strategies/door/red_door.png");
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

//		Sprite enemyStrategy2 = SpriteFactory.strategyEnemies(bulletSizePowerUp, bulletSizePowerUp.getSpriteClassId(), 1000, 80);
        //new gun

        Sprite newRedGun = SpriteFactory.newGun(80, 50, Color.RED, platform1.getX() + 20, platform1.getY() - 120, "src/main/resources/assets/strategies/gun/gun1.png", "src/main/resources/assets/strategies/gun/gun_glow1.png");
        newRedGun.setSpriteClassId(SpriteClassIdConstants.GUN_RED);
        player.addCustomCollision(SpriteClassIdConstants.GUN_RED, new PickNewGunBehavior(player, newRedGun.getColor()));
        newRedGun.addCustomCollision(player.getSpriteClassId(), new DisableBehavior());

        redDoor1.addCustomCollision(SpriteClassIdConstants.BULLET_RED, new DisableBehavior());

//		Sprite newGreenGun = SpriteFactory.newGun(80, 50, Color.GREEN, Constants.WINDOW_X + 80, platform2.getY() - player.getHeight()/2, "src/main/resources/assets/strategies/gun/gun_green.png", "src/main/resources/assets/strategies/gun/gun_green_glow.png");
        Sprite newGreenGun = SpriteFactory.newGun(80, 50, Color.GREEN, Constants.WINDOW_X + 20, platform2.getY() - player.getHeight() / 2, "src/main/resources/assets/strategies/gun/gun_green.png", "src/main/resources/assets/strategies/gun/gun_green_glow.png");
        newGreenGun.setSpriteClassId(SpriteClassIdConstants.GUN_GREEN);
        player.addCustomCollision(SpriteClassIdConstants.GUN_GREEN, new PickNewGunBehavior(player, newGreenGun.getColor()));
        newGreenGun.addCustomCollision(player.getSpriteClassId(), new DisableBehavior());

        Sprite enemyRed = SpriteFactory.planeEnemies(30, 30, newGreenGun.getX() + 50, platform2.getY() - player.getHeight() / 2 - 20, 3, newGreenGun.getX() + newGreenGun.getWidth(), redDoor1.getX() - 100, SpriteClassIdConstants.BULLET_RED);
        enemyRed.addCustomCollision(SpriteClassIdConstants.BULLET_GREEN, new DisableBehavior());
//		Sprite bulletSprite = SpriteFactory.enemyBullet();
//		enemyRed.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new ShootWhenNearBehavior(player, bulletSprite)));

        //room 2
        Sprite platform5 = SpriteFactory.floorPlatform(700, 20, 1500, (int) (floor.getY() - (player.getHeight()) - 20));
        platform5.setSpriteId(504);
        player.addCustomCollision(504, new CollideWithFloorNoClipBehavior(platform5));
        Sprite greenDoor1 = SpriteFactory.door(80, player.getHeight() + platform2.getHeight(), platform5.getX(), (int) (floor.getY() - player.getHeight() - platform2.getHeight() + 5), Color.GREEN, "src/main/resources/assets/strategies/door/green_door.png");
        greenDoor1.addCustomCollision(SpriteClassIdConstants.BULLET_GREEN, new DisableBehavior());

        Sprite platform6 = SpriteFactory.platform(100, 20, 1200, 540);
        platform6.setSpriteId(506); //set it high so we know it won't get overwritten upon insertion
        player.addCustomCollision(506, new CollideWithFloorNoClipBehavior(platform6));


        Sprite wall1 = SpriteFactory.wall();
        wall1.setColor(Color.GRAY);
        wall1.setWidth(50);
        wall1.setHeight(Math.abs(platform5.getY() - floor.getY()));
        wall1.setX(platform5.getX() + platform5.getWidth());
        wall1.setY(platform5.getY());
        player.addCustomCollision(SpriteClassIdConstants.WALL, new MoveSetAmountBehavior(-20, 0));

        Sprite newYellowGun = SpriteFactory.newGun(80, 50, Color.YELLOW, wall1.getX() - 80, floor.getY() - player.getHeight() / 2, "src/main/resources/assets/strategies/gun/gun_yellow.png", "src/main/resources/assets/strategies/gun/gun_yellow_glow.png");
        newYellowGun.setSpriteClassId(SpriteClassIdConstants.GUN_YELLOW);
        player.addCustomCollision(SpriteClassIdConstants.GUN_YELLOW, new PickNewGunBehavior(player, newYellowGun.getColor()));
        newYellowGun.addCustomCollision(player.getSpriteClassId(), new DisableBehavior());

        Sprite platform7 = SpriteFactory.platform(100, 20, (int) (wall1.getX() + 180), 520);
        platform7.setSpriteId(507); //set it high so we know it won't get overwritten upon 
        player.addCustomCollision(507, new CollideWithFloorNoClipBehavior(platform7));

        Sprite platform10 = SpriteFactory.platform(100, 20, (int) (platform7.getX() + 300), (int) (platform7.getY() - 110));
        platform10.setSpriteId(520); //set it high so we know it won't get overwritten upon 
        player.addCustomCollision(520, new CollideWithFloorNoClipBehavior(platform10));

        Sprite platform11 = SpriteFactory.platform(100, 20, (int) (platform10.getX() + 300), (int) (platform10.getY() - 90));
        platform11.setSpriteId(525); //set it high so we know it won't get overwritten upon 
        player.addCustomCollision(525, new CollideWithFloorNoClipBehavior(platform11));


        //room 3
        //bottom
        Sprite platform8 = SpriteFactory.floorPlatform(700, 20, (int) nextLevelSprite.getX() - 600, 300);
        platform8.setSpriteId(531);
        player.addCustomCollision(531, new CollideWithFloorNoClipBehavior(platform8));
        //up
        Sprite platform9 = SpriteFactory.floorPlatform(700, 20, (int) nextLevelSprite.getX() - 600, (int) (300 - player.getHeight()));
        platform9.setSpriteId(540);
        player.addCustomCollision(540, new CollideWithFloorNoClipBehavior(platform9));

        Sprite yellowDoor1 = SpriteFactory.door(80, player.getHeight() + platform8.getHeight(), platform8.getX() - 80, platform9.getY(), Color.YELLOW, "src/main/resources/assets/strategies/door/yellow_door.png");
        yellowDoor1.addCustomCollision(SpriteClassIdConstants.BULLET_YELLOW, new DisableBehavior());
        player.addCustomCollision(SpriteClassIdConstants.DOOR, new MoveSetAmountBehavior(-20, 0));

        nextLevelSprite.setY(platform8.getY() - nextLevelSprite.getHeight());

        ArrayList<Sprite> sprites = new ArrayList<>();
//		Sprite computerIcon = SpriteFactory.computerIcon();
        Sprite messageFromHQ = SpriteFactory.messageFromHQ();
        Sprite popup = SpriteFactory.compositePopupInteractE(messageFromHQ);
        Sprite interactTrigger = SpriteFactory.interactTrigger(popup);

        sprites.add(popup);
        sprites.add(floor);
        sprites.add(registerInformationSign);
        sprites.add(popup);
        sprites.add(nextLevelSprite);
        sprites.add(newRedGun);
        sprites.add(scoreDisplay);
        sprites.add(platform1);
        sprites.add(platform2);
        sprites.add(platform3);
        sprites.add(platform4);
        sprites.add(platform5);
        sprites.add(greenDoor1);
        sprites.add(platform6);
        sprites.add(platform7);
        sprites.add(yellowDoor1);
        sprites.add(platform10);
        sprites.add(redDoor1);
        sprites.add(enemyRed);
        sprites.add(newGreenGun);
        sprites.add(wall1);
        sprites.add(platform11);
        sprites.add(platform8);
        sprites.add(platform9);
        sprites.add(player);
        sprites.add(newYellowGun);
        double minXBoundary = Math.abs(player.getX() - Constants.WINDOW_WIDTH / 2);
        double maxXBoundary = Math.abs(nextLevelSprite.getX() - Constants.WINDOW_WIDTH + nextLevelSprite.getWidth() + 30);
        Level strategyLevel = new Level(Constants.STRATEGY_LEVEL_NUMBER, sprites, "Level_Music.mp3", minXBoundary, maxXBoundary);
        strategyLevel.setFocusSprite(player);
        return strategyLevel;
    }
}
