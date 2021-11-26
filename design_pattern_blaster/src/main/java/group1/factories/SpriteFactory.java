package group1.factories;
import group1.model.sprite.behavior.*;
import group1.App;
import group1.model.sprite.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.nio.file.Paths;
import java.util.ArrayList;

import group1.constants.Constants;
import group1.model.sprite.game_event.GameEvent;
import group1.viewcontroller.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import javax.swing.text.View;
import java.nio.file.Paths;
import java.util.ArrayList;


//Creational class for making sprites
//Right now everything is a Factory Method but we could refactor to Builder later
public final class SpriteFactory
{
    private SpriteFactory() {}

    //Player sprite: not the final version by any stretch of the imagination
    public static Sprite player()
    {
        Sprite playerSprite = new Sprite();
        playerSprite.setX(Constants.WINDOW_WIDTH/2 -25);
        //playerSprite.setY(Constants.WINDOW_HEIGHT - 200);
        playerSprite.setY(Constants.PLAYER_Y);
        playerSprite.setWidth(50);
        playerSprite.setHeight(Constants.PLAYER_HEIGHT);
        playerSprite.setSpriteClassId(SpriteClassIdConstants.PLAYER);
        playerSprite.setDirection(Constants.LEFT);
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.A, -1*Constants.PLAYER_DX)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.D, Constants.PLAYER_DX)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.A), new UpdateVelocityXBehavior(0)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.D), new UpdateVelocityXBehavior(0)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new GravityBehavior(Constants.GRAVITY)));
        //Order is starting to matter for this process - JumpBehavior must come AFTER GravityBehavior
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new JumpBehavior(KeyCode.W, -12)));
        //Likewise, MoveBehavior must come AFTER all behaviors that affect velocity
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new MoveBehavior()));
        playerSprite.setColor(Color.BLUE);
        ArrayList<Image> playerImageRight;
        if(playerSprite.getAnimation().getAnimationLoopForState(AnimationState.RIGHT_MOVEMENT)==null)
        {
            playerImageRight = new ArrayList<Image>();
        }
        else
        {
            playerImageRight = playerSprite.getAnimation().getAnimationLoopForState(AnimationState.RIGHT_MOVEMENT);
        }

        ArrayList<String> imageRightPaths = new ArrayList<String>();
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x/walk_right_frame1_0.2x.png");
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x/walk_right_frame2_0.2x.png");
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x/walk_right_frame3_0.2x.png");
        for(String imagePath: imageRightPaths)
        {
            playerImageRight.add(new Image(Paths.get(imagePath).toUri().toString()));
        }
        playerSprite.getAnimation().setAnimationLoopForState(AnimationState.RIGHT_MOVEMENT, playerImageRight);
        playerSprite.getAnimation().setState(AnimationState.RIGHT_MOVEMENT);

        ArrayList<Image> playerImageLeft;
        if(playerSprite.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT)==null)
        {
            playerImageLeft = new ArrayList<Image>();
        }
        else
        {
            playerImageLeft = playerSprite.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT);
        }
        ArrayList<String> imageLeftPaths = new ArrayList<String>();
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x/walk_left_frame1_0.2x.png");
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x/walk_left_frame2_0.2x.png");
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x/walk_left_frame3_0.2x.png");
        for(String imagePath: imageLeftPaths)
        {
            playerImageLeft.add(new Image(Paths.get(imagePath).toUri().toString()));
        }

        playerSprite.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, playerImageLeft);
        playerSprite.getAnimation().setState(AnimationState.LEFT_MOVEMENT);

        Sprite bulletSprite = bullet();

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.SPACE), new ShootSpriteBehavior((int)(playerSprite.getWidth()+30), (int)(playerSprite.getHeight() * 0.5), bulletSprite)));
        return playerSprite;
    }

    public static Sprite bullet()
    {
        Sprite bulletSprite = new Sprite();
        bulletSprite.setX(100);
        bulletSprite.setY(80);
        bulletSprite.setWidth(24);
        bulletSprite.setHeight(24);
        bulletSprite.setVelocityX(40);
//        bulletSprite.setDirection(Constants.LEFT);
        bulletSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));
        bulletSprite.setColor(Color.ORANGE);
        bulletSprite.setDefaultCollisionBehavior(new DisableBehavior());
        bulletSprite.addCustomCollision(SpriteClassIdConstants.SUBORDINATE, new DoNothingBehavior());
        bulletSprite.setSpriteClassId(SpriteClassIdConstants.BULLET);
        return bulletSprite;
    }

    public static Sprite enemyBullet()
    {
        Sprite bulletSprite = new Sprite();
        bulletSprite.setWidth(24);
        bulletSprite.setHeight(24);
        bulletSprite.setVelocityX(5);
        bulletSprite.setVelocityY(5);
        bulletSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new MoveBehavior()));
        bulletSprite.setColor(Color.RED);
        bulletSprite.setDefaultCollisionBehavior(new DisableBehavior());
        bulletSprite.addCustomCollision(SpriteClassIdConstants.ENEMY, new DoNothingBehavior());
        bulletSprite.setSpriteClassId(SpriteClassIdConstants.ENEMY_BULLET);
        return bulletSprite;
    }

    public static Sprite viewSprite()
    {
        Sprite viewSprite = new Sprite();
        viewSprite.setWidth(50);
        viewSprite.setHeight(Constants.WINDOW_HEIGHT);
        viewSprite.setVelocityX(0);
        viewSprite.setVelocityY(0);
        viewSprite.setColor(Color.GRAY);
        viewSprite.setDefaultCollisionBehavior(new DoNothingBehavior());
        viewSprite.setSpriteClassId(-9); //placeholder
        viewSprite.setDirection(Constants.LEFT);


        ViewBehavior viewBehavior = new ViewBehavior();
        viewBehavior.setShootSpriteBehavior(viewBehavior);
        viewSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), viewBehavior));
        return viewSprite;
    }

    public static Sprite wall()
    {
        Sprite wallSprite = new Sprite();
        wallSprite.setWidth(1000);
        wallSprite.setHeight(1000);
        wallSprite.setVelocityX(0);
        wallSprite.setVelocityY(0);
        wallSprite.setColor(Color.BLACK);
        wallSprite.setDefaultCollisionBehavior(new DoNothingBehavior());
        wallSprite.setSpriteClassId(SpriteClassIdConstants.WALL);
        return wallSprite;
    }

    public static Sprite dummyFocusSprite()
    {
        Sprite dumStupid = new Sprite();
        dumStupid.setX(Constants.WINDOW_WIDTH / 2 - 25);
        dumStupid.setY(Constants.WINDOW_HEIGHT - 200);
        dumStupid.disable();
        return dumStupid;
    }

    public static Sprite MVCPlayer()
    {
        Sprite playerSprite = player();
        playerSprite.getEventBehaviors().clear();
        playerSprite.setVelocityY(30);
        playerSprite.getCustomCollisionMap().addCustomCollision(SpriteClassIdConstants.WALL, new MoveSetAmountBehavior(10, 0));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.A, -1*Constants.PLAYER_DX)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.D, Constants.PLAYER_DX)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.A), new UpdateVelocityXBehavior(0)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.D), new UpdateVelocityXBehavior(0)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new GravityBehavior(Constants.GRAVITY)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new JetPackBehavior(KeyCode.W, -1)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new MoveBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.SPACE),
                new ShootSpriteBehavior((int) (playerSprite.getWidth() + 30),
                        (int) (playerSprite.getHeight() * 0.78), bullet())));

        int viewBehaviorCollisionID = -9;
        playerSprite.addCustomCollision(viewBehaviorCollisionID, new MoveSetAmountBehavior(-playerSprite.getVelocityX(),0));
        return playerSprite;
    }

    public static Sprite floor(int width, int height)
    {
        Sprite floor = new Sprite();
        floor.setLayer(SpriteClassIdConstants.FLOOR);
        floor.setX(-1 * (int)(width/2));
        floor.setY(Constants.FLOOR_Y);
        floor.setWidth(width);
        floor.setHeight(height);
        floor.setSpriteClassId(SpriteClassIdConstants.FLOOR);
        floor.setColor(Color.BLACK);
        floor.setDefaultCollisionBehavior(new DoNothingBehavior());
        return floor;
    }

    public static Sprite platform(int width, int height, int x, int y)
    {
        Sprite floor = new Sprite();
        floor.setLayer(SpriteClassIdConstants.FLOOR);
        floor.setX(x);
        floor.setY(y);
        floor.setWidth(width);
        floor.setHeight(height);
        floor.setSpriteClassId(SpriteClassIdConstants.FLOOR);
        floor.setColor(Color.BLACK);
        floor.setDefaultCollisionBehavior(new DoNothingBehavior());
        return floor;
    }

    public static Sprite demoEnemy1()
    {
        Sprite enemy = new Sprite();
        enemy.setWidth(50);
        enemy.setSpriteClassId(-2);
        enemy.setHeight(100);
        enemy.setX(100);
        enemy.setY(Constants.WINDOW_HEIGHT - 150);
        enemy.setColor(Color.RED);
        enemy.setVelocityX(-1);
        enemy.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));
        enemy.getCustomCollisionMap().addCustomCollision(SpriteClassIdConstants.BULLET, new DisableBehavior());
        return enemy;
    }

    public static Sprite demoEnemy2()
    {
        Sprite enemy1 = demoEnemy1();
        enemy1.setX(Constants.WINDOW_WIDTH - 100);
        enemy1.setColor(Color.RED);
        enemy1.setVelocityX(1);
        enemy1.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));
        enemy1.getCustomCollisionMap().addCustomCollision(SpriteClassIdConstants.BULLET, new DisableBehavior());
        return enemy1;
    }

    public static Sprite endOfLevelSprite()
    {
        Sprite endOfLevelSprite = new Sprite();
        endOfLevelSprite.setWidth(50);
        endOfLevelSprite.setHeight(50);
        endOfLevelSprite.setX(1400);
        endOfLevelSprite.setY(Constants.WINDOW_HEIGHT - 150);
        endOfLevelSprite.setColor(Color.GOLD);
        endOfLevelSprite.getCustomCollisionMap().addCustomCollision(SpriteClassIdConstants.PLAYER, new LoadNextLevelBehavior());
        return endOfLevelSprite;
    }

	public static Sprite commander(Sprite subordinate)
    {
		Sprite commander = new Sprite();
		commander.setWidth(50);
		commander.setHeight(50);
		commander.setLayer(Constants.BACKGROUND);
//		commander.setVelocityX(10);
        commander.setX(100);
        commander.setY(Constants.WINDOW_HEIGHT - 150);
        commander.setColor(Color.RED);

        Sprite commandBullet = commandBullet();

        CommanderBehavior commanderBehavior = new CommanderBehavior();
        commanderBehavior.setShootSpriteBehavior(new ShootDiagonallyAtTargetBehavior((int) (commander.getWidth() + 10), (int) (commander.getHeight() * 0.78), commandBullet, 10, subordinate));
        commander.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), commanderBehavior));

        return commander;
    }


	public static Sprite commandBullet()
    {
        Sprite bulletSprite = new Sprite();
        bulletSprite.setX(100);
        bulletSprite.setY(80);
        bulletSprite.setWidth(30);
        bulletSprite.setHeight(30);
        bulletSprite.setVelocityX(2);
//        bulletSprite.setDirection(Constants.LEFT);
        bulletSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new MoveBehavior()));
        bulletSprite.setColor(Color.GREEN);
        bulletSprite.setDefaultCollisionBehavior(new DisableBehavior());
        bulletSprite.setSpriteClassId(SpriteClassIdConstants.COMMAND);
        return bulletSprite;
    }

	public static Sprite subordinate()
	{
		Sprite subordinate = new Sprite();
		subordinate.setWidth(40);
		subordinate.setHeight(50);
		subordinate.setX(400);
		subordinate.setY(Constants.WINDOW_HEIGHT - 150);
		subordinate.setColor(Color.YELLOW);
		subordinate.setSpriteClassId(SpriteClassIdConstants.SUBORDINATE);

		Sprite bulletSprite = enemyBullet();
        bulletSprite.addCustomCollision(SpriteClassIdConstants.BULLET, new DoNothingBehavior());
		bulletSprite.setHeight(100);
		bulletSprite.setWidth(100);
		bulletSprite.setVelocityY(0);
		bulletSprite.setVelocityX(10);

		subordinate.addCustomCollision(SpriteClassIdConstants.COMMAND, new ShootSpriteBehavior((int)(0),(int)(subordinate.getHeight() - 50), bulletSprite));
		subordinate.addCustomCollision(SpriteClassIdConstants.BULLET, new DoNothingBehavior());
		return subordinate;
	}

    public static Sprite observer(Sprite observable, int x, int y)
    {
        Sprite observer = new Sprite();
        observer.setSpriteClassId(SpriteClassIdConstants.ENEMY);
        observer.setWidth(50);
        observer.setHeight(50);
        observer.setVelocityX(5);
        observer.setX(x);
        observer.setY(y);
        observer.setColor(Color.GOLD);

		Sprite bulletSprite = enemyBullet();

		OldObserverBehavior observerBehavior = new OldObserverBehavior(observable, 250, 5);
		observerBehavior.setShootSpriteBehavior(new ShootAtPlayerBehavior(0, (int)(observer.getHeight() + 20), bulletSprite, 20));

		observer.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), observerBehavior));
		observer.addCustomCollision(SpriteClassIdConstants.BULLET, new DisableBehavior());
		observer.setDefaultCollisionBehavior(new DoNothingBehavior());

		return observer;
	}

	public static Sprite notifier()
	{
		return demoEnemy2();
	}

	public static Sprite factory(Sprite blueprint, int spawnInterval)
	{
		Sprite factory = new Sprite();
		factory.setWidth(100);
		factory.setHeight(400);
        factory.setX(1000);
		factory.setY(Constants.WINDOW_HEIGHT - factory.getHeight());
		factory.setHealth(10);
		factory.setColor(Color.PURPLE);
		FactoryBehavior factoryBehavior = new FactoryBehavior(blueprint, spawnInterval);
		factory.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), factoryBehavior));

        //This is how to make an enemy require a certain number of bullets to kill them

        //Make them take damage from bullets
		factory.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));

        //Just for visual flair - enemy will flash that color when they're damaged
		factory.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new FlashColorWhenDamagedBehavior(Color.RED)));

        //Make them check their health every tick and send themselves a HealthDepletedEvent if they're <= 0
		factory.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));

        //Make them disable on HealthDepletedEvent
        factory.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));

		return factory;
	}
	public static Sprite strategyEnemies(Sprite powerUpSpriteToFollow, int powerUpID, int x, int y) {
		
		Sprite strategySpriteEnemy = new Sprite();
		strategySpriteEnemy.setWidth(50);
		strategySpriteEnemy.setHeight(50);
		strategySpriteEnemy.setX(x);
		strategySpriteEnemy.setY(y);
		strategySpriteEnemy.setHealth(10);

        //Just for visual flair - enemy will flash that color when they're damaged
		strategySpriteEnemy.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new FlashColorWhenDamagedBehavior(Color.RED)));

        //Make them check their health every tick and send themselves a HealthDepletedEvent if they're <= 0
		strategySpriteEnemy.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));

        //Make them disable on HealthDepletedEvent
        strategySpriteEnemy.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		strategySpriteEnemy.setColor(Color.PURPLE);
		Sprite bulletSprite = enemyBullet();
		
		StrategyBehavior strategyBehavior = new StrategyBehavior(powerUpSpriteToFollow);
		strategySpriteEnemy.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), strategyBehavior));
		strategySpriteEnemy.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
		strategyBehavior.setShootSpriteBehavior(new ShootAtPlayerBehavior(0, (int)(strategySpriteEnemy.getHeight() + 20), bulletSprite, 20));
		
		
		if(powerUpID == SpriteClassIdConstants.TAKE_NO_DAMAGE_POWERUP) {
			powerUpSpriteToFollow.addCustomCollision(strategySpriteEnemy.getSpriteClassId(), new DisableBehavior());
			
//			strategySpriteEnemy.addCustomCollision(powerUpID, new PowerUpBehavior());
			strategySpriteEnemy.addCustomCollision(powerUpID, new PowerUpBehavior(strategyBehavior));
			
//			strategySpriteEnemy.addEventBehavior(new EventBehavior(GameEvent.PowerUpEvent(), new PowerUpBehavior(strategyBehavior.setSpriteToMoveTowards(player)));

			
//			TakeNoDamageBehavior tndb = new TakeNoDamageBehavior(strategySpriteEnemy);
//			tndb.isBehaviorSet(true);
			strategyBehavior.setSpriteStrategy(new TakeNoDamageBehavior(strategySpriteEnemy));
			
			strategySpriteEnemy.addEventBehavior(new EventBehavior(GameEvent.PowerUpEndEvent(), new PowerUpEndBehavior(strategyBehavior)));
		}
		
		
		if(powerUpID == SpriteClassIdConstants.SIZE_INCREASE_POWERUP) {
			powerUpSpriteToFollow.addCustomCollision(strategySpriteEnemy.getSpriteClassId(), new DisableBehavior());
			
//			strategySpriteEnemy.addCustomCollision(powerUpID, new PowerUpBehavior());
			strategySpriteEnemy.addCustomCollision(powerUpID, new PowerUpBehavior(strategyBehavior));
			
//			strategySpriteEnemy.addEventBehavior(new EventBehavior(GameEvent.PowerUpEvent(), new PowerUpBehavior(strategyBehavior.setSpriteToMoveTowards(player)));

			
//			TakeNoDamageBehavior tndb = new TakeNoDamageBehavior(strategySpriteEnemy);
//			tndb.isBehaviorSet(true);
			strategyBehavior.setSpriteStrategy(new IncreaseSpriteSizeBehavior(bulletSprite));
			
			strategySpriteEnemy.addEventBehavior(new EventBehavior(GameEvent.PowerUpEndEvent(), new PowerUpEndBehavior(strategyBehavior)));
		}
		return strategySpriteEnemy;
    }

	public static CompositeSprite compositeEnemy() {
		CompositeSprite compositeEnemyBlueprint = new CompositeSprite();
		compositeEnemyBlueprint.setSpriteClassId(SpriteClassIdConstants.ENEMY);
		compositeEnemyBlueprint.setWidth(150);
		compositeEnemyBlueprint.setHeight(80);
		compositeEnemyBlueprint.setVelocityX(-5);
		compositeEnemyBlueprint.setX(720);
		compositeEnemyBlueprint.setY(350);
		compositeEnemyBlueprint.setHealth(1);
		compositeEnemyBlueprint.setColor(Color.CORAL);

		compositeEnemyBlueprint.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
		compositeEnemyBlueprint.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
        compositeEnemyBlueprint.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));


		CompositeSprite compositeEnemy = compositeEnemyBlueprint.copy();

		compositeEnemyBlueprint.setWidth(70);
		compositeEnemyBlueprint.setY(430);
		CompositeSprite l1l1 = compositeEnemyBlueprint.copy();

		// TODO: Fix copy of event behaviors and collision maps
//		l1l1.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l1l1.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l1l1.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l1l1.setX(660);

		CompositeSprite l1r1 = compositeEnemyBlueprint.copy();
//		l1r1.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l1r1.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l1r1.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l1r1.setX(860);
		compositeEnemy.setLeft(l1l1);
		compositeEnemy.setRight(l1r1);

		compositeEnemyBlueprint.setWidth(50);
		compositeEnemyBlueprint.setY(480);
		CompositeSprite l2l1 = compositeEnemyBlueprint.copy();
//		l2l1.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l2l1.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l2l1.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l2l1.setX(620);
		CompositeSprite l2r1 = compositeEnemyBlueprint.copy();
//		l2r1.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l2r1.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l2r1.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l2r1.setX(720);
		CompositeSprite l2l2 = compositeEnemyBlueprint.copy();
//		l2l2.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l2l2.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l2l2.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l2l2.setX(820);
		CompositeSprite l2r2 = compositeEnemyBlueprint.copy();
//		l2r2.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l2r2.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l2r2.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l2r2.setX(920);
		l1l1.setLeft(l2l1);
		l1l1.setRight(l2r1);
		l1r1.setLeft(l2l2);
		l1r1.setRight(l2r2);

		compositeEnemyBlueprint.setWidth(40);
		compositeEnemyBlueprint.setY(530);
		CompositeSprite l3l1 = compositeEnemyBlueprint.copy();
//		l3l1.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l3l1.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l3l1.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l3l1.setX(600);
		CompositeSprite l3r1 = compositeEnemyBlueprint.copy();
//		l3r1.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l3r1.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l3r1.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l3r1.setX(650);
		CompositeSprite l3l2 = compositeEnemyBlueprint.copy();
//		l3l2.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l3l2.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l3l2.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l3l2.setX(700);
		CompositeSprite l3r2 = compositeEnemyBlueprint.copy();
//		l3r2.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l3r2.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l3r2.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l3r2.setX(750);
		CompositeSprite l3l3 = compositeEnemyBlueprint.copy();
//		l3l3.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l3l3.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l3l3.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l3l3.setX(800);
		CompositeSprite l3r3 = compositeEnemyBlueprint.copy();
//		l3r3.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l3r3.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l3r3.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l3r3.setX(850);
		CompositeSprite l3l4 = compositeEnemyBlueprint.copy();
//		l3l4.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l3l4.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l3l4.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l3l4.setX(900);
		CompositeSprite l3r4 = compositeEnemyBlueprint.copy();
//		l3r4.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
//		l3r4.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
//		l3r4.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		l3r4.setX(950);
		l2l1.setLeft(l3l1);
		l2l1.setRight(l3r1);
		l2r1.setLeft(l3l2);
		l2r1.setRight(l3r2);
		l2l2.setLeft(l3l3);
		l2l2.setRight(l3r3);
		l2r2.setLeft(l3l4);
		l2r2.setRight(l3r4);
		return compositeEnemy;
	}

	public static Sprite observablePlayer() 
	{
		Sprite player = player();
		//Do some stuff with observer behavior
		return player;
	}

	//Blank observer sprite which can be registered to the player at runtime by another sprite
	public static Sprite observer() 
	{
        Sprite observer = new Sprite();
        observer.setSpriteClassId(SpriteClassIdConstants.ENEMY);
        observer.setWidth(50);
        observer.setHeight(50);
        observer.setVelocityX(5);
        observer.setX(1000);
        observer.setY(500);
        observer.setColor(Color.GOLD);

		Sprite bulletSprite = enemyBullet();

		ObserverBehavior observerBehavior = new ObserverBehavior(250, 5);
		observerBehavior.setShootSpriteBehavior(new ShootAtPlayerBehavior(0, (int)(observer.getHeight() + 20), bulletSprite, 20));

		observer.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), observerBehavior));
		observer.addCustomCollision(SpriteClassIdConstants.BULLET, new DisableBehavior());
		observer.setDefaultCollisionBehavior(new DoNothingBehavior());

		return observer;
	}
	
	public static Sprite registerObserverButton(Sprite observable, ArrayList<Sprite> observers)
	{
		Sprite button = new Sprite();
		ObservableBehavior ob = (ObservableBehavior)observable.getObservableBehavior();
		ArrayList<ObserverBehavior> observerBehaviors = new ArrayList<>();
		for (Sprite s : observers)
		{
			observerBehaviors.add((ObserverBehavior)s.getObserverBehavior());
		}
		button.addCustomCollision(SpriteClassIdConstants.PLAYER, new RegisterObserversBehavior(ob, observerBehaviors));
		return button;
	}
	
	public static Sprite unregisterObserverButton(Sprite observable)
	{
		Sprite button = new Sprite();
		ObservableBehavior ob = (ObservableBehavior) observable.getObservableBehavior();
		button.addCustomCollision(SpriteClassIdConstants.PLAYER, new UnregisterObserverBehavior(ob));
		return button;
	}
}
