package group1.megaman_dp;

import group1.megaman_dp.constants.Constants;
import group1.megaman_dp.model.Model;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application
{
	
	//Model singleton reference
	private static Model model = new Model();
	
	
    public static void main( String[] args )
    {
        launch(args);
    }

	@Override
	public void start(Stage mainStage) throws Exception 
	{
		//TODO most of this needs moved out of here, I just slapped it together to get something on the screen
		//									so we have something to demo if we do nothing else this weekend
		
		//Make the red X actually terminate the process 
		mainStage.setOnCloseRequest((WindowEvent event1) ->
		{
			//exit jfx 
			Platform.exit();
			
			//terminate process with exit code 0 (success)
			System.exit(0);
		});
		
		//FXML loader to read our FXML file
		FXMLLoader loader = new FXMLLoader();
		
		//Set location of mainWindow.fxml
		loader.setLocation(App.class.getResource("mainWindow.fxml"));
		
		//Tell the loader who it's loading for 
		loader.setController(this);
		
		//Load from the loader
		VBox windowLayout = (VBox)loader.load();
		
		//Create a new main scene from the loaded layout
		Scene mainScene = new Scene(windowLayout);
		
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
}
