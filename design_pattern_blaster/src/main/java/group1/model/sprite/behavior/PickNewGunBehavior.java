package group1.model.sprite.behavior;

import java.nio.file.Paths;
import java.util.ArrayList;

import group1.model.sprite.AnimationState;
import group1.model.sprite.Sprite;
import javafx.scene.image.Image;

public class PickNewGunBehavior implements Behavior{

	Sprite playerSprite;
	ArrayList<String> imageRightPaths;
	ArrayList<String> imageLeftPaths;
	ArrayList<Image> playerImageRight;
	ArrayList<Image> playerImageLeft;
	
	
	
	public PickNewGunBehavior(Sprite playerSprite) {
		this.playerSprite = playerSprite;
	}

	@Override
	public void performBehavior(Sprite sprite) {

            playerImageRight = new ArrayList<Image>();
		
		imageRightPaths = new ArrayList<String>();
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x_red_gun/walk_right_frame1_0.2x.png");
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x_red_gun/walk_right_frame2_0.2x.png");
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x_red_gun/walk_right_frame3_0.2x.png");

        for(String imagePath: imageRightPaths)
        {
            playerImageRight.add(new Image(Paths.get(imagePath).toUri().toString()));
        }
        playerSprite.getAnimation().setAnimationLoopForState(AnimationState.RIGHT_MOVEMENT, playerImageRight);
        playerSprite.getAnimation().setState(AnimationState.RIGHT_MOVEMENT);

            playerImageLeft = new ArrayList<Image>();

        imageLeftPaths = new ArrayList<String>();
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x_red_gun/walk_left_frame1_0.2x.png");
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x_red_gun/walk_left_frame2_0.2x.png");
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x_red_gun/walk_left_frame3_0.2x.png");
        for(String imagePath: imageLeftPaths)
        {
            playerImageLeft.add(new Image(Paths.get(imagePath).toUri().toString()));
        }

        playerSprite.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, playerImageLeft);
        playerSprite.getAnimation().setPreviousState(AnimationState.RIGHT_MOVEMENT);
        playerSprite.getAnimation().setState(AnimationState.LEFT_MOVEMENT);
		
	}

	@Override
	public Behavior copy() {
		return null;
	}

}
