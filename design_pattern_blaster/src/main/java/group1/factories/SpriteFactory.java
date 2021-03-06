package group1.factories;
import group1.App;
import group1.model.sprite.behavior.*;
import group1.model.collision.HitBox;
import group1.model.sprite.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

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
        playerSprite.setDirection(Constants.RIGHT);
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

        Sprite bulletSprite = bullet(Color.ORANGE);

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.SPACE), new ShootSpriteBehavior((int)(playerSprite.getWidth()+30), (int)(playerSprite.getHeight() * 0.5), bulletSprite, bulletSprite.getColor())));

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new ReloadLevelBehavior()));
        playerSprite.addCustomCollision(SpriteClassIdConstants.ENEMY_BULLET, new DecrementHealthBehavior());

        return playerSprite;
    }

    public static Sprite bullet(Color color)
    {
        Sprite bulletSprite = new Sprite();
        bulletSprite.setX(100);
        bulletSprite.setY(80);
        bulletSprite.setWidth(24);
        bulletSprite.setHeight(24);
        bulletSprite.setVelocityX(40);
//        bulletSprite.setDirection(Constants.LEFT);
        bulletSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));
        bulletSprite.setColor(color);
        bulletSprite.setDefaultCollisionBehavior(new DisableBehavior());
        bulletSprite.addCustomCollision(SpriteClassIdConstants.SUBORDINATE, new DoNothingBehavior());
        bulletSprite.addCustomCollision(SpriteClassIdConstants.INVOKER, new DisableBehavior());
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
        bulletSprite.addCustomCollision(SpriteClassIdConstants.INTERACTABLE, new DoNothingBehavior());
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
        viewSprite.setY(viewSprite.getY()+5);
        viewSprite.setColor(Color.GRAY);
        viewSprite.setDefaultCollisionBehavior(new DoNothingBehavior());
        viewSprite.setSpriteClassId(-9); //placeholder
        viewSprite.setDirection(Constants.LEFT);
        Image avatar = new Image(Paths.get("src/main/resources/assets/MVC/MVCRequestSender 0.png").toUri().toString());
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
                        (int) (playerSprite.getHeight() * 0.78), bullet(Color.ORANGE), Color.ORANGE)));

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
        ArrayList<Image> asset = new ArrayList<>(Arrays.asList(new Image(Paths.get("src/main/resources/assets/scenery/DefaultPlatform.png").toUri().toString())));
        floor.getAnimation().setAnimationLoopForState(AnimationState.IDLE, asset);
        return floor;
    }
    public static Sprite floorPlatform(int width, int height, int x, int y)
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
        commander.setX(300);
        commander.setY(Constants.WINDOW_HEIGHT - 150);
        commander.setColor(Color.RED);

        Sprite commandBullet = commandBullet();
        commandBullet.addCustomCollision(SpriteClassIdConstants.PLAYER, new DoNothingBehavior());

        CommanderBehavior commanderBehavior = new CommanderBehavior();
        commanderBehavior.setShootSpriteBehavior(new ShootDiagonallyAtTargetBehavior2((int) (commander.getWidth() + 10), (int) (commander.getHeight() * 0.78), commandBullet, 10, subordinate));
        commander.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), commanderBehavior));

      //Setting up animation

        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/enemies/commander/Commander3.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> commanderImageIdle = new ArrayList<>();
        commanderImageIdle.add(idleFrame);

        //Add them to animation object
        commander.getAnimation().setAnimationLoopForState(AnimationState.IDLE, commanderImageIdle);

        //Set the observer to be idle
        commander.getAnimation().setState(AnimationState.IDLE);
        return commander;
    }

	public static Sprite commander2(Sprite subordinate)
    {
		Sprite commander = new Sprite();
		commander.setWidth(50);
		commander.setHeight(50);
		commander.setLayer(Constants.BACKGROUND);
        commander.setX(550);
        commander.setY(Constants.WINDOW_HEIGHT - 470);
        commander.setColor(Color.RED);

        Sprite commandBullet = commandBullet();
        commandBullet.addCustomCollision(SpriteClassIdConstants.PLAYER, new DoNothingBehavior());

        CommanderBehavior2 commanderBehavior = new CommanderBehavior2();
        commanderBehavior.setShootSpriteBehavior(new ShootDiagonallyAtTargetBehavior2((int) (commander.getWidth() +10), (int) (commander.getHeight() * 0.78), commandBullet, 10, subordinate));
        commander.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), commanderBehavior));

      //Setting up animation

        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/enemies/commander/Commander3.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> commanderImageIdle = new ArrayList<>();
        commanderImageIdle.add(idleFrame);

        //Add them to animation object
        commander.getAnimation().setAnimationLoopForState(AnimationState.IDLE, commanderImageIdle);

        //Set the observer to be idle
        commander.getAnimation().setState(AnimationState.IDLE);
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
        bulletSprite.addCustomCollision(0, null);
        bulletSprite.setSpriteClassId(SpriteClassIdConstants.COMMAND);

        bulletSprite.addCustomCollision(SpriteClassIdConstants.PLAYER, new DoNothingBehavior());
        bulletSprite.addCustomCollision(SpriteClassIdConstants.INVOKER, new DisableBehavior());
        bulletSprite.setDefaultCollisionBehavior(new DisableBehavior());
        return bulletSprite;
    }

	public static Sprite invoker()
	{
		Sprite subordinate = new Sprite();
		subordinate.setWidth(40);
		subordinate.setHeight(300);
		subordinate.setX(1400);
		subordinate.setY(Constants.WINDOW_HEIGHT - 400);
//		subordinate.setColor(Color.TRANSPARENT);
		subordinate.setColor(Color.ORANGE);
		subordinate.setSpriteClassId(SpriteClassIdConstants.INVOKER);
		subordinate.setHealth(25);

		Sprite bulletSprite = enemyBullet();
		bulletSprite.setSpriteClassId(SpriteClassIdConstants.COMMAND);
		bulletSprite.setHeight(30);
		bulletSprite.setWidth(30);
		bulletSprite.setVelocityY(0);
		bulletSprite.setVelocityX(10);
		bulletSprite.setColor(Color.GREEN);
		bulletSprite.addCustomCollision(SpriteClassIdConstants.PLAYER, new DoNothingBehavior());
		bulletSprite.addCustomCollision(SpriteClassIdConstants.BULLET, new DisableBehavior());
		bulletSprite.addCustomCollision(SpriteClassIdConstants.COMMAND, new DoNothingBehavior());

		subordinate.addCustomCollision(SpriteClassIdConstants.COMMAND, new ShootSpriteBehavior2((int)(20),(int)(subordinate.getHeight() - 70), bulletSprite));
		subordinate.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
		subordinate.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new FlashColorWhenDamagedBehavior(Color.RED)));
		subordinate.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
        subordinate.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		return subordinate;
	}

	public static Sprite invoker2()
	{
		Sprite subordinate = new Sprite();
		subordinate.setWidth(40);
		subordinate.setHeight(300);
		subordinate.setX(3600);
		subordinate.setY(Constants.WINDOW_HEIGHT - 400);
//		subordinate.setColor(Color.TRANSPARENT);
		subordinate.setColor(Color.ORANGE);
		subordinate.setSpriteClassId(SpriteClassIdConstants.INVOKER2);
		subordinate.setHealth(25);

		Sprite bulletSprite = enemyBullet();
		bulletSprite.setSpriteClassId(SpriteClassIdConstants.COMMAND);
		bulletSprite.setHeight(30);
		bulletSprite.setWidth(30);
		bulletSprite.setVelocityY(0);
		bulletSprite.setVelocityX(10);
		bulletSprite.setColor(Color.GREEN);
		bulletSprite.addCustomCollision(SpriteClassIdConstants.PLAYER, new DoNothingBehavior());
		bulletSprite.addCustomCollision(SpriteClassIdConstants.BULLET, new DisableBehavior());
		bulletSprite.addCustomCollision(SpriteClassIdConstants.COMMAND, new DoNothingBehavior());

		subordinate.addCustomCollision(SpriteClassIdConstants.COMMAND, new ShootSpriteBehavior2((int)(20),(int)(subordinate.getHeight() - 70), bulletSprite));
		subordinate.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
		subordinate.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new FlashColorWhenDamagedBehavior(Color.RED)));
		subordinate.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
        subordinate.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		return subordinate;
	}

	public static Sprite commandWall() {
		Sprite wall = new Sprite();
		wall.setX(2400);
		wall.setY(Constants.WINDOW_HEIGHT - 800);
		wall.setHeight(800);
		wall.setWidth(50);
		wall.setColor(Color.RED);
		return wall;
	}


	public static Sprite subordinate(Sprite player)
	{
		Sprite subordinate = new Sprite();
		subordinate.setWidth(40);
		subordinate.setHeight(50);
		subordinate.setX(500);
		subordinate.setY(Constants.WINDOW_HEIGHT - 200);
		subordinate.setColor(Color.YELLOW);
		subordinate.setSpriteClassId(SpriteClassIdConstants.SUBORDINATE);

		Sprite bulletSprite = enemyBullet();
		bulletSprite.setHeight(100);
		bulletSprite.setWidth(100);
		bulletSprite.setVelocityY(0);
		bulletSprite.setVelocityX(10);
		bulletSprite.setColor(Color.RED);
		bulletSprite.addCustomCollision(SpriteClassIdConstants.BULLET, new DoNothingBehavior());
		bulletSprite.addCustomCollision(SpriteClassIdConstants.COMMAND, new DoNothingBehavior());

		Image idleFrame = new Image(Paths.get("src/main/resources/assets/enemies/commander/Subordinate2.png").toUri().toString());
		ArrayList<Image> subordinateImageIdle = new ArrayList<>();
		subordinateImageIdle.add(idleFrame);
		subordinate.getAnimation().setAnimationLoopForState(AnimationState.IDLE, subordinateImageIdle);
		subordinate.getAnimation().setState(AnimationState.IDLE);


		subordinate.addCustomCollision(SpriteClassIdConstants.COMMAND, new ShootAtTargetBehavior((int)(10),(int)(-20), bulletSprite,player));
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
	public static Sprite factory(int x, int y, HashMap<Integer, Sprite> blueprintFamily)
	{
		Sprite factory = new Sprite();
		factory.setWidth(600);
		factory.setHeight(900);
		factory.setX(x);
		factory.setY(y);
		factory.setHealth(10);
		factory.setColor(Color.PURPLE);
		factory.setLayer(-2);

        ArrayList<Image> factoryAsset = new ArrayList<>(Arrays.asList(new Image(Paths.get("src/main/resources/assets/FactorySmoke.png").toUri().toString())));
        factory.getAnimation().setAnimationLoopForState(AnimationState.IDLE, factoryAsset);

        factory.addEventBehavior(new EventBehavior(GameEvent.InteractEvent(), new AbstractFactoryBehavior(blueprintFamily)));

		return factory;
	}

	public static Sprite stackFactory(int x, int y, Stack<Sprite> spriteStack)
	{
		Sprite factory = new Sprite();
		factory.setWidth(600);
		factory.setHeight(900);
		factory.setX(x);
		factory.setY(y);
		factory.setHealth(10);
		factory.setColor(Color.PURPLE);
		factory.setLayer(-2);

        ArrayList<Image> factoryAsset = new ArrayList<>(Arrays.asList(new Image(Paths.get("src/main/resources/assets/FactorySmoke.png").toUri().toString())));
        factory.getAnimation().setAnimationLoopForState(AnimationState.IDLE, factoryAsset);

        factory.addEventBehavior(new EventBehavior(GameEvent.InteractEvent(), new StackFactoryBehavior(spriteStack)));

		return factory;
	}

	public static Sprite planeEnemies(int width, int height, double x, double y, double velocity, double minLimit, double maxLimit, int bulletClassId) {
		Sprite enemy = new Sprite();
		enemy.setWidth(width);
		enemy.setHeight(height);
		enemy.setVelocityX(velocity);
		enemy.setX(x);
		enemy.setY(y);
		enemy.setHealth(1);
		enemy.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new MoveBetweenLimitsBehavior(minLimit, maxLimit, enemy.getX())));
		enemy.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));

        //Make them disable on HealthDepletedEvent
		enemy.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));
		enemy.addCustomCollision(bulletClassId, new DecrementHealthBehavior(1));
		Image activeFrame = new Image(Paths.get("src/main/resources/assets/enemies/observer/Observer_EyeOpen_BluePupil.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> enemyImageIdle = new ArrayList<>();
        enemyImageIdle.add(activeFrame);

        //Add them to animation object
        enemy.getAnimation().setAnimationLoopForState(AnimationState.IDLE, enemyImageIdle);
        //Set the observer to be idle
        enemy.getAnimation().setState(AnimationState.IDLE);
		return enemy;
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

	public static Sprite newGun(int width, int height, Color color, double x, double y, String imgPath1, String imgPath2) {
		Sprite newGun = new Sprite();
		newGun.setWidth(width);
		newGun.setSpriteClassId(SpriteClassIdConstants.GUN);
		newGun.setHeight(height);
		newGun.setX(x);
		newGun.setY(y);
		newGun.setColor(color);

		//Getting images
        Image image1 = new Image(Paths.get(imgPath1).toUri().toString());
        Image image2 = new Image(Paths.get(imgPath2).toUri().toString());

        ArrayList<Image> imageIdle = new ArrayList<>();
        imageIdle.add(image1);
        imageIdle.add(image2);

      //Add them to animation object
        newGun.getAnimation().setAnimationLoopForState(AnimationState.IDLE, imageIdle);
        newGun.getAnimation().setState(AnimationState.IDLE);

        return newGun;


	}

	public static Sprite door(double width, double height, double x, double y, Color color, String imgPath) {
		Sprite newDoor = new Sprite();
		newDoor.setX(x);
		newDoor.setY(y);
		newDoor.setWidth(width);
		newDoor.setHeight(height);
		newDoor.setColor(color);
		newDoor.setSpriteClassId(SpriteClassIdConstants.DOOR);
		Image image1 = new Image(Paths.get(imgPath).toUri().toString());
		ArrayList<Image> imageIdle = new ArrayList<>();
        imageIdle.add(image1);
        newDoor.getAnimation().setAnimationLoopForState(AnimationState.IDLE, imageIdle);
        newDoor.getAnimation().setState(AnimationState.IDLE);
		return newDoor;
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


	/*
	 * COMPOSITE SPRITES - 891
	 */
	 public static CompositeSprite compositeEnemy() {
		CompositeSprite compositeEnemyBlueprint = new CompositeSprite();
		compositeEnemyBlueprint.setSpriteClassId(SpriteClassIdConstants.ENEMY);
		compositeEnemyBlueprint.setWidth(640);
		compositeEnemyBlueprint.setHeight(80);
		compositeEnemyBlueprint.setVelocityX(-5);
		compositeEnemyBlueprint.setX(1140);
		compositeEnemyBlueprint.setY(80);
		compositeEnemyBlueprint.setHealth(1);
		compositeEnemyBlueprint.setColor(Color.VIOLET);
		int depth = 0;
		int width = 0;
		int baseWidth = 600;
		int baseX = 900;
		int xCenter = 960;
		int baseY = 80;
		int heightDiff = 80;

		compositeEnemyBlueprint.addCustomCollision(SpriteClassIdConstants.BULLET, new DecrementHealthBehavior(1));
		compositeEnemyBlueprint.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
        compositeEnemyBlueprint.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new DisableBehavior()));



		CompositeSprite compositeEnemy = compositeEnemyBlueprint.copy();

		baseWidth = 330;
		baseY +=heightDiff;
//		baseX = xCenter - (depth*depth)*baseWidth/2;
		compositeEnemyBlueprint.setWidth(baseWidth);
		compositeEnemyBlueprint.setY(baseY);
		compositeEnemyBlueprint.setColor(Color.INDIGO);
		CompositeSprite l1l1 = compositeEnemyBlueprint.copy();
		l1l1.setX(1010);
		CompositeSprite l1r1 = compositeEnemyBlueprint.copy();
		l1r1.setX(1010+560);
		compositeEnemy.setLeft(l1l1);
		compositeEnemy.setRight(l1r1);

		baseWidth -=40;
		baseY+=heightDiff;
		compositeEnemyBlueprint.setWidth(155);
		compositeEnemyBlueprint.setY(baseY);
		compositeEnemyBlueprint.setColor(Color.CORNFLOWERBLUE);

		CompositeSprite l2l1 = compositeEnemyBlueprint.copy();
		l2l1.setX(960);
		CompositeSprite l2r1 = compositeEnemyBlueprint.copy();
		l2r1.setX(960+280);
		CompositeSprite l2l2 = compositeEnemyBlueprint.copy();
		l2l2.setX(960+(280*2));
		CompositeSprite l2r2 = compositeEnemyBlueprint.copy();
		l2r2.setX(960+(280*3));
		l1l1.setLeft(l2l1);
		l1l1.setRight(l2r1);
		l1r1.setLeft(l2l2);
		l1r1.setRight(l2r2);

		baseWidth -=40;
		baseY+=heightDiff;
		compositeEnemyBlueprint.setWidth(95);
		compositeEnemyBlueprint.setY(baseY);
		compositeEnemyBlueprint.setColor(Color.FORESTGREEN);
		CompositeSprite l3l1 = compositeEnemyBlueprint.copy();
		l3l1.setX(920);
		CompositeSprite l3r1 = compositeEnemyBlueprint.copy();
		l3r1.setX(1060);
		CompositeSprite l3l2 = compositeEnemyBlueprint.copy();
		l3l2.setX(1200);
		CompositeSprite l3r2 = compositeEnemyBlueprint.copy();
		l3r2.setX(1340);
		CompositeSprite l3l3 = compositeEnemyBlueprint.copy();
		l3l3.setX(1480);
		CompositeSprite l3r3 = compositeEnemyBlueprint.copy();
		l3r3.setX(1620);
		CompositeSprite l3l4 = compositeEnemyBlueprint.copy();
		l3l4.setX(1760);
		CompositeSprite l3r4 = compositeEnemyBlueprint.copy();
		l3r4.setX(1900);
		l2l1.setLeft(l3l1);
		l2l1.setRight(l3r1);
		l2r1.setLeft(l3l2);
		l2r1.setRight(l3r2);
		l2l2.setLeft(l3l3);
		l2l2.setRight(l3r3);
		l2r2.setLeft(l3l4);
		l2r2.setRight(l3r4);

		baseWidth -=40; //80
		baseY+=heightDiff;
		compositeEnemyBlueprint.setWidth(55);
		compositeEnemyBlueprint.setY(baseY);
		compositeEnemyBlueprint.setColor(Color.ORANGE);
		CompositeSprite l4l1 = compositeEnemyBlueprint.copy();
		l4l1.setX(905);
		CompositeSprite l4r1 = compositeEnemyBlueprint.copy();
		l4r1.setX(975);
		CompositeSprite l4l2 = compositeEnemyBlueprint.copy();
		l4l2.setX(1045);
		CompositeSprite l4r2 = compositeEnemyBlueprint.copy();
		l4r2.setX(1115);
		CompositeSprite l4l3 = compositeEnemyBlueprint.copy();
		l4l3.setX(1185);
		CompositeSprite l4r3 = compositeEnemyBlueprint.copy();
		l4r3.setX(1255);
		CompositeSprite l4l4 = compositeEnemyBlueprint.copy();
		l4l4.setX(1325);
		CompositeSprite l4r4 = compositeEnemyBlueprint.copy();
		l4r4.setX(1395);
		CompositeSprite l4l5 = compositeEnemyBlueprint.copy();
		l4l5.setX(1465);
		CompositeSprite l4r5 = compositeEnemyBlueprint.copy();
		l4r5.setX(1535);
		CompositeSprite l4l6 = compositeEnemyBlueprint.copy();
		l4l6.setX(1605);
		CompositeSprite l4r6 = compositeEnemyBlueprint.copy();
		l4r6.setX(1675);
		CompositeSprite l4l7 = compositeEnemyBlueprint.copy();
		l4l7.setX(1745);
		CompositeSprite l4r7 = compositeEnemyBlueprint.copy();
		l4r7.setX(1815);
		CompositeSprite l4l8 = compositeEnemyBlueprint.copy();
		l4l8.setX(1885);
		CompositeSprite l4r8 = compositeEnemyBlueprint.copy();
		l4r8.setX(1955);

		l3l1.setLeft(l4l1);
		l3l1.setRight(l4r1);
		l3r1.setLeft(l4l2);
		l3r1.setRight(l4r2);
		l3l2.setLeft(l4l3);
		l3l2.setRight(l4r3);
		l3r2.setLeft(l4l4);
		l3r2.setRight(l4r4);
		l3l3.setLeft(l4l5);
		l3l3.setRight(l4r5);
		l3r3.setLeft(l4l6);
		l3r3.setRight(l4r6);
		l3l4.setLeft(l4l7);
		l3l4.setRight(l4r7);
		l3r4.setLeft(l4l8);
		l3r4.setRight(l4r8);

		baseWidth -=50; //40
		baseY+=heightDiff;
		compositeEnemyBlueprint.setWidth(30);//baseWidth);
		compositeEnemyBlueprint.setY(baseY);
		compositeEnemyBlueprint.setColor(Color.CRIMSON);
		CompositeSprite l5l1 = compositeEnemyBlueprint.copy();
		l5l1.setX(900);
		CompositeSprite l5r1 = compositeEnemyBlueprint.copy();
		l5r1.setX(935);
		CompositeSprite l5l2 = compositeEnemyBlueprint.copy();
		l5l2.setX(970);
		CompositeSprite l5r2 = compositeEnemyBlueprint.copy();
		l5r2.setX(1005);
		CompositeSprite l5l3 = compositeEnemyBlueprint.copy();
		l5l3.setX(1040);
		CompositeSprite l5r3 = compositeEnemyBlueprint.copy();
		l5r3.setX(1075);
		CompositeSprite l5l4 = compositeEnemyBlueprint.copy();
		l5l4.setX(1110);
		CompositeSprite l5r4 = compositeEnemyBlueprint.copy();
		l5r4.setX(1145);
		CompositeSprite l5l5 = compositeEnemyBlueprint.copy();
		l5l5.setX(1180);
		CompositeSprite l5r5 = compositeEnemyBlueprint.copy();
		l5r5.setX(1215);
		CompositeSprite l5l6 = compositeEnemyBlueprint.copy();
		l5l6.setX(1250);
		CompositeSprite l5r6 = compositeEnemyBlueprint.copy();
		l5r6.setX(1285);
		CompositeSprite l5l7 = compositeEnemyBlueprint.copy();
		l5l7.setX(1320);
		CompositeSprite l5r7 = compositeEnemyBlueprint.copy();
		l5r7.setX(1355);
		CompositeSprite l5l8 = compositeEnemyBlueprint.copy();
		l5l8.setX(1390);
		CompositeSprite l5r8 = compositeEnemyBlueprint.copy();
		l5r8.setX(1425);
		CompositeSprite l5l9 = compositeEnemyBlueprint.copy();
		l5l9.setX(1460);
		CompositeSprite l5r9 = compositeEnemyBlueprint.copy();
		l5r9.setX(1495);
		CompositeSprite l5l10 = compositeEnemyBlueprint.copy();
		l5l10.setX(1530);
		CompositeSprite l5r10 = compositeEnemyBlueprint.copy();
		l5r10.setX(1565);
		CompositeSprite l5l11 = compositeEnemyBlueprint.copy();
		l5l11.setX(1600);
		CompositeSprite l5r11 = compositeEnemyBlueprint.copy();
		l5r11.setX(1635);
		CompositeSprite l5l12 = compositeEnemyBlueprint.copy();
		l5l12.setX(1670);
		CompositeSprite l5r12 = compositeEnemyBlueprint.copy();
		l5r12.setX(1705);
		CompositeSprite l5l13 = compositeEnemyBlueprint.copy();
		l5l13.setX(1740);
		CompositeSprite l5r13 = compositeEnemyBlueprint.copy();
		l5r13.setX(1775);
		CompositeSprite l5l14 = compositeEnemyBlueprint.copy();
		l5l14.setX(1810);
		CompositeSprite l5r14 = compositeEnemyBlueprint.copy();
		l5r14.setX(1845);
		CompositeSprite l5l15 = compositeEnemyBlueprint.copy();
		l5l15.setX(1880);
		CompositeSprite l5r15 = compositeEnemyBlueprint.copy();
		l5r15.setX(1915);
		CompositeSprite l5l16 = compositeEnemyBlueprint.copy();
		l5l16.setX(1950);
		CompositeSprite l5r16 = compositeEnemyBlueprint.copy();
		l5r16.setX(1985);
		l4l1.setLeft(l5l1);
		l4l1.setRight(l5r1);
		l4r1.setLeft(l5l2);
		l4r1.setRight(l5r2);
		l4l2.setLeft(l5l3);
		l4l2.setRight(l5r3);
		l4r2.setLeft(l5l4);
		l4r2.setRight(l5r4);
		l4l3.setLeft(l5l5);
		l4l3.setRight(l5r5);
		l4r3.setLeft(l5l6);
		l4r3.setRight(l5r6);
		l4l4.setLeft(l5l7);
		l4l4.setRight(l5r7);
		l4r4.setLeft(l5l8);
		l4r4.setRight(l5r8);
		l4l5.setLeft(l5l9);
		l4l5.setRight(l5r9);
		l4r5.setLeft(l5l10);
		l4r5.setRight(l5r10);
		l4l6.setLeft(l5l11);
		l4l6.setRight(l5r11);
		l4r6.setLeft(l5l12);
		l4r6.setRight(l5r12);
		l4l7.setLeft(l5l13);
		l4l7.setRight(l5r13);
		l4r7.setLeft(l5l14);
		l4r7.setRight(l5r14);
		l4l8.setLeft(l5l15);
		l4l8.setRight(l5r15);
		l4r8.setLeft(l5l16);
		l4r8.setRight(l5r16);

		return compositeEnemy;
	 }
/*
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
	}*/

	public static Sprite computerIcon(Sprite popupPressToInteract, Sprite messageFromHQ, ArrayList<Sprite> puzzleSprites, Sprite puzzleCompletedPopup, Sprite levelend)
	{
		Sprite computerIcon = new Sprite();
		computerIcon.setSpriteClassId(SpriteClassIdConstants.INTERACTABLE);
        computerIcon.setX(50);
        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/composite/0.5x/computer_icon_0.5x.png").toUri().toString());
        computerIcon.setY(Constants.FLOOR_Y-idleFrame.getHeight());
        computerIcon.setWidth(idleFrame.getWidth());
        computerIcon.setHeight(idleFrame.getHeight());
        //Put them in arraylists
        ArrayList<Image> computerImageIdle = new ArrayList<>();
        computerImageIdle.add(idleFrame);

        //Add them to animation object
        computerIcon.getAnimation().setAnimationLoopForState(AnimationState.IDLE, computerImageIdle);

        HitBox hitBox = computerIcon.getHitBox();
        hitBox.setWidth(idleFrame.getWidth()+200);
        hitBox.setHeight(idleFrame.getHeight()+100);
        hitBox.setX(computerIcon.getX()-100);
        hitBox.setY(Constants.FLOOR_Y-idleFrame.getHeight()-100);
        computerIcon.addCustomCollision(-1, new PressKeyToInteractPopupBehavior(popupPressToInteract));
        computerIcon.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.E),new MessageFromHQPopupBehavior(messageFromHQ, puzzleSprites))); //TODO: Change this. This was very last minute. Add a new behavior later.
        //Set the observer to be idle
        computerIcon.getAnimation().setState(AnimationState.IDLE);
        return computerIcon;
    }

	public static Sprite interactPopupE(Sprite attachedSprite)
	{
		Sprite interactPopup = new Sprite();
		interactPopup.setSpriteClassId(SpriteClassIdConstants.INTERACTABLE);
        interactPopup.setWidth(160);
        interactPopup.setHeight(160);
        interactPopup.setX(30);
        interactPopup.setLayer(49);
        interactPopup.setY(Constants.FLOOR_Y-240);
        interactPopup.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.E), new InteractPopupBehavior(attachedSprite)));
        interactPopup.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new DisableBehavior()));
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/composite/0.2x/press_to_interact_popup_0.2x.png").toUri().toString());
        interactPopup.getAnimation().setAnimationLoopForState(AnimationState.IDLE, new ArrayList<>(Arrays.asList(idleFrame)));
        interactPopup.disable();
        return interactPopup;
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


	public static Sprite popupInteractE()
	{
		Sprite interactPopup = new Sprite();
		interactPopup.setSpriteClassId(SpriteClassIdConstants.INTERACTABLE);
        interactPopup.setWidth(160);
        interactPopup.setHeight(160);
        interactPopup.setX(15);
        interactPopup.setY(Constants.FLOOR_Y-200);
        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/composite/0.2x/press_to_interact_popup_0.2x.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> popupImageIdle = new ArrayList<>();
        popupImageIdle.add(idleFrame);

        //Add them to animation object
        interactPopup.getAnimation().setAnimationLoopForState(AnimationState.IDLE, popupImageIdle);
        interactPopup.disable();

        //Set the observer to be idle
        interactPopup.getAnimation().setState(AnimationState.IDLE);
        interactPopup.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new DisableBehavior()));
        return interactPopup;
    }


	public static Sprite puzzleCompletedPopup()
	{
		Sprite puzzleCompletePopup = new Sprite();
		puzzleCompletePopup.setSpriteClassId(SpriteClassIdConstants.INTERACTABLE);
        puzzleCompletePopup.setWidth(160);
        puzzleCompletePopup.setHeight(160);
        puzzleCompletePopup.setX(15);
        puzzleCompletePopup.setLayer(60);
        puzzleCompletePopup.setY(Constants.FLOOR_Y-400);
        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/composite/0.5x/puzzle_completed_popup_0.5x.png").toUri().toString());

        //Put them in arraylists
        ArrayList<Image> popupImageIdle = new ArrayList<>();
        popupImageIdle.add(idleFrame);

        //Add them to animation object
        puzzleCompletePopup.getAnimation().setAnimationLoopForState(AnimationState.IDLE, popupImageIdle);
        puzzleCompletePopup.disable();

        //Set the observer to be idle
        puzzleCompletePopup.getAnimation().setState(AnimationState.IDLE);
        puzzleCompletePopup.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.X), new CloseMessageFromHQBehavior(puzzleCompletePopup)));
        return puzzleCompletePopup;
    }

	public static Sprite messageFromHQ()
	{
		Sprite computer = new Sprite();
		computer.setSpriteClassId(SpriteClassIdConstants.INTERACTABLE);
        computer.setLayer(49);
//        computer.setY(Constants.FLOOR_Y+80);
        //Getting images
        Image idleFrame = new Image(Paths.get("src/main/resources/assets/composite/0.6x/message_from_hq_0.6x.png").toUri().toString());
        computer.setX(-400);
        computer.setY(Constants.FLOOR_Y-idleFrame.getHeight());

        //Put them in arraylists
        ArrayList<Image> computerImageIdle = new ArrayList<>();
        computerImageIdle.add(idleFrame);

        //Add them to animation object
        computer.getAnimation().setAnimationLoopForState(AnimationState.IDLE, computerImageIdle);
        computer.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.X), new CloseMessageFromHQBehavior(computer)));
        computer.disable();
        //Set the observer to be idle
        computer.getAnimation().setState(AnimationState.IDLE);
        return computer;
    }


	public static ArrayList<Sprite> compositePuzzle(Sprite puzzleCompletedPopup, Sprite levelend)
	{
		ArrayList<Sprite> boxes = new ArrayList<Sprite>();

        Sprite purpleBox = new Sprite();
        purpleBox.setLayer(51);
        Image purpleFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/PurpleLandscape_1x.png").toUri().toString());
        ArrayList<Image> purpleImageIdle = new ArrayList<>();
        purpleImageIdle.add(purpleFrame);
        purpleBox.setX(215);
        purpleBox.setY(270);
        purpleBox.setHeight(purpleFrame.getHeight());
        purpleBox.setWidth(purpleFrame.getWidth());
        purpleBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, purpleImageIdle);
        boxes.add(purpleBox);

        Sprite aquaBox = new Sprite();
        aquaBox.setLayer(52);
        Image aquaFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/Aqua_1x.png").toUri().toString());
        ArrayList<Image> aquaImageIdle = new ArrayList<>();
        aquaImageIdle.add(aquaFrame);
        aquaBox.setX(-15);
        aquaBox.setY(300);
        aquaBox.setHeight(aquaFrame.getHeight());
        aquaBox.setWidth(aquaFrame.getWidth());
        aquaBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, aquaImageIdle);
        boxes.add(aquaBox);

        Sprite orangeSquareBox = new Sprite();
        orangeSquareBox.setLayer(53);
        Image orangeSquareFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/OrangeSquare_1x.png").toUri().toString());
        ArrayList<Image> orangeSquareImageIdle = new ArrayList<>();
        orangeSquareImageIdle.add(orangeSquareFrame);
        orangeSquareBox.setX(-80);
        orangeSquareBox.setY(320);
        orangeSquareBox.setHeight(orangeSquareFrame.getHeight());
        orangeSquareBox.setWidth(orangeSquareFrame.getWidth());
        orangeSquareBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, orangeSquareImageIdle);
        boxes.add(orangeSquareBox);

        Sprite blueSquareBox = new Sprite();
        blueSquareBox.setLayer(53);
        Image blueSquareFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/BlueSquare_1x.png").toUri().toString());
        ArrayList<Image> blueSquareImageIdle = new ArrayList<>();
        blueSquareImageIdle.add(blueSquareFrame);
        blueSquareBox.setX(-20);
        blueSquareBox.setY(230);
        blueSquareBox.setHeight(blueSquareFrame.getHeight());
        blueSquareBox.setWidth(blueSquareFrame.getWidth());
        blueSquareBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, blueSquareImageIdle);
        boxes.add(blueSquareBox);

        Sprite greenSquareBox = new Sprite();
        greenSquareBox.setLayer(52);
        Image greenSquareFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/GreenSquare_1x.png").toUri().toString());
        ArrayList<Image> greenSquareImageIdle = new ArrayList<>();
        greenSquareImageIdle.add(greenSquareFrame);
        greenSquareBox.setX(245);
        greenSquareBox.setY(247);
        greenSquareBox.setHeight(greenSquareFrame.getHeight());
        greenSquareBox.setWidth(greenSquareFrame.getWidth());
        greenSquareBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, greenSquareImageIdle);
        boxes.add(greenSquareBox);

        Sprite bluePortraitBox = new Sprite();
        bluePortraitBox.setLayer(53);
        Image bluePortraitFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/BluePortrait_1x.png").toUri().toString());
        ArrayList<Image> bluePortraitImageIdle = new ArrayList<>();
        bluePortraitImageIdle.add(bluePortraitFrame);
        bluePortraitBox.setX(170);
        bluePortraitBox.setY(370);
        bluePortraitBox.setHeight(bluePortraitFrame.getHeight());
        bluePortraitBox.setWidth(bluePortraitFrame.getWidth());
        bluePortraitBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, bluePortraitImageIdle);
        boxes.add(bluePortraitBox);

        Sprite orangePortraitBox = new Sprite();
        orangePortraitBox.setLayer(53);
        Image orangePortraitFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/OrangePortrait_1x.png").toUri().toString());
        ArrayList<Image> orangePortraitImageIdle = new ArrayList<>();
        orangePortraitImageIdle.add(orangePortraitFrame);
        orangePortraitBox.setX(230);
        orangePortraitBox.setY(330);
        orangePortraitBox.setHeight(orangePortraitFrame.getHeight());
        orangePortraitBox.setWidth(orangePortraitFrame.getWidth());
        orangePortraitBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, orangePortraitImageIdle);
        boxes.add(orangePortraitBox);

        Sprite blueLandscapeBox = new Sprite();
        blueLandscapeBox.setLayer(53);
        Image blueLandscapeFrame = new Image(Paths.get("src/main/resources/assets/composite/1x/BlueLandscape_1x.png").toUri().toString());
        ArrayList<Image> blueLandscapeImageIdle = new ArrayList<>();
        blueLandscapeImageIdle.add(blueLandscapeFrame);
        blueLandscapeBox.setX(100);
        blueLandscapeBox.setY(280);
        blueLandscapeBox.setHeight(blueLandscapeFrame.getHeight());
        blueLandscapeBox.setWidth(blueLandscapeFrame.getWidth());
        blueLandscapeBox.getAnimation().setAnimationLoopForState(AnimationState.IDLE, blueLandscapeImageIdle);
        boxes.add(blueLandscapeBox);

        purpleBox.addEventBehavior(new EventBehavior(GameEvent.MouseReleaseEvent(), new CheckPuzzleCompletionBehavior(boxes, puzzleCompletedPopup, levelend)));

        for(Sprite b: boxes) {
        	b.setSpriteClassId(SpriteClassIdConstants.DRAGGABLE);
        	b.addEventBehavior(new EventBehavior(GameEvent.MouseDragEvent(), new MouseDragUpdateBehavior()));
        	b.addEventBehavior(new EventBehavior(GameEvent.MouseReleaseEvent(), new MouseDragDoneBehavior()));
        	b.disable();
            b.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.X), new CloseMessageFromHQBehavior(b)));

        }
        return boxes;
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
//       puzzlePopup.setWidth(160);
//       puzzlePopup.setHeight(160);
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
        observer.setY(300);
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
		bulletSprite.addCustomCollision(SpriteClassIdConstants.BULLET, new DoNothingBehavior());

		StationaryObserverBehavior observerBehavior = new StationaryObserverBehavior();
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
		bulletSprite.addCustomCollision(SpriteClassIdConstants.BULLET, new DoNothingBehavior());

		StationaryObserverBehavior observerBehavior = new StationaryObserverBehavior();
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
        button.setY(y);
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
        button.setY(y);
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

	 public static Sprite singletonFlashScreen() {
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
	        observerPaths.add("src/main/resources/group1/view/SingletonFlashScreen/SingletonFlashScreen1.png");
	        observerPaths.add("src/main/resources/group1/view/SingletonFlashScreen/SingletonFlashScreen2.png");
	        observerPaths.add("src/main/resources/group1/view/SingletonFlashScreen/SingletonFlashScreen3.png");
	        observerPaths.add("src/main/resources/group1/view/SingletonFlashScreen/SingletonFlashScreen4.png");
	        observerPaths.add("src/main/resources/group1/view/SingletonFlashScreen/SingletonFlashScreen5.png");
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

        Sprite bulletSprite = bullet(Color.ORANGE);

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.SPACE), new ShootSpriteBehavior((int)(playerSprite.getWidth()+30), (int)(playerSprite.getHeight() * 0.5), bulletSprite, bulletSprite.getColor())));

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new CheckHealthBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new ReloadLevelBehavior()));
        playerSprite.addCustomCollision(SpriteClassIdConstants.ENEMY_BULLET, new DecrementHealthBehavior());

        return playerSprite;
	}

	public static Sprite observerPlatformHorizontal(int width, int height, int x, int y, int maxX, double xVelocity)
	{
		Sprite observerPlatform = new Sprite();
        observerPlatform.setX(x);
        observerPlatform.setY(y);
        observerPlatform.setWidth(width);
        observerPlatform.setHeight(height);
        observerPlatform.setDefaultCollisionBehavior(new DoNothingBehavior());
        observerPlatform.setSpriteClassId(SpriteClassIdConstants.FLOOR);
        ArrayList<Image> idle = new ArrayList<>(Arrays.asList(new Image(Paths.get("src/main/resources/assets/scenery/ObserverPlatformEyeClosed.png").toUri().toString())));
        ArrayList<Image> open = new ArrayList<>(Arrays.asList(new Image(Paths.get("src/main/resources/assets/scenery/ObserverPlatformEyeOpen.png").toUri().toString())));
        observerPlatform.getAnimation().setAnimationLoopForState(AnimationState.IDLE, idle);
        observerPlatform.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, open);

        HorizontalObserverPlatformBehavior opb = new HorizontalObserverPlatformBehavior(maxX, xVelocity);
        observerPlatform.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), opb));

		return observerPlatform;
	}

	public static Sprite informationalSign(int x, int y, String imagePath)
	{
		Sprite sign = new Sprite();
		sign.setX(x);
		sign.setY(y);
		sign.setLayer(-20);
		ArrayList<Image> informationAsset = new ArrayList<>(Arrays.asList(new Image(Paths.get(imagePath).toUri().toString())));
		sign.getAnimation().setAnimationLoopForState(AnimationState.IDLE, informationAsset);
		return sign;
	}
	public static Sprite dummyInformationalSign(int x, int y)
	{
		Sprite sign = new Sprite();
		sign.setX(x);
		sign.setLayer(-20);
		sign.setY(y);
		sign.setWidth(350);
		sign.setHeight(200);
		return sign;
	}
}
