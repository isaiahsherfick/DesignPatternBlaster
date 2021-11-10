package group1.model.level;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import group1.model.level.*;

import org.json.simple.JSONObject;

import group1.App;
import group1.constants.Constants;
import group1.interfaces.Loadable;
import group1.model.Model;
import group1.model.sprite.AnimationState;
import group1.model.sprite.EventBehavior;
import group1.model.sprite.Sprite;
import group1.model.sprite.behavior.DisableBehavior;
import group1.model.sprite.behavior.FaceLeftBehavior;
import group1.model.sprite.behavior.FaceRightBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehavior;
import group1.model.sprite.behavior.HorizontalMoveBehaviorWhileKeyIsBeingHeld;
import group1.model.sprite.behavior.ShootSpriteBehavior;
import group1.model.sprite.game_event.GameEvent;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class ObserverLevel
{
	// level corresponds to the level number in the game
//	private static LevelId levelId = LevelId.OBSERVER_LEVEL;
//
	// allSprites contains the list of all Sprites in this level
	private static ArrayList<Sprite> allSprites;
//
//	public LevelId getLevelId()
//	{
//		return levelId;
//	}

	public void addSpriteToList(Sprite s)
	{
		allSprites.add(s);
	}

	public void removeSpriteFromList(Sprite s)
	{
		if(allSprites.contains(s))
		{
			allSprites.remove(s);
		}
		else
		{
//			System.out.println("Sprite not in this list.");
			//TODO return null sprite
		}
	}

	public static void loadLevel() {
		allSprites = new ArrayList<Sprite>();
		Model model = App.model;
		model.clearSprites();  // Clearing old sprites off before loading level
//		App.viewController.loadBackground();

		Sprite playerSprite = new Sprite();
		playerSprite.setX(Constants.WINDOW_WIDTH/2 -25);
		playerSprite.setY(Constants.WINDOW_HEIGHT - 200);
		playerSprite.setWidth(50);
		playerSprite.setHeight(100);
		playerSprite.setVelocityX(10);
		playerSprite.setSpriteClassId(-1);
		playerSprite.setDirection(Constants.LEFT);
		playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.A), new FaceLeftBehavior()));
		playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.D), new FaceRightBehavior()));
		playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehaviorWhileKeyIsBeingHeld(KeyCode.A)));
		playerSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehaviorWhileKeyIsBeingHeld(KeyCode.D)));
		playerSprite.setColor(Color.BLUE);
		Image image = new Image(Paths.get("src/main/resources/assets/avatar/.png").toUri().toString());
		ArrayList<Image> playerImageRight;
        if(playerSprite.getAnimation().getAnimationLoopForState(AnimationState.RIGHT_MOVEMENT)==null) {
            playerImageRight = new ArrayList<Image>();
        } else {
            playerImageRight = playerSprite.getAnimation().getAnimationLoopForState(AnimationState.RIGHT_MOVEMENT);
        }

        ArrayList<String> imageRightPaths = new ArrayList<String>();
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x/walk_right_frame1_0.2x.png");
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x/walk_right_frame2_0.2x.png");
        imageRightPaths.add("src/main/resources/assets/avatar/0.2x/walk_right_frame3_0.2x.png");
        for(String imagePath: imageRightPaths) {
            playerImageRight.add(new Image(Paths.get(imagePath).toUri().toString()));
        }
        playerSprite.getAnimation().setAnimationLoopForState(AnimationState.RIGHT_MOVEMENT, playerImageRight);
        playerSprite.getAnimation().setState(AnimationState.RIGHT_MOVEMENT);

        ArrayList<Image> playerImageLeft;
        if(playerSprite.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT)==null) {
            playerImageLeft = new ArrayList<Image>();
        } else {
            playerImageLeft = playerSprite.getAnimation().getAnimationLoopForState(AnimationState.LEFT_MOVEMENT);
        }
        ArrayList<String> imageLeftPaths = new ArrayList<String>();
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x/walk_left_frame1_0.2x.png");
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x/walk_left_frame2_0.2x.png");
        imageLeftPaths.add("src/main/resources/assets/avatar/0.2x/walk_left_frame3_0.2x.png");
        for(String imagePath: imageLeftPaths) {
            playerImageLeft.add(new Image(Paths.get(imagePath).toUri().toString()));
        }

        playerSprite.getAnimation().setAnimationLoopForState(AnimationState.LEFT_MOVEMENT, playerImageLeft);
        playerSprite.getAnimation().setState(AnimationState.LEFT_MOVEMENT);

        Sprite bulletSprite = bulletNoInsertIntoSpriteManager();

        playerSprite.addEventBehavior(new EventBehavior(GameEvent.KeyPressedEvent(KeyCode.SPACE), new ShootSpriteBehavior((int)(playerSprite.getWidth() + 10), (int)(playerSprite.getHeight() *0.78), bulletSprite)));
        App.model.addSprite(playerSprite);
	}

    public static Sprite bulletNoInsertIntoSpriteManager()
    {
        Sprite bulletSprite = new Sprite();
        bulletSprite.setX(100);
        bulletSprite.setY(80);
        bulletSprite.setWidth(24);
        bulletSprite.setHeight(12);
        bulletSprite.setVelocityX(40);
        bulletSprite.addEventBehavior(new EventBehavior(GameEvent.ClockTickEvent(), new HorizontalMoveBehavior()));
        bulletSprite.setColor(Color.ORANGE);
        bulletSprite.setDefaultCollisionBehavior(new DisableBehavior());
        bulletSprite.setSpriteClassId(-3);
        return bulletSprite;
    }
	public static void clearLevel() {
		allSprites = null;
	}
}
