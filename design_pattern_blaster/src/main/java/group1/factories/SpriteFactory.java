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
import group1.model.sprite.AnimationState;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
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
        playerSprite.setY(0);
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
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new JumpBehavior(KeyCode.W, 20)));
        playerSprite.addCustomCollision(SpriteClassIdConstants.FLOOR, new UpdateVelocityYBehavior(0.0));
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

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.SPACE), new ShootSpriteBehavior((int)(playerSprite.getWidth()+30), (int)(playerSprite.getHeight() *0.78), bulletSprite)));
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
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehaviorWhileKeyIsBeingHeld(KeyCode.A)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehaviorWhileKeyIsBeingHeld(KeyCode.D)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new JumpBehavior(KeyCode.W, 20)));
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
        floor.setX(0);
        floor.setY(Constants.FLOOR_Y);
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

        Sprite bulletSprite = commandBullet();

        CommanderBehavior commanderBehavior = new CommanderBehavior();
        commanderBehavior.setShootSpriteBehavior(new ShootSpriteBehavior((int) (commander.getWidth() + 10), (int) (commander.getHeight() * 0.78), bulletSprite));
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
	
	public static Sprite subordinates() 
	{
		Sprite subordinate = new Sprite();
		subordinate.setWidth(40);
		subordinate.setHeight(50);
		subordinate.setX(400);
		subordinate.setY(Constants.WINDOW_HEIGHT - 150);
		subordinate.setColor(Color.YELLOW);
		subordinate.setSpriteClassId(SpriteClassIdConstants.SUBORDINATE);
		
		Sprite bulletSprite = enemyBullet();
		bulletSprite.setHeight(100);
		bulletSprite.setWidth(100);
		bulletSprite.setVelocityY(0);
		bulletSprite.setVelocityX(20);

		subordinate.addCustomCollision(SpriteClassIdConstants.COMMAND, new ShootSpriteBehavior((int)(subordinate.getWidth()+110),(int)(subordinate.getHeight() - 50), bulletSprite));
		subordinate.addCustomCollision(SpriteClassIdConstants.BULLET, new DoNothingBehavior());
		return subordinate;
	}

    public static Sprite observer(Sprite observable, int x, int y) 
    {
        Sprite observer = new Sprite();
        observer.setWidth(50);
        observer.setHeight(50);
        observer.setVelocityX(5);
        observer.setX(x);
        observer.setY(y);
        observer.setColor(Color.GOLD);

		Sprite bulletSprite = enemyBullet();
		
		ObserverBehavior observerBehavior = new ObserverBehavior(observable, 450, 5);
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
}
