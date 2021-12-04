package group1.factories;
import group1.App;
import group1.model.sprite.behavior.*;
import group1.model.sprite.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.nio.file.Paths;
import java.util.ArrayList;

import group1.constants.Constants;
import group1.model.sprite.game_event.GameEvent;


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
        playerSprite.setVelocityY(-0.1);
        playerSprite.setWidth(50);
        playerSprite.setHeight(Constants.PLAYER_HEIGHT);
        playerSprite.setSpriteClassId(SpriteClassIdConstants.PLAYER);
        playerSprite.setDirection(Constants.LEFT);
        playerSprite.setHealth(10);
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.A, -1*Constants.PLAYER_DX)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.D, Constants.PLAYER_DX)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.A), new UpdateVelocityXBehavior(0)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.D), new UpdateVelocityXBehavior(0)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new PlayerMoveBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new PlayerGravityBehavior(Constants.GRAVITY)));
        //Order is starting to matter for this process - JumpBehavior must come AFTER GravityBehavior
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new JumpBehavior(KeyCode.W, -12)));
        //Likewise, MoveBehavior must come AFTER all behaviors that affect velocity
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
        playerSprite.getAnimation().setPreviousState(AnimationState.RIGHT_MOVEMENT);
        playerSprite.getAnimation().setState(AnimationState.LEFT_MOVEMENT);

        Sprite bulletSprite = bullet();

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.SPACE), new ShootSpriteBehavior((int)(playerSprite.getWidth()+30), (int)(playerSprite.getHeight() * 0.5), bulletSprite)));

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new ReloadLevelBehavior()));
        playerSprite.addCustomCollision(SpriteClassIdConstants.ENEMY_BULLET, new DecrementHealthBehavior());

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
        //viewSprite.setWidth(50);
        //viewSprite.setHeight(Constants.WINDOW_HEIGHT);
        viewSprite.setVelocityX(0);
        viewSprite.setVelocityY(0);
        viewSprite.setX(viewSprite.getX()+60);
        viewSprite.setColor(Color.GRAY);
        viewSprite.setDefaultCollisionBehavior(new DoNothingBehavior());
        viewSprite.setSpriteClassId(-9); //placeholder
        viewSprite.setDirection(Constants.LEFT);
        Image avatar = new Image(Paths.get("src/main/resources/assets/MVC/MVCRequestSender.png").toUri().toString());
        ArrayList<Image> avatarAppearance = new ArrayList<>();
        avatarAppearance.add(avatar);
        viewSprite.getAnimation().setAnimationLoopForState(AnimationState.IDLE, avatarAppearance);
        viewSprite.getAnimation().setState(AnimationState.IDLE);

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

    public static Sprite Timer(boolean scoreFunctionality)
    {
        Sprite timer = new Sprite();
        //Upper left corner
        //x and y coordinates determine offset from 0,0 (true x position follows camera)
        timer.setX(5); //for some reason 0 is not the true upper left
        timer.setY(150);
        timer.setWidth(0);
        timer.setHeight(0);
        timer.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(),new TimeScoreBehavior(scoreFunctionality)));
        return timer;
    }

    public static Sprite MVCPlayer()
    {
        Sprite playerSprite = player();
        playerSprite.setHealth(Integer.MAX_VALUE);
        playerSprite.getEventBehaviors().clear();
        playerSprite.setVelocityY(0.1);
        playerSprite.getCustomCollisionMap().addCustomCollision(SpriteClassIdConstants.WALL, new MoveSetAmountBehavior(20, 0));
       // playerSprite.getCustomCollisionMap().addCustomCollision(SpriteClassIdConstants.CEILING, new MoveSetAmountBehavior(0, 50));

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.A, -1*Constants.PLAYER_DX)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.D, Constants.PLAYER_DX)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.A), new UpdateVelocityXBehavior(0)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.D), new UpdateVelocityXBehavior(0)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new PlayerGravityBehavior(Constants.GRAVITY)));
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
        //floor.setLayer(SpriteClassIdConstants.FLOOR);
        floor.setX(-1 * (int)(width/2));
        floor.setY(Constants.FLOOR_Y);
        floor.setWidth(width);
        floor.setHeight(height);
        floor.setSpriteClassId(SpriteClassIdConstants.FLOOR);
        floor.setColor(Color.GRAY);
        floor.setDefaultCollisionBehavior(new DoNothingBehavior());
        return floor;
    }

    public static Sprite platform(int width, int height, int x, int y)
    {
        Sprite floor = new Sprite();
        //floor.setLayer(SpriteClassIdConstants.FLOOR);
        floor.setX(x);
        floor.setY(y);
        floor.setWidth(width);
        floor.setHeight(height);
        floor.setSpriteClassId(SpriteClassIdConstants.FLOOR);
        floor.setColor(Color.GRAY);
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

    public static Sprite endOfLevelSprite(Sprite scoreSprite)
    {
        Sprite endOfLevelSprite = new Sprite();
        endOfLevelSprite.setWidth(50);
        endOfLevelSprite.setHeight(50);
        endOfLevelSprite.setX(1400);
        endOfLevelSprite.setY(Constants.WINDOW_HEIGHT - 150);
        endOfLevelSprite.setColor(Color.GOLD);
        endOfLevelSprite.getCustomCollisionMap().addCustomCollision(SpriteClassIdConstants.PLAYER, new LoadNextLevelBehavior(scoreSprite));
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

	public static Sprite InvisibleSubordinate()
	{
		Sprite subordinate = new Sprite();
		subordinate.setWidth(40);
		subordinate.setHeight(50);
		subordinate.setX(100);
		subordinate.setY(Constants.WINDOW_HEIGHT - 150);
		subordinate.setColor(Color.TRANSPARENT);
		subordinate.setSpriteClassId(SpriteClassIdConstants.SUBORDINATE);

		Sprite bulletSprite = enemyBullet();
		bulletSprite.setSpriteClassId(SpriteClassIdConstants.COMMAND);
		bulletSprite.setHeight(30);
		bulletSprite.setWidth(30);
		bulletSprite.setVelocityY(0);
		bulletSprite.setVelocityX(10);
		bulletSprite.setColor(Color.GREEN);

		subordinate.addCustomCollision(SpriteClassIdConstants.COMMAND, new ShootSpriteBehavior((int)(20),(int)(subordinate.getHeight() - 70), bulletSprite));
		subordinate.addCustomCollision(SpriteClassIdConstants.BULLET, new DoNothingBehavior());
		return subordinate;
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

		subordinate.addCustomCollision(SpriteClassIdConstants.COMMAND, new ShootSpriteBehavior((int)(0),(int)(subordinate.getHeight() - 110), bulletSprite));
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

			strategySpriteEnemy.addCustomCollision(powerUpID, new PowerUpBehavior(strategyBehavior));

			strategyBehavior.setSpriteStrategy(new TakeNoDamageBehavior(strategySpriteEnemy));

			strategySpriteEnemy.addEventBehavior(new EventBehavior(GameEvent.PowerUpEndEvent(), new PowerUpEndBehavior(strategyBehavior)));
		}


		if(powerUpID == SpriteClassIdConstants.SIZE_INCREASE_POWERUP) {
			powerUpSpriteToFollow.addCustomCollision(strategySpriteEnemy.getSpriteClassId(), new DisableBehavior());

			strategySpriteEnemy.addCustomCollision(powerUpID, new PowerUpBehavior(strategyBehavior));



			strategyBehavior.setSpriteStrategy(new IncreaseSpriteSizeBehavior(bulletSprite));

			strategySpriteEnemy.addEventBehavior(new EventBehavior(GameEvent.PowerUpEndEvent(), new PowerUpEndBehavior(strategyBehavior)));
		}
		return strategySpriteEnemy;
    }

	public static Sprite bulletPowerUp() {
		Sprite bulletSizePowerUp = new Sprite();
		bulletSizePowerUp.setWidth(80);
		bulletSizePowerUp.setSpriteClassId(SpriteClassIdConstants.SIZE_INCREASE_POWERUP);
		bulletSizePowerUp.setHeight(50);
		bulletSizePowerUp.setX(200);
		bulletSizePowerUp.setY(Constants.WINDOW_HEIGHT / 2 - 200);
		bulletSizePowerUp.setColor(Color.MAGENTA);

		//Getting images
        Image image1 = new Image(Paths.get("src/main/resources/assets/strategies/bullet/bullet1.png").toUri().toString());
        Image image2 = new Image(Paths.get("src/main/resources/assets/strategies/bullet/bullet_glow1.png").toUri().toString());

        ArrayList<Image> imageIdle = new ArrayList<>();
        imageIdle.add(image1);
        imageIdle.add(image2);

      //Add them to animation object
        bulletSizePowerUp.getAnimation().setAnimationLoopForState(AnimationState.IDLE, imageIdle);
        bulletSizePowerUp.getAnimation().setState(AnimationState.IDLE);

        return bulletSizePowerUp;
	}

	public static Sprite pickNewGun() {
		Sprite newGun = new Sprite();
		newGun.setWidth(80);
		newGun.setSpriteClassId(SpriteClassIdConstants.PICKUP_NEW_GUN);
		newGun.setHeight(50);
		newGun.setX(900);
		newGun.setY(Constants.WINDOW_HEIGHT / 2 - 50);
		newGun.setColor(Color.MAGENTA);

		//Getting images
        Image image1 = new Image(Paths.get("src/main/resources/assets/strategies/gun/gun1.png").toUri().toString());
        Image image2 = new Image(Paths.get("src/main/resources/assets/strategies/gun/gun_glow1.png").toUri().toString());

        ArrayList<Image> imageIdle = new ArrayList<>();
        imageIdle.add(image1);
        imageIdle.add(image2);

      //Add them to animation object
        newGun.getAnimation().setAnimationLoopForState(AnimationState.IDLE, imageIdle);
        newGun.getAnimation().setState(AnimationState.IDLE);

        return newGun;


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
		l1l1.setX(660);
		CompositeSprite l1r1 = compositeEnemyBlueprint.copy();
		l1r1.setX(860);
		compositeEnemy.setLeft(l1l1);
		compositeEnemy.setRight(l1r1);

		compositeEnemyBlueprint.setWidth(50);
		compositeEnemyBlueprint.setY(480);
		CompositeSprite l2l1 = compositeEnemyBlueprint.copy();
		l2l1.setX(620);
		CompositeSprite l2r1 = compositeEnemyBlueprint.copy();
		l2r1.setX(720);
		CompositeSprite l2l2 = compositeEnemyBlueprint.copy();
		l2l2.setX(820);
		CompositeSprite l2r2 = compositeEnemyBlueprint.copy();
		l2r2.setX(920);
		l1l1.setLeft(l2l1);
		l1l1.setRight(l2r1);
		l1r1.setLeft(l2l2);
		l1r1.setRight(l2r2);

		compositeEnemyBlueprint.setWidth(40);
		compositeEnemyBlueprint.setY(530);
		CompositeSprite l3l1 = compositeEnemyBlueprint.copy();
		l3l1.setX(600);
		CompositeSprite l3r1 = compositeEnemyBlueprint.copy();
		l3r1.setX(650);
		CompositeSprite l3l2 = compositeEnemyBlueprint.copy();
		l3l2.setX(700);
		CompositeSprite l3r2 = compositeEnemyBlueprint.copy();
		l3r2.setX(750);
		CompositeSprite l3l3 = compositeEnemyBlueprint.copy();
		l3l3.setX(800);
		CompositeSprite l3r3 = compositeEnemyBlueprint.copy();
		l3r3.setX(850);
		CompositeSprite l3l4 = compositeEnemyBlueprint.copy();
		l3l4.setX(900);
		CompositeSprite l3r4 = compositeEnemyBlueprint.copy();
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

	public static Sprite computerIcon()
	{
		Sprite computerIcon = new Sprite();
		computerIcon.setSpriteClassId(SpriteClassIdConstants.INTERACTABLE);
        computerIcon.setX(50);
        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/composite/0.5x/computer_icon_0.5x.png").toUri().toString());
        computerIcon.setY(Constants.FLOOR_Y-idleFrame.getHeight());

        //Put them in arraylists
        ArrayList<Image> computerImageIdle = new ArrayList<>();
        computerImageIdle.add(idleFrame);

        //Add them to animation object
        computerIcon.getAnimation().setAnimationLoopForState(AnimationState.IDLE, computerImageIdle);

        //Set the observer to be idle
        computerIcon.getAnimation().setState(AnimationState.IDLE);
        return computerIcon;
    }

	public static Sprite compositePopupInteractE(Sprite messageFromHQ)
	{
		Sprite interactPopup = new Sprite();
		interactPopup.setSpriteClassId(SpriteClassIdConstants.INTERACTABLE);
        interactPopup.setWidth(160);
        interactPopup.setHeight(160);
        interactPopup.setX(30);
        interactPopup.setLayer(49);
        interactPopup.setY(Constants.FLOOR_Y-240);
        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/composite/0.2x/press_to_interact_popup_0.2x.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> popupImageIdle = new ArrayList<>();
        popupImageIdle.add(idleFrame);

        //Add them to animation object
        interactPopup.getAnimation().setAnimationLoopForState(AnimationState.IDLE, popupImageIdle);
        interactPopup.disable();

        ArrayList<Sprite> boxes = new ArrayList<Sprite>();

        Sprite purpleBox = new Sprite();
        purpleBox.setLayer(51);
        Image purpleFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/Purple_1x.png").toUri().toString());
        ArrayList<Image> purpleImageIdle = new ArrayList<>();
        purpleImageIdle.add(purpleFrame);
        purpleBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, purpleImageIdle);
        boxes.add(purpleBox);

        Sprite aquaBox = new Sprite();
        purpleBox.setLayer(52);
        Image aquaFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/Aqua_1x.png").toUri().toString());
        ArrayList<Image> aquaImageIdle = new ArrayList<>();
        aquaImageIdle.add(aquaFrame);
        aquaBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, aquaImageIdle);
        boxes.add(aquaBox);

        Sprite orangeSquareBox = new Sprite();
        orangeSquareBox.setLayer(53);
        Image orangeSquareFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/OrangeSquare_1x.png").toUri().toString());
        ArrayList<Image> orangeSquareImageIdle = new ArrayList<>();
        orangeSquareImageIdle.add(orangeSquareFrame);
        orangeSquareBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, orangeSquareImageIdle);
        boxes.add(orangeSquareBox);

        Sprite blueSquareBox = new Sprite();
        blueSquareBox.setLayer(53);
        Image blueSquareFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/BlueSquare_1x.png").toUri().toString());
        ArrayList<Image> blueSquareImageIdle = new ArrayList<>();
        blueSquareImageIdle.add(blueSquareFrame);
        blueSquareBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, blueSquareImageIdle);
        boxes.add(blueSquareBox);

        Sprite greenSquareBox = new Sprite();
        greenSquareBox.setLayer(52);
        Image greenSquareFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/GreenSquare_1x.png").toUri().toString());
        ArrayList<Image> greenSquareImageIdle = new ArrayList<>();
        greenSquareImageIdle.add(greenSquareFrame);
        greenSquareBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, greenSquareImageIdle);
        boxes.add(greenSquareBox);

        Sprite bluePortraitBox = new Sprite();
        bluePortraitBox.setLayer(53);
        Image bluePortraitFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/BluePortrait_1x.png").toUri().toString());
        ArrayList<Image> bluePortraitImageIdle = new ArrayList<>();
        bluePortraitImageIdle.add(bluePortraitFrame);
        bluePortraitBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, bluePortraitImageIdle);
        boxes.add(bluePortraitBox);

        Sprite orangePortraitBox = new Sprite();
        orangePortraitBox.setLayer(53);
        Image orangePortraitFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/OrangePortrait_1x.png").toUri().toString());
        ArrayList<Image> orangePortraitImageIdle = new ArrayList<>();
        orangePortraitImageIdle.add(orangePortraitFrame);
        orangePortraitBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, orangePortraitImageIdle);
        boxes.add(orangePortraitBox);

        Sprite blueLandscapeBox = new Sprite();
        blueLandscapeBox.setLayer(53);
        Image blueLandscapeFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/BlueLandscape_1x.png").toUri().toString());
        ArrayList<Image> blueLandscapeImageIdle = new ArrayList<>();
        blueLandscapeImageIdle.add(blueLandscapeFrame);
        blueLandscapeBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, blueLandscapeImageIdle);
        boxes.add(blueLandscapeBox);

        interactPopup.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new DisableBehavior()));
        CompositePuzzlePopupBehavior cppb =  new CompositePuzzlePopupBehavior(boxes, messageFromHQ);
        interactPopup.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.E), cppb));
        interactPopup.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.X), cppb));
        //Set the observer to be idle
        interactPopup.getAnimation().setState(AnimationState.IDLE);
        return interactPopup;
	}

	public static Sprite compositeMessageFromHQ()
	{
		Sprite computer = new Sprite();
		computer.setSpriteClassId(SpriteClassIdConstants.INTERACTABLE);
        computer.setX(-1000);
//        computer.setY(Constants.FLOOR_Y+80);
        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/message_from_hq_1x.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> computerImageIdle = new ArrayList<>();
        computerImageIdle.add(idleFrame);

        //Add them to animation object
        computer.getAnimation().setAnimationLoopForState(AnimationState.IDLE, computerImageIdle);

        //Set the observer to be idle
        computer.getAnimation().setState(AnimationState.IDLE);
        return computer;
    }


	public static Sprite observablePlayer()
	{
		Sprite player = player();
		player.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new ObservableBehavior()));
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

        //Setting up animation

        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/enemies/observer/Observer_EyeClosed.png").toUri().toString());
        Image activeFrame = new Image(Paths.get("src/main/resources/assets/enemies/observer/Observer_EyeOpen_BluePupil.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> observerImageIdle = new ArrayList<>();
        ArrayList<Image> observerImageActive = new ArrayList<>();
        observerImageIdle.add(idleFrame);
        observerImageActive.add(activeFrame);

        //Add them to animation object
        observer.getAnimation().setAnimationLoopForState(AnimationState.IDLE, observerImageIdle);
        observer.getAnimation().setAnimationLoopForState(AnimationState.RIGHT_MOVEMENT, observerImageActive);
        observer.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, observerImageActive);

        //Set the observer to be idle
        observer.getAnimation().setState(AnimationState.IDLE);

		Sprite bulletSprite = enemyBullet();

		ObserverBehavior observerBehavior = new ObserverBehavior(250, 5);
		observerBehavior.setShootSpriteBehavior(new ShootAtPlayerBehavior(0, (int)(observer.getHeight() + 20), bulletSprite, 20));

		observer.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), observerBehavior));
		observer.addCustomCollision(SpriteClassIdConstants.BULLET, new DisableBehavior());
		observer.setDefaultCollisionBehavior(new DoNothingBehavior());

		return observer;
	}

	public static Sprite observer(double x, double y)
	{
        Sprite observer = new Sprite();
        observer.setSpriteClassId(SpriteClassIdConstants.ENEMY);
        observer.setWidth(50);
        observer.setHeight(50);
        observer.setVelocityX(5);
        observer.setX(x);
        observer.setY(y);
        observer.setColor(Color.GOLD);

        //Setting up animation

        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/enemies/observer/Observer_EyeClosed.png").toUri().toString());
        Image activeFrame = new Image(Paths.get("src/main/resources/assets/enemies/observer/Observer_EyeOpen_BluePupil.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> observerImageIdle = new ArrayList<>();
        ArrayList<Image> observerImageActive = new ArrayList<>();
        observerImageIdle.add(idleFrame);
        observerImageActive.add(activeFrame);

        //Add them to animation object
        observer.getAnimation().setAnimationLoopForState(AnimationState.IDLE, observerImageIdle);
        observer.getAnimation().setAnimationLoopForState(AnimationState.RIGHT_MOVEMENT, observerImageActive);
        observer.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, observerImageActive);

        //Set the observer to be idle
        observer.getAnimation().setState(AnimationState.IDLE);
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
		ArrayList<ObserverBehaviorI> observerBehaviors = new ArrayList<>();
		for (Sprite s : observers)
		{
			observerBehaviors.add((ObserverBehaviorI)s.getObserverBehavior());
		}
		button.addCustomCollision(SpriteClassIdConstants.PLAYER, new RegisterObserversBehavior(ob, observerBehaviors));
		return button;
	}

	public static Sprite registerObserverButton(Sprite observable, ArrayList<Sprite> observers, double x, double y, double width, double height)
	{
		Sprite button = new Sprite();
        button.setX(x);
        button.setY(Constants.FLOOR_Y - height);
        button.setWidth(width);
        button.setHeight(height);
		ObservableBehavior ob = (ObservableBehavior)observable.getObservableBehavior();
		ArrayList<ObserverBehaviorI> observerBehaviors = new ArrayList<>();
		for (Sprite s : observers)
		{
			observerBehaviors.add((ObserverBehaviorI)s.getObserverBehavior());
		}
		button.addCustomCollision(SpriteClassIdConstants.PLAYER, new RegisterObserversBehavior(ob, observerBehaviors));
		return button;
	}

	public static Sprite unregisterObserverButton(Sprite observable)
	{
		Sprite button = new Sprite();
        button.setColor(Color.RED);
		ObservableBehavior ob = (ObservableBehavior) observable.getObservableBehavior();
		button.addCustomCollision(SpriteClassIdConstants.PLAYER, new UnregisterObserverBehavior(ob));
		return button;
	}

	public static Sprite unregisterObserverButton(Sprite observable, double x, double y, double width, double height)
	{
		Sprite button = new Sprite();
        button.setX(x);
        button.setY(Constants.FLOOR_Y - height);
        button.setWidth(width);
        button.setHeight(height);
        button.setColor(Color.BLUE);
		ObservableBehavior ob = (ObservableBehavior) observable.getObservableBehavior();
		button.addCustomCollision(SpriteClassIdConstants.PLAYER, new UnregisterObserverBehavior(ob));
		return button;
	}

	public static Sprite spritePool() {
		Sprite pool = new Sprite();
        pool.setSpriteClassId(SpriteClassIdConstants.KILLZONE);
        pool.setWidth(200);
        pool.setHeight(100);
        pool.setVelocityX(5);
        pool.setLayer(1);
        pool.setX(Constants.SPRITE_POOL_X);
        pool.setY(Constants.FLOOR_Y);
        pool.setColor(Color.AQUA);
//        SingletonPoolBehavior spb = new SingletonPoolBehavior();
//        spb.setSpritePool(singletonEnemies);
//        pool.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), spb));
		return pool;
	}

	public static ArrayList<Sprite> singletonEnemies() {
		Sprite singletonEnemy = new Sprite();
		singletonEnemy.setSpriteClassId(SpriteClassIdConstants.ENEMY);
		singletonEnemy.setWidth(50);
		singletonEnemy.setHeight(100);
		singletonEnemy.setHealth(1);
		singletonEnemy.setVelocityX(5);
		singletonEnemy.setX(1000);
		singletonEnemy.setY(Constants.FLOOR_Y-100);
		singletonEnemy.setColor(Color.BROWN);
		singletonEnemy.setEnabled(true);
		singletonEnemy.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
		singletonEnemy.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
        singletonEnemy.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
        singletonEnemy.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));
//        Sprite bulletSprite = enemyBullet();

//        SingletonPoolBehavior spb = new SingletonPoolBehavior();
//        spb.setSpritePool(singletonEnemies);
//        singletonEnemy.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), spb));
//		ObserverBehavior observerBehavior = new ObserverBehavior(targetSprite, 250, 5);
//		observerBehavior.setShootSpriteBehavior(new ShootAtPlayerBehavior(0, (int)(singletonEnemy.getHeight() + 20), bulletSprite, 20));
//		singletonEnemy.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), observerBehavior));
//		singletonEnemy.setDefaultCollisionBehavior(new DoNothingBehavior());

        Sprite enemy1 = singletonEnemy.copy();
		enemy1.setX(1025);
		SingletonPoolBehavior spb = new SingletonPoolBehavior();
		spb.setSpawnIndex(0);
		enemy1.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), spb));
		Sprite enemy2 = singletonEnemy.copy();
		enemy2.setX(1125);
		spb = new SingletonPoolBehavior();
		spb.setSpawnIndex(1);
		enemy2.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), spb));

		ArrayList<Sprite> enemyList = new ArrayList<Sprite>();
		enemyList.add(enemy1);
		enemyList.add(enemy2);

		return enemyList;
	}

	 public static Sprite observerFlashScreen() {
	    	Sprite screen = new Sprite();
	    	screen.setX(-400);
	    	screen.setY(-200);
	    	screen.setWidth(500);
	    	screen.setHeight(500);

	    	ArrayList<Image> observerImage;
	        if(screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT)==null)
	        {
	        	observerImage = new ArrayList<Image>();
	        }
	        else
	        {
	        	observerImage = screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT);
	        }

	        ArrayList<String> observerPaths = new ArrayList<String>();
	        observerPaths.add("src/main/resources/group1/view/ObserverFlashScreen/ObserverFlashScreen1.png");
	        observerPaths.add("src/main/resources/group1/view/ObserverFlashScreen/ObserverFlashScreen2.png");
	        observerPaths.add("src/main/resources/group1/view/ObserverFlashScreen/ObserverFlashScreen3.png");
	        observerPaths.add("src/main/resources/group1/view/ObserverFlashScreen/ObserverFlashScreen4.png");
	        observerPaths.add("src/main/resources/group1/view/ObserverFlashScreen/ObserverFlashScreen5.png");
	        for(String observerPath: observerPaths)
	        {
	        	observerImage.add(new Image(Paths.get(observerPath).toUri().toString()));
	        }

	        screen.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, observerImage);
	        screen.getAnimation().setState(AnimationState.LEFT_MOVEMENT);

	        ScreenBehavior screenBehavior = new ScreenBehavior();
	        screen.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), screenBehavior));
	        return screen;

	    }

	 public static Sprite strategyFlashScreen() {
	    	Sprite screen = new Sprite();
	    	screen.setX(-400);
	    	screen.setY(-200);
	    	screen.setWidth(500);
	    	screen.setHeight(500);

	    	ArrayList<Image> observerImage;
	        if(screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT)==null)
	        {
	        	observerImage = new ArrayList<Image>();
	        }
	        else
	        {
	        	observerImage = screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT);
	        }

	        ArrayList<String> observerPaths = new ArrayList<String>();
	        observerPaths.add("src/main/resources/group1/view/StrategyFlashScreen/StrategyFlashScreen1.png");
	        observerPaths.add("src/main/resources/group1/view/StrategyFlashScreen/StrategyFlashScreen2.png");
	        observerPaths.add("src/main/resources/group1/view/StrategyFlashScreen/StrategyFlashScreen3.png");
	        observerPaths.add("src/main/resources/group1/view/StrategyFlashScreen/StrategyFlashScreen4.png");
	        observerPaths.add("src/main/resources/group1/view/StrategyFlashScreen/StrategyFlashScreen5.png");
	        for(String observerPath: observerPaths)
	        {
	        	observerImage.add(new Image(Paths.get(observerPath).toUri().toString()));
	        }

	        screen.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, observerImage);
	        screen.getAnimation().setState(AnimationState.LEFT_MOVEMENT);

	        ScreenBehavior screenBehavior = new ScreenBehavior();
	        screen.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), screenBehavior));
	        return screen;

	    }

	 public static Sprite commanderFlashScreen() {
	    	Sprite screen = new Sprite();
	    	screen.setX(-400);
	    	screen.setY(-200);
	    	screen.setWidth(500);
	    	screen.setHeight(500);

	    	ArrayList<Image> observerImage;
	        if(screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT)==null)
	        {
	        	observerImage = new ArrayList<Image>();
	        }
	        else
	        {
	        	observerImage = screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT);
	        }

	        ArrayList<String> observerPaths = new ArrayList<String>();
	        observerPaths.add("src/main/resources/group1/view/CommanderFlashScreen/CommanderFlashScreen1.png");
	        observerPaths.add("src/main/resources/group1/view/CommanderFlashScreen/CommanderFlashScreen2.png");
	        observerPaths.add("src/main/resources/group1/view/CommanderFlashScreen/CommanderFlashScreen3.png");
	        observerPaths.add("src/main/resources/group1/view/CommanderFlashScreen/CommanderFlashScreen4.png");
	        observerPaths.add("src/main/resources/group1/view/CommanderFlashScreen/CommanderFlashScreen5.png");
	        for(String observerPath: observerPaths)
	        {
	        	observerImage.add(new Image(Paths.get(observerPath).toUri().toString()));
	        }

	        screen.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, observerImage);
	        screen.getAnimation().setState(AnimationState.LEFT_MOVEMENT);

	        ScreenBehavior screenBehavior = new ScreenBehavior();
	        screen.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), screenBehavior));
	        return screen;

	    }

	 public static Sprite compositeFlashScreen() {
	    	Sprite screen = new Sprite();
	    	screen.setX(-400);
	    	screen.setY(-200);
	    	screen.setWidth(500);
	    	screen.setHeight(500);

	    	ArrayList<Image> observerImage;
	        if(screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT)==null)
	        {
	        	observerImage = new ArrayList<Image>();
	        }
	        else
	        {
	        	observerImage = screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT);
	        }

	        ArrayList<String> observerPaths = new ArrayList<String>();
	        observerPaths.add("src/main/resources/group1/view/CompositeFlashScreen/CompositeFlashScreen1.png");
	        observerPaths.add("src/main/resources/group1/view/CompositeFlashScreen/CompositeFlashScreen2.png");
	        observerPaths.add("src/main/resources/group1/view/CompositeFlashScreen/CompositeFlashScreen3.png");
	        observerPaths.add("src/main/resources/group1/view/CompositeFlashScreen/CompositeFlashScreen4.png");
	        observerPaths.add("src/main/resources/group1/view/CompositeFlashScreen/CompositeFlashScreen5.png");
	        for(String observerPath: observerPaths)
	        {
	        	observerImage.add(new Image(Paths.get(observerPath).toUri().toString()));
	        }

	        screen.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, observerImage);
	        screen.getAnimation().setState(AnimationState.LEFT_MOVEMENT);

	        ScreenBehavior screenBehavior = new ScreenBehavior();
	        screen.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), screenBehavior));
	        return screen;

	    }

	 public static Sprite factoryFlashScreen() {
	    	Sprite screen = new Sprite();
	    	screen.setX(-400);
	    	screen.setY(-200);
	    	screen.setWidth(500);
	    	screen.setHeight(500);

	    	ArrayList<Image> observerImage;
	        if(screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT)==null)
	        {
	        	observerImage = new ArrayList<Image>();
	        }
	        else
	        {
	        	observerImage = screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT);
	        }

	        ArrayList<String> observerPaths = new ArrayList<String>();
	        observerPaths.add("src/main/resources/group1/view/FactoryFlashScreen/FactoryFlashScreen1.png");
	        observerPaths.add("src/main/resources/group1/view/FactoryFlashScreen/FactoryFlashScreen2.png");
	        observerPaths.add("src/main/resources/group1/view/FactoryFlashScreen/FactoryFlashScreen3.png");
	        observerPaths.add("src/main/resources/group1/view/FactoryFlashScreen/FactoryFlashScreen4.png");
	        observerPaths.add("src/main/resources/group1/view/FactoryFlashScreen/FactoryFlashScreen5.png");
	        for(String observerPath: observerPaths)
	        {
	        	observerImage.add(new Image(Paths.get(observerPath).toUri().toString()));
	        }

	        screen.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, observerImage);
	        screen.getAnimation().setState(AnimationState.LEFT_MOVEMENT);

	        ScreenBehavior screenBehavior = new ScreenBehavior();
	        screen.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), screenBehavior));
	        return screen;

	    }

	 public static Sprite MVCFlashScreen() {
	    	Sprite screen = new Sprite();
	    	screen.setX(-400);
	    	screen.setY(-200);
	    	screen.setWidth(500);
	    	screen.setHeight(500);

	    	ArrayList<Image> observerImage;
	        if(screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT)==null)
	        {
	        	observerImage = new ArrayList<Image>();
	        }
	        else
	        {
	        	observerImage = screen.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT);
	        }

	        ArrayList<String> observerPaths = new ArrayList<String>();
	        observerPaths.add("src/main/resources/group1/view/MVCFlashScreen/MVCFlashScreen1.png");
	        observerPaths.add("src/main/resources/group1/view/MVCFlashScreen/MVCFlashScreen2.png");
	        observerPaths.add("src/main/resources/group1/view/MVCFlashScreen/MVCFlashScreen3.png");
	        observerPaths.add("src/main/resources/group1/view/MVCFlashScreen/MVCFlashScreen4.png");
	        observerPaths.add("src/main/resources/group1/view/MVCFlashScreen/MVCFlashScreen5.png");
	        for(String observerPath: observerPaths)
	        {
	        	observerImage.add(new Image(Paths.get(observerPath).toUri().toString()));
	        }

	        screen.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, observerImage);
	        screen.getAnimation().setState(AnimationState.LEFT_MOVEMENT);

	        ScreenBehavior screenBehavior = new ScreenBehavior();
	        screen.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), screenBehavior));
	        return screen;

	    }

	public static Sprite interactTrigger(Sprite popup) {
		Sprite button = new Sprite();
        button.setX(-20);
        button.setY(Constants.FLOOR_Y - 100);
        button.setWidth(200);
        button.setHeight(100);
        button.setColor(Color.TRANSPARENT);
		button.addCustomCollision(SpriteClassIdConstants.PLAYER, new DisplayPopupWhileInteract(popup));
		return button;
	}

	public static Sprite compositePuzzleScreen()
	{
		Sprite puzzlePopup = new Sprite();
		puzzlePopup.setSpriteClassId(SpriteClassIdConstants.INTERACTABLE);
//        puzzlePopup.setWidth(160);
//        puzzlePopup.setHeight(160);
        puzzlePopup.setX(-200);
        puzzlePopup.setLayer(50);
        puzzlePopup.setY(Constants.FLOOR_Y-240);
        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/message_from_hq_1x.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> popupImageIdle = new ArrayList<>();
        popupImageIdle.add(idleFrame);

        //Add them to animation object
        puzzlePopup.getAnimation().setAnimationLoopForState(AnimationState.IDLE, popupImageIdle);
        puzzlePopup.disable();

        puzzlePopup.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.X), new DisableBehavior()));
        //Set the observer to be idle
        puzzlePopup.getAnimation().setState(AnimationState.IDLE);
        return puzzlePopup;
	}

	//TestPlayer has no gravity behavior or animations
	public static Sprite testPlayer() 
	{
        Sprite playerSprite = new Sprite();
        playerSprite.setX(Constants.WINDOW_WIDTH/2 -25);
        //playerSprite.setY(Constants.WINDOW_HEIGHT - 200);
        playerSprite.setY(Constants.PLAYER_Y);
        playerSprite.setVelocityY(-0.1);
        playerSprite.setWidth(50);
        playerSprite.setHeight(Constants.PLAYER_HEIGHT);
        playerSprite.setSpriteClassId(SpriteClassIdConstants.PLAYER);
        playerSprite.setDirection(Constants.LEFT);
        playerSprite.setHealth(10);
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.A, -1*Constants.PLAYER_DX)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.D, Constants.PLAYER_DX)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.A), new UpdateVelocityXBehavior(0)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.D), new UpdateVelocityXBehavior(0)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new PlayerMoveBehavior()));
        //playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new PlayerGravityBehavior(Constants.GRAVITY)));
        //Order is starting to matter for this process - JumpBehavior must come AFTER GravityBehavior
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new JumpBehavior(KeyCode.W, -12)));
        //Likewise, MoveBehavior must come AFTER all behaviors that affect velocity
        playerSprite.setColor(Color.BLUE);

        Sprite bulletSprite = bullet();

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.SPACE), new ShootSpriteBehavior((int)(playerSprite.getWidth()+30), (int)(playerSprite.getHeight() * 0.5), bulletSprite)));

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new ReloadLevelBehavior()));
        playerSprite.addCustomCollision(SpriteClassIdConstants.ENEMY_BULLET, new DecrementHealthBehavior());

        return playerSprite;
	}

	public static Sprite observerPlatformRight(int width, int height, int x, int y, int maxX, double xVelocity) 
	{
		Sprite observerPlatform = new Sprite();
        observerPlatform.setX(x);
        observerPlatform.setY(y);
        observerPlatform.setWidth(width);
        observerPlatform.setHeight(height);
        observerPlatform.setDefaultCollisionBehavior(new DoNothingBehavior());
        observerPlatform.setSpriteClassId(SpriteClassIdConstants.FLOOR);
        observerPlatform.setColor(Color.GREY);

        HorizontalObserverPlatformBehavior opb = new HorizontalObserverPlatformBehavior(maxX, xVelocity);
        observerPlatform.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), opb));

		return observerPlatform;
	}
}
