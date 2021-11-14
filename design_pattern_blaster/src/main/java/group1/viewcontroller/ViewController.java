package group1.viewcontroller;

import javafx.scene.input.KeyCode;
import group1.App;
import group1.constants.Constants;
import group1.interfaces.Observer;
import group1.model.KeyInputManager;
import group1.model.sprite.Animation;
import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteManager;
import group1.model.sprite.behavior.MoveBehavior;
import group1.model.sprite.game_event.GameEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

public class ViewController implements Observer
{

	@FXML private Canvas gameCanvas;
	@FXML private AnchorPane anchorPane;

	private static Image backgroundImage;

	static {
		backgroundImage = new Image(Paths.get("src/main/resources/assets/levels/background_city_blue.png").toUri().toString(), 0, 0, false, false);
	}
	private Stage mainStage;

	public ViewController(Stage stage)
	{
		this.mainStage = stage;
		mainStage.setOnCloseRequest( (WindowEvent event1) ->
		{
			Platform.exit();
			System.exit(0);
		});


		//FXML must be located in resources folder
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(App.class.getResource("mainWindow.fxml"));
		fxmlLoader.setController(this);
		//System.out.println(getClass().getName());

		//Create a new main scene from the loaded layout
		Scene mainScene;

		try
		{
			mainScene = new Scene(fxmlLoader.load());
			mainScene.setOnKeyPressed(e ->
			{
				KeyInputManager keyInputManager = App.model.getKeyInputManager();
                if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.D)
                {
                    //This handles the problem of dealing with the user holding two movement keys at the same time
                    //like our old "speedrun glitch"
                    //also allows the user to still hold two keys at once for things like jumping shooting and running at the same time
                    if (e.getCode() == KeyCode.A)
                    {
                        keyInputManager.onKeyRelease(KeyCode.D);
                    }
                    else if (e.getCode() == KeyCode.D)
                    {
                        keyInputManager.onKeyRelease(KeyCode.A);
                    }
                }
				if (!keyInputManager.isPressed(e.getCode()))
				{
					App.model.receiveEvent(GameEvent.KeyPressedEvent(e.getCode()));
					keyInputManager.onKeyPress(e.getCode());
				}
			});
			mainScene.setOnKeyReleased(e ->
			{
				KeyInputManager keyInputManager = App.model.getKeyInputManager();
                if (keyInputManager.isPressed(e.getCode()))
                {
                    App.model.receiveEvent(GameEvent.KeyReleasedEvent(e.getCode()));
				    keyInputManager.onKeyRelease(e.getCode());
                }
			});
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
		gameCanvas.setWidth(Constants.WINDOW_WIDTH);
		gameCanvas.setHeight(Constants.WINDOW_HEIGHT);

		//Display the window
		mainStage.show();
	}

	private void loadBackground()
	{
		double w = backgroundImage.getWidth();
		double h = backgroundImage.getHeight();
		double x = App.model.getGameCamera().getXPos()%backgroundImage.getWidth() - backgroundImage.getWidth();
		double y = App.model.getGameCamera().getYPos()%backgroundImage.getHeight();

		for(int i=0;i<3;i++) {
			gameCanvas.getGraphicsContext2D().drawImage(backgroundImage, x,y,w,h);
			x+=backgroundImage.getWidth();
		}
	}
	public void update()
	{
		loadBackground();
//		if(App.model.getGameCamera().getXPos()>0)
//		{
			//System.out.println(App.model.getGameCamera().getXPos()+" "+gameCanvas.getLayoutX());
			gameCanvas.getGraphicsContext2D().translate(App.model.getGameCamera().getXPos()-gameCanvas.getLayoutX(), App.model.getGameCamera().getYPos());	//start translating camera
//		}
		//TODO refactor this so it's just one model method to do all this stuff
		SpriteManager spriteManager = App.model.getSpriteManager();
		Set<Integer> layerSet = spriteManager.getLayerSet();
		for (Integer layer : layerSet)
		{
//			System.out.println("ViewController.java drawing layer #" + layer);
			ArrayList<Sprite> spritesInThisLayer = spriteManager.getSpriteListByLayer(layer);
			Iterator<Sprite> iterator = spritesInThisLayer.iterator();
			while (iterator.hasNext())
			{
				Sprite next = iterator.next();
				next.draw(gameCanvas.getGraphicsContext2D());
			}
		}
		gameCanvas.getGraphicsContext2D().translate(-App.model.getGameCamera().getXPos(), -App.model.getGameCamera().getYPos());	//again translate camera with everything new which got rendered
	}
}
