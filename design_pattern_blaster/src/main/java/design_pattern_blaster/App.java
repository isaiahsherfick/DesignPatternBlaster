package design_pattern_blaster;

import design_pattern_blaster.constants.Constants;
import design_pattern_blaster.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
	
	//Model singleton reference
	public static Model model = new Model();
	
	
    public static void main( String[] args )
    {
        launch(args);
    }

	@Override
	public void start(Stage mainStage) throws Exception 
	{

		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mainWindow.fxml"));

		//Create a new main scene from the loaded layout
		Scene mainScene = new Scene(fxmlLoader.load());

		//Set the scene for the main stage
		mainStage.setScene(mainScene);
		
		//Change title, x, y, width, height
		mainStage.setTitle("Design Pattern Blaster 0.0.1");
		mainStage.setX(Constants.WINDOW_X);
		mainStage.setY(Constants.WINDOW_Y);
		mainStage.setWidth(Constants.WINDOW_WIDTH);
		mainStage.setHeight(Constants.WINDOW_HEIGHT);
		
		//Display the window
		mainStage.show();
	}
	
	//Resets the static model. Used for unit tests and save/load
	public static void resetModel()
	{
		model = new Model();
	}
}
