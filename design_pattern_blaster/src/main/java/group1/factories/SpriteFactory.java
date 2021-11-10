package group1.factories;

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
import group1.model.Model;
import group1.model.sprite.AnimationState;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.behavior.Behavior;
import group1.model.sprite.behavior.DisableBehavior;
import group1.model.sprite.behavior.DoNothingBehavior;
import group1.model.sprite.behavior.FaceLeftBehavior;
import group1.model.sprite.behavior.FaceRightBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehaviorWhileKeyIsBeingHeld;
import group1.model.sprite.behavior.MoveBehavior;
import group1.model.sprite.behavior.ObserverBehavior;
import group1.model.sprite.behavior.ShootSpriteBehavior;
import group1.model.sprite.behavior.SpawnSpriteBehavior;
import group1.model.sprite.game_event.GameEvent;
import group1.viewcontroller.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import group1.factories.SpriteFactory;
import group1.model.sprite.behavior.JumpBehavior;
import group1.model.sprite.behavior.LoadNextLevelBehavior;


//Creational class for making sprites
//Right now everything is a Factory Method but we could refactor to Builder later
public final class SpriteFactory
{
    private SpriteFactory(){}

    //Player sprite: not the final version by any stretch of the imagination
    public static Sprite player()
    {
        Sprite playerSprite = new Sprite();
        playerSprite.setX(Constants.WINDOW_WIDTH/2 -25);
        playerSprite.setY(Constants.WINDOW_HEIGHT - 200);
        playerSprite.setWidth(50);
        playerSprite.setHeight(100);
        playerSprite.setVelocityX(10);
        playerSprite.setVelocityY(5);
        playerSprite.setSpriteClassId(SpriteClassIdConstants.PLAYER);
        playerSprite.setDirection(Constants.LEFT);
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehaviorWhileKeyIsBeingHeld(KeyCode.A)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehaviorWhileKeyIsBeingHeld(KeyCode.D)));
        playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new JumpBehavior(100, KeyCode.W)));
        playerSprite.setColor(Color.BLUE);
        ArrayList<Image> playerImageRight;
        if(playerSprite.getAnimation().getAnimationLoopForState(AnimationState.RIGHT_MOVEMENT)==null) {
            playerImageRight = new ArrayList<Image>();
        } else {
            playerImageRight = playerSprite.getAnimation().getAnimationLoopForState(AnimationState.RIGHT_MOVEMENT);
        }

        ArrayList<String> imageRightPaths = new ArrayList<String>();
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x/walk_right_frame1_0.2x.png");
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x/walk_right_frame2_0.2x.png");
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x/walk_right_frame3_0.2x.png");
        for(String imagePath: imageRightPaths) {
            playerImageRight.add(new Image(Paths.get(imagePath).toUri().toString()));
        }
        playerSprite.getAnimation().setAnimationLoopForState(AnimationState.RIGHT_MOVEMENT, playerImageRight);
        playerSprite.getAnimation().setState(AnimationState.RIGHT_MOVEMENT);

        ArrayList<Image> playerImageLeft;
        if(playerSprite.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT)==null) {
            playerImageLeft = new ArrayList<Image>();
        } else {
            playerImageLeft = playerSprite.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT);
        }
        ArrayList<String> imageLeftPaths = new ArrayList<String>();
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x/walk_left_frame1_0.2x.png");
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x/walk_left_frame2_0.2x.png");
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x/walk_left_frame3_0.2x.png");
        for(String imagePath: imageLeftPaths) {
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
        bulletSprite.setHeight(12);
        bulletSprite.setVelocityX(40);
        bulletSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));
        bulletSprite.setColor(Color.ORANGE);
        bulletSprite.setDefaultCollisionBehavior(new DisableBehavior());
        bulletSprite.setSpriteClassId(-3);
        return bulletSprite;
    }

    public static Sprite enemyBullet()
    {
        Sprite bulletSprite = new Sprite();
        bulletSprite.setX(100);
        bulletSprite.setY(80);
        bulletSprite.setWidth(24);
        bulletSprite.setHeight(12);
        bulletSprite.setVelocityX(40);
        bulletSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));
        bulletSprite.setColor(Color.RED);
        bulletSprite.setDefaultCollisionBehavior(new DisableBehavior());
        bulletSprite.setSpriteClassId(-3);
        return bulletSprite;
    }

    //TODO change this
    public static Sprite demoFloor()
    {
    	Sprite floor = new Sprite();
    	for(int i=0;i<1000;i++) {
    		floor = new Sprite();
			floor.setWidth(Constants.WINDOW_WIDTH);

//			floor.setHeight(10);
			floor.setY(Constants.WINDOW_HEIGHT - 50);
			floor.setX(-500+i*20);
//			floor.setColor(Color.BLACK);
			ArrayList<Image> floorImageList;
	        if(floor.getAnimation().getAnimationLoopForState(AnimationState.IDLE)==null) {
	            floorImageList = new ArrayList<Image>();
	        } else {
	            floorImageList = floor.getAnimation().getAnimationLoopForState(AnimationState.IDLE);
	        }
            ArrayList<String> imageCenterPaths = new ArrayList<String>();
            imageCenterPaths.add("src/main/resources/assets/levels/ground/0.2x/center_top@0.2x.png");
            for(String imagePath: imageCenterPaths) {
                floorImageList.add(new Image(Paths.get(imagePath).toUri().toString()));
            }
            floor.getAnimation().setAnimationLoopForState(AnimationState.IDLE, floorImageList);
            floor.getAnimation().setState(AnimationState.IDLE);

            App.model.addSprite(floor);
    	}
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

	public static Sprite observer(Sprite observable) 
	{
		Sprite observer = new Sprite();
		observer.setWidth(50);
		observer.setHeight(50);
		observer.setVelocityX(5);
		observer.setX(1400);
		observer.setY(Constants.WINDOW_HEIGHT - 150);
		observer.setColor(Color.GOLD);

		Sprite bulletSprite = enemyBullet();
		
		ObserverBehavior observerBehavior = new ObserverBehavior(observable);
		observerBehavior.setShootSpriteBehavior(new ShootSpriteBehavior((int)(observer.getWidth() + 15), (int)(observer.getHeight() *0.78), bulletSprite));
		
		observer.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), observerBehavior));
		
		
		
		return observer;
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
		
		ObserverBehavior observerBehavior = new ObserverBehavior(observable);
		observerBehavior.setShootSpriteBehavior(new ShootSpriteBehavior((int)(observer.getWidth() + 15), (int)(observer.getHeight() *0.78), bulletSprite));
		
		observer.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), observerBehavior));
		
		
		
		return observer;
	}

	public static Sprite notifier() 
	{
		return demoEnemy2();
	}
}
