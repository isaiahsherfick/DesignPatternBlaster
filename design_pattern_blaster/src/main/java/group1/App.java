package group1;

import group1.constants.Constants;
import group1.model.Model;
import group1.model.sprite.Sprite;
import group1.viewcontroller.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
			Sprite s1 = new Sprite();
			s1.setWidth(50);
			s1.setHeight(50);
			model.addSprite(s1);
	}

	//Resets the static model. Used for unit tests and save/load
	public static void resetModel()
	{
		model = new Model();
		if (view != null)
			model.registerObserver(view);
	}
}
