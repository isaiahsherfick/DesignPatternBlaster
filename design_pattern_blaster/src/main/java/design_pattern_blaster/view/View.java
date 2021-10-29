package design_pattern_blaster.view;

import design_pattern_blaster.interfaces.Observer;
import design_pattern_blaster.model.sprite.Animation;
import design_pattern_blaster.model.sprite.AnimationState;
import design_pattern_blaster.model.sprite.Sprite;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class View implements Initializable, Observer
{
	@FXML
	public Canvas canvas;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle){
		//random stuff for testing
		Sprite testSprite = new Sprite();
		File spriteSheetFile = new File("megaman_dp/src/main/resources/design_pattern_blaster/sample spritesheet.png");
		testSprite.setAnimation(new Animation(spriteSheetFile));
		HashMap<AnimationState, ArrayList<Image>> stateToAnimationLoop = testSprite.getAnimation().stateToAnimationLoop;
		for (int i = 1; i <= 4; i++) {
			canvas.getGraphicsContext2D().drawImage(stateToAnimationLoop.get(AnimationState.IDLE).get(i-1),
					32*i,0);
		}

	}
	public void update()
	{
		//Get list of game objects from the model
		//Use an iterator to draw everything in that list
	}
}
