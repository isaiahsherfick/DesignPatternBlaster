package group1.view;

import group1.App;
import group1.constants.Constants;
import group1.interfaces.Observer;
import group1.model.sprite.Animation;
import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;
import group1.model.sprite.behavior.MoveBehavior;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class View implements Observer
{
	
	private Canvas gameCanvas;
	private Stage mainStage;
	
	public View(Stage mainStage)
	{
		//FXML must be located in resources folder
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(App.class.getResource("mainWindow.fxml"));
		System.out.println(getClass().getName());

		//Create a new main scene from the loaded layout
		Scene mainScene;
		try 
		{
			mainScene = new Scene(fxmlLoader.load());
			//Set the scene for the main stage
			mainStage.setScene(mainScene);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		//Change title, x, y, width, height
		mainStage.setTitle("Design Pattern Blaster 0.0.1");
		mainStage.setX(Constants.WINDOW_X);
		mainStage.setY(Constants.WINDOW_Y);
		mainStage.setWidth(Constants.WINDOW_WIDTH);
		mainStage.setHeight(Constants.WINDOW_HEIGHT);
		
		//Display the window
		mainStage.show();
	}


	public void update()
	{
		//Get list of game objects from the model
		//Use an iterator to draw everything in that list
	}
}
