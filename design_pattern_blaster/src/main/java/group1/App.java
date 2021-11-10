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
			//levels.add(LevelFactory.demoLevel1());
			//levels.add(LevelFactory.demoLevel2());
			levels.add(LevelFactory.observerLevel());

			for (Level l : levels)
			{
				model.getLevelManager().addLevel(l);
			}
			model.startGame();
//            Sprite player =  SpriteFactory.player();
//            Sprite floor = SpriteFactory.demo_floor();
//
//            Sprite enemy1 = SpriteFactory.demo_enemy_1();
//            Sprite enemy2 = SpriteFactory.demo_enemy_2();
//
//			model.startGameClock();
	}

	//Resets the static model. Used for unit tests and save/load
	public static void resetModel()
	{
		model = new Model();
		if (viewController != null)
			model.registerObserver(viewController);
	}
}
