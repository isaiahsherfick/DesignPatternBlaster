package group1;

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
import group1.model.sprite.behavior.DisableBehavior;
import group1.model.sprite.behavior.DoNothingBehavior;
import group1.model.sprite.behavior.FaceLeftBehavior;
import group1.model.sprite.behavior.FaceRightBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehaviorWhileKeyIsBeingHeld;
import group1.model.sprite.behavior.MoveBehavior;
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
import group1.model.sprite.SpriteClassIdConstants;

public class App extends Application
{

	//Model singleton reference
	public static Model model = new Model();
	public static ViewController viewController;


    public static void main( String[] args )
    {
        launch(args);
    }

	@Override
	public void start(Stage mainStage) throws Exception
	{
			viewController = new ViewController(mainStage);
			model.registerObserver(viewController);
    //		ObserverLevel obLevel = new ObserverLevel();


			//TODO move all this to Level stuff, just for testing right now
			//TODO this is ideally all handled by creational patterns. PlayerBuilder, ___EnemyBuilder, etc.
            Sprite playerSprite = SpriteFactory.playerSprite();
			Sprite floor = new Sprite();
			floor.setWidth(Constants.WINDOW_WIDTH);
			floor.setHeight(10);
			floor.setY(Constants.WINDOW_HEIGHT - 50);
			floor.setX(10);
			floor.setColor(Color.BLACK);

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
			Sprite enemy1 = enemy.copy();
			enemy1.setX(Constants.WINDOW_WIDTH - 100);
			enemy1.setColor(Color.RED);
			enemy1.setVelocityX(1);
			enemy1.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));
			enemy1.getCustomCollisionMap().addCustomCollision(SpriteClassIdConstants.BULLET, new DisableBehavior());

			model.addSprite(playerSprite);
			model.addSprite(floor);
			model.addSprite(enemy);
			model.addSprite(enemy1);
			model.startGameClock();
	}

	//Resets the static model. Used for unit tests and save/load
	public static void resetModel()
	{
		model = new Model();
		if (viewController != null)
			model.registerObserver(viewController);
	}
}
