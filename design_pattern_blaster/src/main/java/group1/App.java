package group1;

import group1.constants.Constants;
import group1.model.Model;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.behavior.FaceLeftBehavior;
import group1.model.sprite.behavior.FaceRightBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehavior;
import group1.model.sprite.behavior.MoveBehavior;
import group1.model.sprite.game_event.GameEvent;
import group1.viewcontroller.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
			Sprite playerSprite = new Sprite();
			playerSprite.setWidth(50);
			playerSprite.setHeight(50);
			playerSprite.setVelocityX(10);
			playerSprite.setDirection(Constants.RIGHT);
			playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
			playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
			playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new HorizontalMoveBehavior()));
			playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new HorizontalMoveBehavior()));
			model.addSprite(playerSprite);
	}

	//Resets the static model. Used for unit tests and save/load
	public static void resetModel()
	{
		model = new Model();
		if (view != null)
			model.registerObserver(view);
	}
}
