package group1.view;

import group1.interfaces.Observer;
import group1.model.sprite.Animation;
import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;
import group1.model.sprite.behavior.MoveBehavior;
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
		File spriteSheetFile = new File("design_pattern_blaster/src/main/resources/design_pattern_blaster/sample spritesheet.png");
		testSprite.setAnimation(new Animation(spriteSheetFile));
		HashMap<AnimationState, ArrayList<Image>> stateToAnimationLoop = testSprite.getAnimation().stateToAnimationLoop;
		testSprite.setVelocityX(32);
		for (int i = 1; i <= 4; i++) {
			MoveBehavior mb = new MoveBehavior();
			mb.performBehavior(testSprite);
			canvas.getGraphicsContext2D().drawImage(stateToAnimationLoop.get(AnimationState.IDLE).get(i-1),
					testSprite.getX(), testSprite.getY());
		}

	}
	public void update()
	{
		//Get list of game objects from the model
		//Use an iterator to draw everything in that list
	}
}
