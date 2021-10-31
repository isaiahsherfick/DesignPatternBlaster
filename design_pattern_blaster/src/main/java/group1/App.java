package group1;

import group1.constants.Constants;
import group1.model.Model;
//import group1.view.ObserverLevel;
import group1.view.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{

	//Model singleton reference
	public static Model model = new Model();
	public static View view;


    public static void main( String[] args )
    {
        launch(args);
    }

	@Override
	public void start(Stage mainStage) throws Exception
	{
			view = new View(mainStage);
//		ObserverLevel obLevel = new ObserverLevel();
	}

	//Resets the static model. Used for unit tests and save/load
	public static void resetModel()
	{
		model = new Model();
	}
}
