package group1.model.sprite.behavior;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import group1.constants.Constants;
import group1.factories.SpriteFactory;
import group1.model.sprite.AnimationState;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.SpriteClassIdConstants;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class PickNewGunBehavior implements Behavior{

	Sprite playerSprite;
	ArrayList<String> imageRightPaths;
	ArrayList<String> imageLeftPaths;
	ArrayList<Image> playerImageRight;
	ArrayList<Image> playerImageLeft;
	Color gunColor, currentColor;
	private ColorBasedCollisionBehavior colorBasedCollision;
	String imgPath = "";
	
	public PickNewGunBehavior(Sprite playerSprite, Color gunColor) {
		this.playerSprite = playerSprite;
		this.gunColor = gunColor;
	}

	@Override
	public void performBehavior(Sprite sprite) {

			currentColor = gunColor;
			//			sprite.respondToEvent(GameEvent.PickedNewGunEvent());
			colorBasedCollision.setCurrentGunColor(currentColor);
            playerImageRight = new ArrayList<Image>();
            playerSprite.getEventBehaviors().clear();
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.A, -1*Constants.PLAYER_DX)));
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new UpdateVelocityXOnKeyPressBehavior(KeyCode.D, Constants.PLAYER_DX)));
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.A), new UpdateVelocityXBehavior(0)));
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyReleasedEvent(KeyCode.D), new UpdateVelocityXBehavior(0)));
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new PlayerMoveBehavior()));
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new PlayerGravityBehavior(Constants.GRAVITY)));
            //Order is starting to matter for this process - JumpBehavior must come AFTER GravityBehavior
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new JumpBehavior(KeyCode.W, -12)));
            Sprite bulletSprite = SpriteFactory.bullet(gunColor);
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.SPACE), new ShootSpriteBehavior((int)(playerSprite.getWidth()+30), (int)(playerSprite.getHeight() * 0.5), bulletSprite, gunColor)));
            playerSprite.addEventBehavior(new EventBehavior(GameEvent.HealthDepletedEvent(), new ReloadLevelBehavior()));
            playerSprite.addCustomCollision(SpriteClassIdConstants.ENEMY_BULLET, new DecrementHealthBehavior());
            if(currentColor == Color.RED) {
            	imgPath = "0.2x_red_gun";
            }
            else if(currentColor == Color.GREEN) {
            	imgPath = "0.2x_green_gun";
            }
            
		imageRightPaths = new ArrayList<String>();
        imageRightPaths.add("src/main/resources/assets/avatar/"+imgPath+"/walk_right_frame1_0.2x.png");
        imageRightPaths.add("src/main/resources/assets/avatar/"+imgPath+"/walk_right_frame2_0.2x.png");
        imageRightPaths.add("src/main/resources/assets/avatar/"+imgPath+"/walk_right_frame3_0.2x.png");

        for(String imagePath: imageRightPaths)
        {
            playerImageRight.add(new Image(Paths.get(imagePath).toUri().toString()));
        }
        playerSprite.getAnimation().setAnimationLoopForState(AnimationState.RIGHT_MOVEMENT, playerImageRight);
        playerSprite.getAnimation().setState(AnimationState.RIGHT_MOVEMENT);

            playerImageLeft = new ArrayList<Image>();

        imageLeftPaths = new ArrayList<String>();
        imageLeftPaths.add("src/main/resources/assets/avatar/"+imgPath+"/walk_left_frame1_0.2x.png");
        imageLeftPaths.add("src/main/resources/assets/avatar/"+imgPath+"/walk_left_frame2_0.2x.png");
        imageLeftPaths.add("src/main/resources/assets/avatar/"+imgPath+"/walk_left_frame3_0.2x.png");
        for(String imagePath: imageLeftPaths)
        {
            playerImageLeft.add(new Image(Paths.get(imagePath).toUri().toString()));
        }

        playerSprite.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, playerImageLeft);
        playerSprite.getAnimation().setPreviousState(AnimationState.RIGHT_MOVEMENT);
        playerSprite.getAnimation().setState(AnimationState.LEFT_MOVEMENT);
		
	}
	
	public void setColorBasedCollisionBehavior(ColorBasedCollisionBehavior colorBasedCollision) {
		this.colorBasedCollision = colorBasedCollision;
	}

	@Override
	public Behavior copy() {
		return null;
	}

}
