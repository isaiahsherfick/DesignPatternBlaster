package group1;

import group1.model.Model;
import group1.model.level.Level;
import group1.model.sprite.Sprite;
import group1.viewcontroller.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

import group1.factories.LevelFactory;
import group1.factories.SpriteFactory;

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

			ArrayList<Level> levels = new ArrayList<>();
			//This is the order the levels will appear in
			levels.add(LevelFactory.observerLevel());
			levels.add(LevelFactory.strategyLevel());
			levels.add(LevelFactory.commanderLevel());
			levels.add(LevelFactory.compositeLevel());
			levels.add(LevelFactory.factoryLevel());
			levels.add(LevelFactory.singletonLevel());
			levels.add(LevelFactory.MVCLevel());

			for (Level l : levels)
			{
				model.getLevelManager().addLevel(l);
			}
			model.startGame();
	}

	//Resets the static model. Used for unit tests
	public static void resetModel()
	{
		model = new Model();
		if (viewController != null)
			model.registerObserver(viewController);
	}
}
