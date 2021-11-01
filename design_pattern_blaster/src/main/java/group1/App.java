package group1;

import group1.constants.Constants;
import group1.model.Model;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.behavior.DisableBehavior;
import group1.model.sprite.behavior.FaceLeftBehavior;
import group1.model.sprite.behavior.FaceRightBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehaviorWhileKeyIsBeingHeld;
import group1.model.sprite.behavior.MoveBehavior;
import group1.model.sprite.behavior.SpawnSpriteBehavior;
import group1.model.sprite.game_event.GameEvent;
import group1.viewcontroller.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application
{

	//Model singleton reference
	public static Model model = new Model();
	public static ViewController view;


    public static void main( String[] args )
    {
        launch(args);
    }

	@Override
	public void start(Stage mainStage) throws Exception
	{
			view = new ViewController(mainStage);
			model.registerObserver(view);
//		ObserverLevel obLevel = new ObserverLevel();
			
			
			//TODO move all this to Level stuff, just for testing right now
			//TODO this is ideally all handled by creational patterns. PlayerBuilder, ___EnemyBuilder, etc.
			Sprite playerSprite = new Sprite();
			playerSprite.setY(Constants.WINDOW_HEIGHT - 150);
			playerSprite.setWidth(50);
			playerSprite.setHeight(100);
			playerSprite.setVelocityX(10);
			playerSprite.setDirection(Constants.RIGHT);
			playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
			playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
			playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehaviorWhileKeyIsBeingHeld(KeyCode.A)));
			playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehaviorWhileKeyIsBeingHeld(KeyCode.D)));
			playerSprite.setColor(Color.BLUE);
			
			Sprite bulletSprite = new Sprite();
			bulletSprite.setWidth(20);
			bulletSprite.setHeight(20);
			bulletSprite.setVelocityX(5);
			bulletSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));
			bulletSprite.setColor(Color.ORANGE);
			
			playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.SPACE), new SpawnSpriteBehavior((int)(playerSprite.getWidth() + 15), (int)(playerSprite.getHeight() / 2), bulletSprite)));
			
			Sprite floor = new Sprite();
			floor.setWidth(Constants.WINDOW_WIDTH);
			floor.setHeight(10);
			floor.setY(Constants.WINDOW_HEIGHT - 50);
			floor.setX(0);
			floor.setColor(Color.BLACK);
			
			Sprite enemy = new Sprite();
			enemy.setWidth(50);
			enemy.setHeight(100);
			enemy.setX(Constants.WINDOW_WIDTH - 100);
			enemy.setY(Constants.WINDOW_HEIGHT - 150);
			enemy.setColor(Color.RED);
			
			model.addSprite(playerSprite);
			model.addSprite(floor);
			model.addSprite(enemy);
			model.startGameClock();
	}

	//Resets the static model. Used for unit tests and save/load
	public static void resetModel()
	{
		model = new Model();
		if (view != null)
			model.registerObserver(view);
	}
}
